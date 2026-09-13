package com.airis.api.client

import com.airis.api.types.InputFile
import com.airis.api.utils.TelegramJson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.*

/**
 * Low-level transport for Telegram Bot API 10.3.
 *
 * Optimal + optimized:
 * - Ktor CIO engine (coroutine-based, zero native deps, best throughput/memory for many concurrent bots)
 * - kotlinx.serialization (codegen, no reflection)
 * - JSON for regular calls (single allocation JsonObject), multipart only when files present
 * - Reused HttpClient + Json, keep-alive, pipelining via CIO
 * - Respects 429 retry_after via [TelegramException.retryAfter]
 */
class ApiClient(
    val token: String,
    val baseUrl: String = "https://api.telegram.org",
    val timeoutMillis: Long = 60_000,
    configure: HttpClientConfig<CIOEngineConfig>.() -> Unit = {}
) : AutoCloseable {
    private val http = HttpClient(CIO) {
        install(ContentNegotiation) { json(TelegramJson) }
        install(HttpTimeout) {
            requestTimeoutMillis = timeoutMillis
            connectTimeoutMillis = 15_000
            socketTimeoutMillis = timeoutMillis
        }
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 2)
            exponentialDelay()
        }
        configure()
    }

    data class FilePayload(
        val fileName: String,
        val bytes: ByteArray,
        val contentType: String? = null
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is FilePayload) return false
            if (fileName != other.fileName) return false
            if (!bytes.contentEquals(other.bytes)) return false
            return contentType == other.contentType
        }
        override fun hashCode(): Int {
            var r = fileName.hashCode()
            r = 31 * r + bytes.contentHashCode()
            r = 31 * r + (contentType?.hashCode() ?: 0)
            return r
        }
    }

    /**
     * Execute Bot API method.
     * @param method e.g. "sendMessage"
     * @param params JSON-encoded params (nulls already omitted)
     * @param files attachName -> payload (for InputFile.Upload). JSON must contain "attach://attachName".
     */
    suspend fun <T> call(
        method: String,
        params: Map<String, JsonElement>,
        files: Map<String, FilePayload> = emptyMap(),
        resultDeserializer: DeserializationStrategy<T>
    ): T {
        val url = "$baseUrl/bot$token/$method"
        val raw: JsonObject = if (files.isEmpty()) {
            http.post(url) {
                contentType(ContentType.Application.Json)
                setBody(JsonObject(params))
            }.body()
        } else {
            http.submitFormWithBinaryData(
                url = url,
                formData = formData {
                    // Regular fields: primitives as plain, objects/arrays as JSON strings (per Bot API multipart spec)
                    for ((k, v) in params) {
                        when (v) {
                            is JsonPrimitive -> {
                                if (v.isString) append(k, v.content)
                                else append(k, v.toString())
                            }
                            else -> append(k, TelegramJson.encodeToString(JsonElement.serializer(), v))
                        }
                    }
                    for ((attachName, payload) in files) {
                        append(
                            attachName,
                            payload.bytes,
                            Headers.build {
                                append(HttpHeaders.ContentDisposition, "form-data; name=\"$attachName\"; filename=\"${payload.fileName}\"")
                                append(HttpHeaders.ContentType, payload.contentType ?: "application/octet-stream")
                            }
                        )
                    }
                }
            ).body()
        }

        // Parse envelope manually for optimal error path (avoid generic ApiResponse<T> reified issues with polymorphic T)
        val ok = raw["ok"]?.jsonPrimitive?.booleanOrNull ?: false
        if (ok) {
            val resultEl = raw["result"] ?: JsonNull
            // Handle `true` results decoded as Boolean, etc.
            return TelegramJson.decodeFromJsonElement(resultDeserializer, resultEl)
        } else {
            val desc = raw["description"]?.jsonPrimitive?.contentOrNull ?: "Unknown error"
            val code = raw["error_code"]?.jsonPrimitive?.intOrNull
            val prm = raw["parameters"]?.let {
                try { TelegramJson.decodeFromJsonElement(com.airis.api.types.ResponseParameters.serializer(), it) } catch (_: Exception) { null }
            }
            throw TelegramException(code, desc, prm?.retryAfter, prm?.migrateToChatId)
        }
    }

    /**
     * Helper: build params map from vararg pairs, skipping null values (already optimal: no nulls on wire).
     */
    fun paramsOf(vararg pairs: Pair<String, JsonElement?>): Map<String, JsonElement> {
        val m = LinkedHashMap<String, JsonElement>(pairs.size)
        for ((k, v) in pairs) if (v != null && v != JsonNull) m[k] = v
        return m
    }

    /**
     * Collect top-level InputFile.Uploads into files map.
     * Returns Json value for param (file_id / url / attach://).
     */
    fun inputFileToJson(file: InputFile, collect: MutableMap<String, FilePayload>): JsonElement {
        return when (file) {
            is InputFile.FileId -> JsonPrimitive(file.id)
            is InputFile.Url -> JsonPrimitive(file.url)
            is InputFile.Upload -> {
                val bytes: ByteArray = file.bytes ?: file.file?.readBytes()
                    ?: throw IllegalArgumentException("InputFile.Upload must have bytes or file")
                val ct = file.contentType ?: guessContentType(file.fileName)
                collect[file.fileName] = FilePayload(file.fileName, bytes, ct)
                JsonPrimitive("attach://${file.fileName}")
            }
        }
    }

    private fun guessContentType(name: String): String = when {
        name.endsWith(".jpg", true) || name.endsWith(".jpeg", true) -> "image/jpeg"
        name.endsWith(".png", true) -> "image/png"
        name.endsWith(".webp", true) -> "image/webp"
        name.endsWith(".gif", true) -> "image/gif"
        name.endsWith(".mp4", true) -> "video/mp4"
        name.endsWith(".mp3", true) -> "audio/mpeg"
        name.endsWith(".ogg", true) -> "audio/ogg"
        name.endsWith(".pdf", true) -> "application/pdf"
        name.endsWith(".json", true) -> "application/json"
        else -> "application/octet-stream"
    }

    override fun close() = http.close()
}
