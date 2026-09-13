package com.airis.api.polling

import com.airis.api.client.AirisBot
import com.airis.api.client.TelegramException
import com.airis.api.types.Update
import kotlinx.coroutines.*

/**
 * Optimized long-polling runner for Bot API 10.3.
 * - Sequential offset handling (no gaps, no dupes)
 * - Respects 429 retry_after from Telegram (no busy loop)
 * - Exponential backoff on network errors, immediate resume on success
 * - Structured concurrency via CoroutineScope, cancellable Job
 */
class LongPolling(
    private val bot: AirisBot,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO),
    private val timeout: Long = 50,
    private val limit: Long? = null,
    private val allowedUpdates: List<String>? = null,
    private val onUpdate: suspend (Update) -> Unit,
    private val onError: suspend (Throwable) -> Unit = {}
) {
    private var job: Job? = null
    @Volatile private var offset: Long? = null

    fun start(): Job {
        if (job?.isActive == true) return job!!
        job = scope.launch {
            var backoff = 100L
            while (isActive) {
                try {
                    val updates: List<Update> = bot.getUpdates(
                        offset = offset,
                        limit = limit,
                        timeout = timeout,
                        allowedUpdates = allowedUpdates
                    )
                    backoff = 100L
                    for (u in updates) {
                        offset = maxOf(offset ?: 0L, u.updateId + 1)
                        try {
                            onUpdate(u)
                        } catch (e: Exception) {
                            try { onError(e) } catch (_: Exception) {}
                        }
                    }
                } catch (e: TelegramException) {
                    if (e.errorCode == 429 && e.retryAfter != null) {
                        delay(e.retryAfter * 1000L)
                    } else {
                        try { onError(e) } catch (_: Exception) {}
                        delay(backoff)
                        backoff = (backoff * 2).coerceAtMost(5_000L)
                    }
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    try { onError(e) } catch (_: Exception) {}
                    delay(backoff)
                    backoff = (backoff * 2).coerceAtMost(5_000L)
                }
            }
        }
        return job!!
    }

    suspend fun stop() {
        job?.cancelAndJoin()
        job = null
    }
}

/**
 * Convenience: run polling blocking until cancelled.
 */
suspend fun AirisBot.startPolling(
    timeout: Long = 50,
    limit: Long? = null,
    allowedUpdates: List<String>? = null,
    onError: suspend (Throwable) -> Unit = {},
    onUpdate: suspend (Update) -> Unit
) {
    val polling = LongPolling(this, CoroutineScope(currentCoroutineContext()), timeout, limit, allowedUpdates, onUpdate, onError)
    polling.start().join()
}
