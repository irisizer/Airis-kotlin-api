package com.airis.api.webhook

import com.airis.api.types.Update
import com.airis.api.utils.TelegramJson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.JsonObject

/**
 * Optimized webhook receiver for Bot API 10.3 (Ktor CIO server).
 * - Zero-copy read of Update via shared TelegramJson
 * - Optional secret_token validation (X-Telegram-Bot-Api-Secret-Token)
 * - Single route POST /{path}, 200 OK ASAP (Telegram retries on non-2xx)
 */
class WebhookReceiver(
    val port: Int = 8080,
    val path: String = "/webhook",
    val secretToken: String? = null,
    private val onUpdate: suspend (Update) -> Unit,
    private val onError: suspend (Throwable) -> Unit = {}
) : AutoCloseable {
    private var engine: EmbeddedServer<*, *>? = null

    fun start(wait: Boolean = false): EmbeddedServer<*, *> {
        if (engine != null) return engine!!
        engine = embeddedServer(CIO, port = port) {
            routing {
                post(path) {
                    try {
                        if (secretToken != null) {
                            val got = call.request.header("X-Telegram-Bot-Api-Secret-Token")
                            if (got != secretToken) {
                                call.respond(HttpStatusCode.Forbidden, "bad secret")
                                return@post
                            }
                        }
                        val body = call.receiveText()
                        val update = TelegramJson.decodeFromString(Update.serializer(), body)
                        // Ack fast, process async to avoid Telegram timeouts
                        call.respond(HttpStatusCode.OK, "ok")
                        try {
                            onUpdate(update)
                        } catch (e: Exception) {
                            try { onError(e) } catch (_: Exception) {}
                        }
                    } catch (e: Exception) {
                        try { onError(e) } catch (_: Exception) {}
                        try { call.respond(HttpStatusCode.OK, "ok") } catch (_: Exception) {}
                    }
                }
                get("/health") { call.respondText("ok") }
            }
        }.start(wait = wait)
        return engine!!
    }

    override fun close() {
        engine?.stop(500, 2000)
        engine = null
    }
}

/**
 * Parse raw webhook JSON body to [Update] without server (for custom frameworks / AWS Lambda etc).
 */
fun parseWebhookUpdate(json: String): Update =
    TelegramJson.decodeFromString(Update.serializer(), json)

fun parseWebhookUpdate(obj: JsonObject): Update =
    TelegramJson.decodeFromJsonElement(Update.serializer(), obj)
