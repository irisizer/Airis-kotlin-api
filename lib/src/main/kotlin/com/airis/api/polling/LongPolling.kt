package com.airis.api.polling

import com.airis.api.client.AirisBot
import com.airis.api.client.TelegramException
import com.airis.api.types.Update
import com.airis.api.utils.TelegramJson
import kotlinx.coroutines.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull

/**
 * Optimized long-polling runner for Bot API 10.3.
 * - Sequential offset handling (no gaps, no dupes)
 * - Respects 429 retry_after from Telegram (no busy loop)
 * - Exponential backoff on network errors, immediate resume on success
 * - Structured concurrency via CoroutineScope, cancellable Job
 * - Poison-update immunity: every update is decoded individually; one
 *   malformed update is reported via [onMalformedUpdate] and SKIPPED
 *   (offset advances past it) instead of wedging the whole polling loop.
 */
class LongPolling(
    private val bot: AirisBot,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO),
    private val timeout: Long = 50,
    private val limit: Long? = null,
    private val allowedUpdates: List<String>? = null,
    private val onUpdate: suspend (Update) -> Unit,
    private val onError: suspend (Throwable) -> Unit = {},
    private val onMalformedUpdate: suspend (rawJson: String) -> Unit = {}
) {
    private var job: Job? = null
    @Volatile private var offset: Long? = null

    fun start(): Job {
        if (job?.isActive == true) return job!!
        job = scope.launch {
            var backoff = 100L
            while (isActive) {
                try {
                    val raw: List<JsonElement> = bot.getUpdatesRaw(
                        offset = offset,
                        limit = limit,
                        timeout = timeout,
                        allowedUpdates = allowedUpdates
                    )
                    backoff = 100L
                    for (el in raw) {
                        val id = (el as? JsonObject)?.get("update_id")?.jsonPrimitive?.longOrNull
                        val update = try {
                            TelegramJson.decodeFromJsonElement(Update.serializer(), el)
                        } catch (e: Exception) {
                            // Poison update: report raw JSON for diagnostics, then
                            // advance past it — polling must never wedge on one
                            // update Telegram sent in an unexpected shape.
                            try {
                                val rawJson = TelegramJson.encodeToString(JsonElement.serializer(), el)
                                    .take(4000)
                                try { onMalformedUpdate(rawJson) } catch (_: Exception) {}
                                try { onError(e) } catch (_: Exception) {}
                            } catch (_: Exception) {}
                            if (id != null) offset = maxOf(offset ?: 0L, id + 1)
                            else {
                                // Can't even read update_id — fail the batch with
                                // backoff instead of spinning on it.
                                throw e
                            }
                            continue
                        }
                        offset = maxOf(offset ?: 0L, update.updateId + 1)
                        try {
                            onUpdate(update)
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
    onMalformedUpdate: suspend (String) -> Unit = {},
    onUpdate: suspend (Update) -> Unit
) {
    val polling = LongPolling(this, CoroutineScope(currentCoroutineContext()), timeout, limit, allowedUpdates, onUpdate, onError, onMalformedUpdate)
    polling.start().join()
}
