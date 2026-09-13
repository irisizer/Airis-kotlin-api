package com.airis.api.client

/**
 * Exception thrown on Telegram Bot API errors (ok=false).
 * Carries error_code, description and optional auto-handling hints from ResponseParameters.
 */
class TelegramException(
    val errorCode: Int?,
    override val message: String,
    val retryAfter: Long? = null,
    val migrateToChatId: Long? = null
) : Exception("Telegram API error ${errorCode ?: "?"}: $message")
