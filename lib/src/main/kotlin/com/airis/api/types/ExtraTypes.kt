package com.airis.api.types

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

/**
 * Chat identifier: either numeric ID or @username.
 * Optimized: single serializer handling both JSON number and string without boxing overhead.
 */
@Serializable(with = ChatIdSerializer::class)
sealed interface ChatId {
    @Serializable
    data class Id(val value: Long) : ChatId
    @Serializable
    data class Username(val value: String) : ChatId

    companion object {
        fun from(id: Long): ChatId = Id(id)
        fun from(id: Int): ChatId = Id(id.toLong())
        fun from(username: String): ChatId = Username(username)
    }
}

object ChatIdSerializer : KSerializer<ChatId> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("ChatId", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: ChatId) {
        when (value) {
            is ChatId.Id -> encoder.encodeLong(value.value)
            is ChatId.Username -> encoder.encodeString(value.value)
        }
    }

    override fun deserialize(decoder: Decoder): ChatId {
        // Only used for requests (ChatId is request-only), but support both for completeness.
        // JsonDecoder path:
        if (decoder is JsonDecoder) {
            val el = decoder.decodeJsonElement()
            val prim = el.jsonPrimitive
            return if (prim.isString) {
                val s = prim.content
                // Numeric string -> Id for robustness
                s.toLongOrNull()?.let { ChatId.Id(it) } ?: ChatId.Username(s)
            } else {
                ChatId.Id(prim.long)
            }
        }
        // Fallback: try string then long
        return try {
            ChatId.Username(decoder.decodeString())
        } catch (_: Exception) {
            ChatId.Id(decoder.decodeLong())
        }
    }
}

fun Long.toChatId(): ChatId = ChatId.Id(this)
fun Int.toChatId(): ChatId = ChatId.Id(this.toLong())
fun String.toChatId(): ChatId = ChatId.Username(this)

/**
 * File to send: file_id, URL or new upload.
 * Optimized: serializes to plain String (file_id / URL / attach://) — zero overhead for JSON.
 * Uploads are collected by [com.airis.api.client.AirisBot] for multipart/form-data.
 */
@Serializable(with = InputFileSerializer::class)
sealed interface InputFile {
    data class FileId(val id: String) : InputFile
    data class Url(val url: String) : InputFile
    data class Upload(
        val fileName: String,
        val bytes: ByteArray? = null,
        val file: java.io.File? = null,
        val contentType: String? = null
    ) : InputFile {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Upload) return false
            if (fileName != other.fileName) return false
            if (contentType != other.contentType) return false
            // Compare bytes by content, file by path (avoid heavy compare)
            if (file?.absolutePath != other.file?.absolutePath) {
                if (file != null || other.file != null) return false
            }
            if (bytes != null && other.bytes != null) {
                if (!bytes.contentEquals(other.bytes)) return false
            } else if (bytes !== other.bytes) return false
            return true
        }
        override fun hashCode(): Int {
            var r = fileName.hashCode()
            r = 31 * r + (bytes?.contentHashCode() ?: 0)
            r = 31 * r + (file?.absolutePath?.hashCode() ?: 0)
            return r
        }
    }

    companion object {
        fun fromFileId(id: String): InputFile = FileId(id)
        fun fromUrl(url: String): InputFile = Url(url)
        fun fromFile(file: java.io.File, fileName: String = file.name): InputFile =
            Upload(fileName = fileName, file = file)
        fun fromBytes(fileName: String, bytes: ByteArray, contentType: String? = null): InputFile =
            Upload(fileName = fileName, bytes = bytes, contentType = contentType)
    }
}

object InputFileSerializer : KSerializer<InputFile> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("InputFile", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: InputFile) {
        val s = when (value) {
            is InputFile.FileId -> value.id
            is InputFile.Url -> value.url
            is InputFile.Upload -> "attach://${value.fileName}"
        }
        encoder.encodeString(s)
    }

    override fun deserialize(decoder: Decoder): InputFile {
        val s = decoder.decodeString()
        return if (s.startsWith("attach://")) {
            InputFile.FileId(s) // fallback: treat as reference (uploads are request-only)
        } else if (s.startsWith("http://") || s.startsWith("https://")) {
            InputFile.Url(s)
        } else {
            InputFile.FileId(s)
        }
    }
}

fun String.asFileId(): InputFile = InputFile.FileId(this)
fun String.asUrl(): InputFile = InputFile.Url(this)
fun java.io.File.asInputFile(fileName: String = this.name): InputFile = InputFile.fromFile(this, fileName)

/**
 * Reply markup union: InlineKeyboardMarkup | ReplyKeyboardMarkup | ReplyKeyboardRemove | ForceReply.
 * The four implementations are generated in Types*.kt and patched to implement this interface.
 */
@Serializable(with = ReplyMarkupSerializer::class)
sealed interface ReplyMarkup

object ReplyMarkupSerializer : JsonContentPolymorphicSerializer<ReplyMarkup>(ReplyMarkup::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<ReplyMarkup> {
        val obj = element as? JsonObject
            ?: throw IllegalArgumentException("Expected JsonObject for ReplyMarkup")
        return when {
            obj.containsKey("inline_keyboard") -> InlineKeyboardMarkup.serializer() as kotlinx.serialization.DeserializationStrategy<ReplyMarkup>
            obj.containsKey("keyboard") -> ReplyKeyboardMarkup.serializer() as kotlinx.serialization.DeserializationStrategy<ReplyMarkup>
            obj.containsKey("remove_keyboard") -> ReplyKeyboardRemove.serializer() as kotlinx.serialization.DeserializationStrategy<ReplyMarkup>
            obj.containsKey("force_reply") -> ForceReply.serializer() as kotlinx.serialization.DeserializationStrategy<ReplyMarkup>
            else -> throw IllegalArgumentException("Unknown ReplyMarkup shape: ${obj.toString().take(200)}")
        }
    }
}

/**
 * Result of editMessage* methods: either edited [Message] or `true` for inline messages.
 * Optimized single serializer trying Message first, falling back to Boolean.
 */
@Serializable(with = MessageOrTrueSerializer::class)
sealed interface MessageOrTrue {
    data class OfMessage(val message: Message) : MessageOrTrue
    data class OfBoolean(val value: Boolean) : MessageOrTrue

    fun asMessageOrNull(): Message? = (this as? OfMessage)?.message
    fun asBooleanOrNull(): Boolean? = (this as? OfBoolean)?.value
    fun isMessage(): Boolean = this is OfMessage
}

object MessageOrTrueSerializer : KSerializer<MessageOrTrue> {
    private val jsonLoose: Json by lazy {
        Json { ignoreUnknownKeys = true; explicitNulls = false; coerceInputValues = true }
    }
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("MessageOrTrue", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: MessageOrTrue) {
        require(encoder is JsonEncoder)
        when (value) {
            is MessageOrTrue.OfMessage -> encoder.encodeJsonElement(jsonLoose.encodeToJsonElement(Message.serializer(), value.message))
            is MessageOrTrue.OfBoolean -> encoder.encodeBoolean(value.value)
        }
    }

    override fun deserialize(decoder: Decoder): MessageOrTrue {
        require(decoder is JsonDecoder)
        val el = decoder.decodeJsonElement()
        // Boolean true case (inline messages)
        if (el is JsonPrimitive && el.isString.not() && el.booleanOrNull != null) {
            return MessageOrTrue.OfBoolean(el.boolean)
        }
        // Try Message
        return try {
            MessageOrTrue.OfMessage(jsonLoose.decodeFromJsonElement(Message.serializer(), el))
        } catch (_: Exception) {
            // Fallback: if primitive string "true"?
            if (el is JsonPrimitive && el.content == "true") MessageOrTrue.OfBoolean(true)
            else throw IllegalArgumentException("Cannot decode MessageOrTrue from $el")
        }
    }
}

/**
 * Telegram API response wrapper: {"ok":true,"result":...} or {"ok":false,"description":...}.
 */
@Serializable
data class ApiResponse<T>(
    val ok: Boolean,
    val result: T? = null,
    val description: String? = null,
    @SerialName("error_code")
    val errorCode: Int? = null,
    val parameters: ResponseParameters? = null
)
