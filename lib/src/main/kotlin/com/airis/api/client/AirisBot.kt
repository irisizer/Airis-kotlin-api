package com.airis.api.client

import com.airis.api.types.*
import com.airis.api.utils.TelegramJson
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive

/**
 * High-level Telegram Bot API 10.3 client (all 185 methods).
 * Auto-generated from https://core.telegram.org/bots/api (Bot API 10.3, Aug 24 2026).
 *
 * Optimal + optimized:
 * - Single reused [ApiClient] (Ktor CIO) + shared [TelegramJson]
 * - Nulls omitted on wire (explicitNulls=false), unknown fields ignored (forward-compat)
 * - Multipart only for InputFile.Upload, otherwise JSON
 * - Required params first (Kotlin), optional nullable with defaults
 *
 * @param token Bot token from @BotFather
 * @param baseUrl For local Bot API server: http://localhost:8081
 */
class AirisBot(
    val token: String,
    val baseUrl: String = "https://api.telegram.org",
    val timeoutMillis: Long = 60_000
) {
    val api = ApiClient(token, baseUrl, timeoutMillis)

    /** Close underlying HTTP client (Ktor CIO). Note: Bot API method `close()` is separate. */
    fun closeClient() = api.close()

    /**
     * addStickerToSet — Use this method to add a new sticker to a set created by the bot. Emoji sticker sets can have up to 200 stickers. Other sticker sets can have up to 120 stickers. Returns True on success.
     * @return Boolean
     */
    suspend fun addStickerToSet(userId: Long, name: String, sticker: InputSticker): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        p["name"] = JsonPrimitive(name)
        p["sticker"] = TelegramJson.encodeToJsonElement(InputSticker.serializer(), sticker)
        return api.call("addStickerToSet", p, files, Boolean.serializer())
    }

    /**
     * answerCallbackQuery — Use this method to send answers to callback queries sent from inline keyboards . The answer will be displayed to the user as a notification at the top of the chat screen or as an alert. On success, True is returned. Alternatively, the user can be redirected to the specified Game URL. For this option
     * @return Boolean
     */
    suspend fun answerCallbackQuery(callbackQueryId: String, text: String? = null, showAlert: Boolean? = null, url: String? = null, cacheTime: Long? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["callback_query_id"] = JsonPrimitive(callbackQueryId)
        if (text != null) p["text"] = JsonPrimitive(text)
        if (showAlert != null) p["show_alert"] = JsonPrimitive(showAlert)
        if (url != null) p["url"] = JsonPrimitive(url)
        if (cacheTime != null) p["cache_time"] = JsonPrimitive(cacheTime)
        return api.call("answerCallbackQuery", p, files, Boolean.serializer())
    }

    /**
     * answerChatJoinRequestQuery — Use this method to process a received chat join request query. Returns True on success.
     * @return Boolean
     */
    suspend fun answerChatJoinRequestQuery(chatJoinRequestQueryId: String, result: String): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_join_request_query_id"] = JsonPrimitive(chatJoinRequestQueryId)
        p["result"] = JsonPrimitive(result)
        return api.call("answerChatJoinRequestQuery", p, files, Boolean.serializer())
    }

    /**
     * answerGuestQuery — Use this method to reply to a received guest message. On success, a SentGuestMessage object is returned.
     * @return SentGuestMessage
     */
    suspend fun answerGuestQuery(guestQueryId: String, result: InlineQueryResult): SentGuestMessage {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["guest_query_id"] = JsonPrimitive(guestQueryId)
        p["result"] = TelegramJson.encodeToJsonElement(InlineQueryResult.serializer(), result)
        return api.call("answerGuestQuery", p, files, SentGuestMessage.serializer())
    }

    /**
     * answerInlineQuery — Use this method to send answers to an inline query. On success, True is returned. No more than 50 results per query are allowed.
     * @return Boolean
     */
    suspend fun answerInlineQuery(inlineQueryId: String, results: List<InlineQueryResult>, cacheTime: Long? = null, isPersonal: Boolean? = null, nextOffset: String? = null, button: InlineQueryResultsButton? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["inline_query_id"] = JsonPrimitive(inlineQueryId)
        p["results"] = TelegramJson.encodeToJsonElement(ListSerializer(InlineQueryResult.serializer()), results)
        if (cacheTime != null) p["cache_time"] = JsonPrimitive(cacheTime)
        if (isPersonal != null) p["is_personal"] = JsonPrimitive(isPersonal)
        if (nextOffset != null) p["next_offset"] = JsonPrimitive(nextOffset)
        if (button != null) p["button"] = TelegramJson.encodeToJsonElement(InlineQueryResultsButton.serializer(), button)
        return api.call("answerInlineQuery", p, files, Boolean.serializer())
    }

    /**
     * answerPreCheckoutQuery — Once the user has confirmed their payment and shipping details, the Bot API sends the final confirmation in the form of an Update with the field pre_checkout_query . Use this method to respond to such pre-checkout queries. On success, True is returned. Note: The Bot API must receive an answer within
     * @return Boolean
     */
    suspend fun answerPreCheckoutQuery(preCheckoutQueryId: String, ok: Boolean, errorMessage: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["pre_checkout_query_id"] = JsonPrimitive(preCheckoutQueryId)
        p["ok"] = JsonPrimitive(ok)
        if (errorMessage != null) p["error_message"] = JsonPrimitive(errorMessage)
        return api.call("answerPreCheckoutQuery", p, files, Boolean.serializer())
    }

    /**
     * answerShippingQuery — If you sent an invoice requesting a shipping address and the parameter is_flexible was specified, the Bot API will send an Update with a shipping_query field to the bot. Use this method to reply to shipping queries. On success, True is returned.
     * @return Boolean
     */
    suspend fun answerShippingQuery(shippingQueryId: String, ok: Boolean, shippingOptions: List<ShippingOption>? = null, errorMessage: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["shipping_query_id"] = JsonPrimitive(shippingQueryId)
        p["ok"] = JsonPrimitive(ok)
        if (shippingOptions != null) p["shipping_options"] = TelegramJson.encodeToJsonElement(ListSerializer(ShippingOption.serializer()), shippingOptions)
        if (errorMessage != null) p["error_message"] = JsonPrimitive(errorMessage)
        return api.call("answerShippingQuery", p, files, Boolean.serializer())
    }

    /**
     * answerWebAppQuery — Use this method to set the result of an interaction with a Web App and send a corresponding message on behalf of the user to the chat from which the query originated. On success, a SentWebAppMessage object is returned.
     * @return SentWebAppMessage
     */
    suspend fun answerWebAppQuery(webAppQueryId: String, result: InlineQueryResult): SentWebAppMessage {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["web_app_query_id"] = JsonPrimitive(webAppQueryId)
        p["result"] = TelegramJson.encodeToJsonElement(InlineQueryResult.serializer(), result)
        return api.call("answerWebAppQuery", p, files, SentWebAppMessage.serializer())
    }

    /**
     * approveChatJoinRequest — Use this method to approve a chat join request. The bot must be an administrator in the chat for this to work and must have the can_invite_users administrator right. Returns True on success.
     * @return Boolean
     */
    suspend fun approveChatJoinRequest(chatId: ChatId, userId: Long): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["user_id"] = JsonPrimitive(userId)
        return api.call("approveChatJoinRequest", p, files, Boolean.serializer())
    }

    /**
     * approveSuggestedPost — Use this method to approve a suggested post in a direct messages chat. The bot must have the &#39;can_post_messages&#39; administrator right in the corresponding channel chat. Returns True on success.
     * @return Boolean
     */
    suspend fun approveSuggestedPost(chatId: Long, messageId: Long, sendDate: Long? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = JsonPrimitive(chatId)
        p["message_id"] = JsonPrimitive(messageId)
        if (sendDate != null) p["send_date"] = JsonPrimitive(sendDate)
        return api.call("approveSuggestedPost", p, files, Boolean.serializer())
    }

    /**
     * banChatMember — Use this method to ban a user in a group, a supergroup or a channel. In the case of supergroups and channels, the user will not be able to return to the chat on their own using invite links, etc., unless unbanned first. The bot must be an administrator in the chat for this to work and must have the 
     * @return Boolean
     */
    suspend fun banChatMember(chatId: ChatId, userId: Long, untilDate: Long? = null, revokeMessages: Boolean? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["user_id"] = JsonPrimitive(userId)
        if (untilDate != null) p["until_date"] = JsonPrimitive(untilDate)
        if (revokeMessages != null) p["revoke_messages"] = JsonPrimitive(revokeMessages)
        return api.call("banChatMember", p, files, Boolean.serializer())
    }

    /**
     * banChatSenderChat — Use this method to ban a channel chat in a supergroup or a channel. Until the chat is unbanned , the owner of the banned chat won&#39;t be able to send messages on behalf of any of their channels . The bot must be an administrator in the supergroup or channel for this to work and must have the appro
     * @return Boolean
     */
    suspend fun banChatSenderChat(chatId: ChatId, senderChatId: Long): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["sender_chat_id"] = JsonPrimitive(senderChatId)
        return api.call("banChatSenderChat", p, files, Boolean.serializer())
    }

    /**
     * close — Use this method to close the bot instance before moving it from one local server to another. You need to delete the webhook before calling this method to ensure that the bot isn&#39;t launched again after server restart. The method will return error 429 in the first 10 minutes after the bot is launc
     * @return Boolean
     */
    suspend fun close(): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        return api.call("close", p, files, Boolean.serializer())
    }

    /**
     * closeForumTopic — Use this method to close an open topic in a forum supergroup chat. The bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator rights, unless it is the creator of the topic. Returns True on success.
     * @return Boolean
     */
    suspend fun closeForumTopic(chatId: ChatId, messageThreadId: Long): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["message_thread_id"] = JsonPrimitive(messageThreadId)
        return api.call("closeForumTopic", p, files, Boolean.serializer())
    }

    /**
     * closeGeneralForumTopic — Use this method to close an open &#39;General&#39; topic in a forum supergroup chat. The bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator rights. Returns True on success.
     * @return Boolean
     */
    suspend fun closeGeneralForumTopic(chatId: ChatId): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        return api.call("closeGeneralForumTopic", p, files, Boolean.serializer())
    }

    /**
     * convertGiftToStars — Converts a given regular gift to Telegram Stars. Requires the can_convert_gifts_to_stars business bot right. Returns True on success.
     * @return Boolean
     */
    suspend fun convertGiftToStars(businessConnectionId: String, ownedGiftId: String): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        p["owned_gift_id"] = JsonPrimitive(ownedGiftId)
        return api.call("convertGiftToStars", p, files, Boolean.serializer())
    }

    /**
     * copyMessage — Use this method to copy messages of any kind. Service messages, paid media messages, giveaway messages, giveaway winners messages, and invoice messages can&#39;t be copied. A quiz poll can be copied only if the value of the field correct_option_ids is known to the bot. The method is analogous to the
     * @return MessageId
     */
    suspend fun copyMessage(chatId: ChatId, fromChatId: ChatId, messageId: Long, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, videoStartTimestamp: Long? = null, caption: String? = null, parseMode: String? = null, captionEntities: List<MessageEntity>? = null, showCaptionAboveMedia: Boolean? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): MessageId {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["from_chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), fromChatId)
        p["message_id"] = JsonPrimitive(messageId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (videoStartTimestamp != null) p["video_start_timestamp"] = JsonPrimitive(videoStartTimestamp)
        if (caption != null) p["caption"] = JsonPrimitive(caption)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (captionEntities != null) p["caption_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), captionEntities)
        if (showCaptionAboveMedia != null) p["show_caption_above_media"] = JsonPrimitive(showCaptionAboveMedia)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("copyMessage", p, files, MessageId.serializer())
    }

    /**
     * copyMessages — Use this method to copy messages of any kind. If some of the specified messages can&#39;t be found or copied, they are skipped. Service messages, paid media messages, giveaway messages, giveaway winners messages, and invoice messages can&#39;t be copied. A quiz poll can be copied only if the value o
     * @return List<MessageId>
     */
    suspend fun copyMessages(chatId: ChatId, fromChatId: ChatId, messageIds: List<Long>, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, removeCaption: Boolean? = null): List<MessageId> {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["from_chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), fromChatId)
        p["message_ids"] = TelegramJson.encodeToJsonElement(ListSerializer(Long.serializer()), messageIds)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (removeCaption != null) p["remove_caption"] = JsonPrimitive(removeCaption)
        return api.call("copyMessages", p, files, ListSerializer(MessageId.serializer()))
    }

    /**
     * createChatInviteLink — Use this method to create an additional invite link for a chat. The bot must be an administrator in the chat for this to work and must have the appropriate administrator rights. The link can be revoked using the method revokeChatInviteLink . Returns the new invite link as ChatInviteLink object.
     * @return ChatInviteLink
     */
    suspend fun createChatInviteLink(chatId: ChatId, name: String? = null, expireDate: Long? = null, memberLimit: Long? = null, createsJoinRequest: Boolean? = null): ChatInviteLink {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        if (name != null) p["name"] = JsonPrimitive(name)
        if (expireDate != null) p["expire_date"] = JsonPrimitive(expireDate)
        if (memberLimit != null) p["member_limit"] = JsonPrimitive(memberLimit)
        if (createsJoinRequest != null) p["creates_join_request"] = JsonPrimitive(createsJoinRequest)
        return api.call("createChatInviteLink", p, files, ChatInviteLink.serializer())
    }

    /**
     * createChatSubscriptionInviteLink — Use this method to create a subscription invite link for a channel chat. The bot must have the can_invite_users administrator rights. The link can be edited using the method editChatSubscriptionInviteLink or revoked using the method revokeChatInviteLink . Returns the new invite link as a ChatInviteL
     * @return ChatInviteLink
     */
    suspend fun createChatSubscriptionInviteLink(chatId: ChatId, subscriptionPeriod: Long, subscriptionPrice: Long, name: String? = null): ChatInviteLink {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["subscription_period"] = JsonPrimitive(subscriptionPeriod)
        p["subscription_price"] = JsonPrimitive(subscriptionPrice)
        if (name != null) p["name"] = JsonPrimitive(name)
        return api.call("createChatSubscriptionInviteLink", p, files, ChatInviteLink.serializer())
    }

    /**
     * createForumTopic — Use this method to create a topic in a forum supergroup chat or a private chat with a user. In the case of a supergroup chat the bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator right. Returns information about the created topic as a ForumTo
     * @return ForumTopic
     */
    suspend fun createForumTopic(chatId: ChatId, name: String, iconColor: Long? = null, iconCustomEmojiId: String? = null): ForumTopic {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["name"] = JsonPrimitive(name)
        if (iconColor != null) p["icon_color"] = JsonPrimitive(iconColor)
        if (iconCustomEmojiId != null) p["icon_custom_emoji_id"] = JsonPrimitive(iconCustomEmojiId)
        return api.call("createForumTopic", p, files, ForumTopic.serializer())
    }

    /**
     * createInvoiceLink — Use this method to create a link for an invoice. Returns the created invoice link as String on success.
     * @return String
     */
    suspend fun createInvoiceLink(title: String, description: String, payload: String, currency: String, prices: List<LabeledPrice>, businessConnectionId: String? = null, providerToken: String? = null, subscriptionPeriod: Long? = null, maxTipAmount: Long? = null, suggestedTipAmounts: List<Long>? = null, providerData: String? = null, photoUrl: String? = null, photoSize: Long? = null, photoWidth: Long? = null, photoHeight: Long? = null, needName: Boolean? = null, needPhoneNumber: Boolean? = null, needEmail: Boolean? = null, needShippingAddress: Boolean? = null, sendPhoneNumberToProvider: Boolean? = null, sendEmailToProvider: Boolean? = null, isFlexible: Boolean? = null): String {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["title"] = JsonPrimitive(title)
        p["description"] = JsonPrimitive(description)
        p["payload"] = JsonPrimitive(payload)
        p["currency"] = JsonPrimitive(currency)
        p["prices"] = TelegramJson.encodeToJsonElement(ListSerializer(LabeledPrice.serializer()), prices)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (providerToken != null) p["provider_token"] = JsonPrimitive(providerToken)
        if (subscriptionPeriod != null) p["subscription_period"] = JsonPrimitive(subscriptionPeriod)
        if (maxTipAmount != null) p["max_tip_amount"] = JsonPrimitive(maxTipAmount)
        if (suggestedTipAmounts != null) p["suggested_tip_amounts"] = TelegramJson.encodeToJsonElement(ListSerializer(Long.serializer()), suggestedTipAmounts)
        if (providerData != null) p["provider_data"] = JsonPrimitive(providerData)
        if (photoUrl != null) p["photo_url"] = JsonPrimitive(photoUrl)
        if (photoSize != null) p["photo_size"] = JsonPrimitive(photoSize)
        if (photoWidth != null) p["photo_width"] = JsonPrimitive(photoWidth)
        if (photoHeight != null) p["photo_height"] = JsonPrimitive(photoHeight)
        if (needName != null) p["need_name"] = JsonPrimitive(needName)
        if (needPhoneNumber != null) p["need_phone_number"] = JsonPrimitive(needPhoneNumber)
        if (needEmail != null) p["need_email"] = JsonPrimitive(needEmail)
        if (needShippingAddress != null) p["need_shipping_address"] = JsonPrimitive(needShippingAddress)
        if (sendPhoneNumberToProvider != null) p["send_phone_number_to_provider"] = JsonPrimitive(sendPhoneNumberToProvider)
        if (sendEmailToProvider != null) p["send_email_to_provider"] = JsonPrimitive(sendEmailToProvider)
        if (isFlexible != null) p["is_flexible"] = JsonPrimitive(isFlexible)
        return api.call("createInvoiceLink", p, files, String.serializer())
    }

    /**
     * createNewStickerSet — Use this method to create a new sticker set owned by a user. The bot will be able to edit the sticker set thus created. Returns True on success.
     * @return Boolean
     */
    suspend fun createNewStickerSet(userId: Long, name: String, title: String, stickers: List<InputSticker>, stickerType: String? = null, needsRepainting: Boolean? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        p["name"] = JsonPrimitive(name)
        p["title"] = JsonPrimitive(title)
        p["stickers"] = TelegramJson.encodeToJsonElement(ListSerializer(InputSticker.serializer()), stickers)
        if (stickerType != null) p["sticker_type"] = JsonPrimitive(stickerType)
        if (needsRepainting != null) p["needs_repainting"] = JsonPrimitive(needsRepainting)
        return api.call("createNewStickerSet", p, files, Boolean.serializer())
    }

    /**
     * declineChatJoinRequest — Use this method to decline a chat join request. The bot must be an administrator in the chat for this to work and must have the can_invite_users administrator right. Returns True on success.
     * @return Boolean
     */
    suspend fun declineChatJoinRequest(chatId: ChatId, userId: Long): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["user_id"] = JsonPrimitive(userId)
        return api.call("declineChatJoinRequest", p, files, Boolean.serializer())
    }

    /**
     * declineSuggestedPost — Use this method to decline a suggested post in a direct messages chat. The bot must have the &#39;can_manage_direct_messages&#39; administrator right in the corresponding channel chat. Returns True on success.
     * @return Boolean
     */
    suspend fun declineSuggestedPost(chatId: Long, messageId: Long, comment: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = JsonPrimitive(chatId)
        p["message_id"] = JsonPrimitive(messageId)
        if (comment != null) p["comment"] = JsonPrimitive(comment)
        return api.call("declineSuggestedPost", p, files, Boolean.serializer())
    }

    /**
     * deleteAllMessageReactions — Use this method to remove up to 10000 recent reactions in a group or a supergroup chat added by a given user or chat. The bot must have the &#39;can_delete_messages&#39; administrator right in the chat. Returns True on success.
     * @return Boolean
     */
    suspend fun deleteAllMessageReactions(chatId: ChatId, userId: Long? = null, actorChatId: Long? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        if (userId != null) p["user_id"] = JsonPrimitive(userId)
        if (actorChatId != null) p["actor_chat_id"] = JsonPrimitive(actorChatId)
        return api.call("deleteAllMessageReactions", p, files, Boolean.serializer())
    }

    /**
     * deleteBusinessMessages — Delete messages on behalf of a business account. Requires the can_delete_sent_messages business bot right to delete messages sent by the bot itself, or the can_delete_all_messages business bot right to delete any message. Returns True on success.
     * @return Boolean
     */
    suspend fun deleteBusinessMessages(businessConnectionId: String, messageIds: List<Long>): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        p["message_ids"] = TelegramJson.encodeToJsonElement(ListSerializer(Long.serializer()), messageIds)
        return api.call("deleteBusinessMessages", p, files, Boolean.serializer())
    }

    /**
     * deleteChatPhoto — Use this method to delete a chat photo. Photos can&#39;t be changed for private chats. The bot must be an administrator in the chat for this to work and must have the appropriate administrator rights. Returns True on success.
     * @return Boolean
     */
    suspend fun deleteChatPhoto(chatId: ChatId): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        return api.call("deleteChatPhoto", p, files, Boolean.serializer())
    }

    /**
     * deleteChatStickerSet — Use this method to delete a group sticker set from a supergroup. The bot must be an administrator in the chat for this to work and must have the appropriate administrator rights. Use the field can_set_sticker_set optionally returned in getChat requests to check if the bot can use this method. Return
     * @return Boolean
     */
    suspend fun deleteChatStickerSet(chatId: ChatId): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        return api.call("deleteChatStickerSet", p, files, Boolean.serializer())
    }

    /**
     * deleteEphemeralMessage — Use this method to delete an ephemeral message. Note that it is not guaranteed that the user will receive the message deletion event, especially if they are offline. Returns True on success.
     * @return Boolean
     */
    suspend fun deleteEphemeralMessage(chatId: ChatId, receiverUserId: Long, ephemeralMessageId: Long): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["receiver_user_id"] = JsonPrimitive(receiverUserId)
        p["ephemeral_message_id"] = JsonPrimitive(ephemeralMessageId)
        return api.call("deleteEphemeralMessage", p, files, Boolean.serializer())
    }

    /**
     * deleteForumTopic — Use this method to delete a forum topic along with all its messages in a forum supergroup chat or a private chat with a user. In the case of a supergroup chat the bot must be an administrator in the chat for this to work and must have the can_delete_messages administrator rights. Returns True on suc
     * @return Boolean
     */
    suspend fun deleteForumTopic(chatId: ChatId, messageThreadId: Long): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["message_thread_id"] = JsonPrimitive(messageThreadId)
        return api.call("deleteForumTopic", p, files, Boolean.serializer())
    }

    /**
     * deleteMessage — Use this method to delete a message, including service messages, with the following limitations: - A message can only be deleted if it was sent less than 48 hours ago. - Service messages about a supergroup, channel, or forum topic creation can&#39;t be deleted. - A dice message in a private chat can
     * @return Boolean
     */
    suspend fun deleteMessage(chatId: ChatId, messageId: Long): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["message_id"] = JsonPrimitive(messageId)
        return api.call("deleteMessage", p, files, Boolean.serializer())
    }

    /**
     * deleteMessageReaction — Use this method to remove a reaction from a message in a group or a supergroup chat. The bot must have the &#39;can_delete_messages&#39; administrator right in the chat. Returns True on success.
     * @return Boolean
     */
    suspend fun deleteMessageReaction(chatId: ChatId, messageId: Long, userId: Long? = null, actorChatId: Long? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["message_id"] = JsonPrimitive(messageId)
        if (userId != null) p["user_id"] = JsonPrimitive(userId)
        if (actorChatId != null) p["actor_chat_id"] = JsonPrimitive(actorChatId)
        return api.call("deleteMessageReaction", p, files, Boolean.serializer())
    }

    /**
     * deleteMessages — Use this method to delete multiple messages simultaneously. If some of the specified messages can&#39;t be found, they are skipped. Returns True on success.
     * @return Boolean
     */
    suspend fun deleteMessages(chatId: ChatId, messageIds: List<Long>): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["message_ids"] = TelegramJson.encodeToJsonElement(ListSerializer(Long.serializer()), messageIds)
        return api.call("deleteMessages", p, files, Boolean.serializer())
    }

    /**
     * deleteMyCommands — Use this method to delete the list of the bot&#39;s commands for the given scope and user language. After deletion, higher level commands will be shown to affected users. Returns True on success.
     * @return Boolean
     */
    suspend fun deleteMyCommands(scope: BotCommandScope? = null, languageCode: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (scope != null) p["scope"] = TelegramJson.encodeToJsonElement(BotCommandScope.serializer(), scope)
        if (languageCode != null) p["language_code"] = JsonPrimitive(languageCode)
        return api.call("deleteMyCommands", p, files, Boolean.serializer())
    }

    /**
     * deleteStickerFromSet — Use this method to delete a sticker from a set created by the bot. Returns True on success.
     * @return Boolean
     */
    suspend fun deleteStickerFromSet(sticker: String): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["sticker"] = JsonPrimitive(sticker)
        return api.call("deleteStickerFromSet", p, files, Boolean.serializer())
    }

    /**
     * deleteStickerSet — Use this method to delete a sticker set that was created by the bot. Returns True on success.
     * @return Boolean
     */
    suspend fun deleteStickerSet(name: String): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["name"] = JsonPrimitive(name)
        return api.call("deleteStickerSet", p, files, Boolean.serializer())
    }

    /**
     * deleteStory — Deletes a story previously posted by the bot on behalf of a managed business account. Requires the can_manage_stories business bot right. Returns True on success.
     * @return Boolean
     */
    suspend fun deleteStory(businessConnectionId: String, storyId: Long): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        p["story_id"] = JsonPrimitive(storyId)
        return api.call("deleteStory", p, files, Boolean.serializer())
    }

    /**
     * deleteWebhook — Use this method to remove webhook integration if you decide to switch back to getUpdates . Returns True on success.
     * @return Boolean
     */
    suspend fun deleteWebhook(dropPendingUpdates: Boolean? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (dropPendingUpdates != null) p["drop_pending_updates"] = JsonPrimitive(dropPendingUpdates)
        return api.call("deleteWebhook", p, files, Boolean.serializer())
    }

    /**
     * editChatInviteLink — Use this method to edit a non-primary invite link created by the bot. The bot must be an administrator in the chat for this to work and must have the appropriate administrator rights. Returns the edited invite link as a ChatInviteLink object.
     * @return ChatInviteLink
     */
    suspend fun editChatInviteLink(chatId: ChatId, inviteLink: String, name: String? = null, expireDate: Long? = null, memberLimit: Long? = null, createsJoinRequest: Boolean? = null): ChatInviteLink {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["invite_link"] = JsonPrimitive(inviteLink)
        if (name != null) p["name"] = JsonPrimitive(name)
        if (expireDate != null) p["expire_date"] = JsonPrimitive(expireDate)
        if (memberLimit != null) p["member_limit"] = JsonPrimitive(memberLimit)
        if (createsJoinRequest != null) p["creates_join_request"] = JsonPrimitive(createsJoinRequest)
        return api.call("editChatInviteLink", p, files, ChatInviteLink.serializer())
    }

    /**
     * editChatSubscriptionInviteLink — Use this method to edit a subscription invite link created by the bot. The bot must have the can_invite_users administrator rights. Returns the edited invite link as a ChatInviteLink object.
     * @return ChatInviteLink
     */
    suspend fun editChatSubscriptionInviteLink(chatId: ChatId, inviteLink: String, name: String? = null): ChatInviteLink {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["invite_link"] = JsonPrimitive(inviteLink)
        if (name != null) p["name"] = JsonPrimitive(name)
        return api.call("editChatSubscriptionInviteLink", p, files, ChatInviteLink.serializer())
    }

    /**
     * editEphemeralMessageCaption — Use this method to edit the caption of an ephemeral message. Note that it is not guaranteed that the user will receive the message edit event, especially if they are offline. On success, True is returned.
     * @return Boolean
     */
    suspend fun editEphemeralMessageCaption(chatId: ChatId, receiverUserId: Long, ephemeralMessageId: Long, caption: String? = null, parseMode: String? = null, captionEntities: List<MessageEntity>? = null, showCaptionAboveMedia: Boolean? = null, replyMarkup: InlineKeyboardMarkup? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["receiver_user_id"] = JsonPrimitive(receiverUserId)
        p["ephemeral_message_id"] = JsonPrimitive(ephemeralMessageId)
        if (caption != null) p["caption"] = JsonPrimitive(caption)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (captionEntities != null) p["caption_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), captionEntities)
        if (showCaptionAboveMedia != null) p["show_caption_above_media"] = JsonPrimitive(showCaptionAboveMedia)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(InlineKeyboardMarkup.serializer(), replyMarkup)
        return api.call("editEphemeralMessageCaption", p, files, Boolean.serializer())
    }

    /**
     * editEphemeralMessageMedia — Use this method to edit the media of an ephemeral message. Note that it is not guaranteed that the user will receive the message edit event, especially if they are offline. On success, True is returned.
     * @return Boolean
     */
    suspend fun editEphemeralMessageMedia(chatId: ChatId, receiverUserId: Long, ephemeralMessageId: Long, media: InputMedia, replyMarkup: InlineKeyboardMarkup? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["receiver_user_id"] = JsonPrimitive(receiverUserId)
        p["ephemeral_message_id"] = JsonPrimitive(ephemeralMessageId)
        p["media"] = TelegramJson.encodeToJsonElement(InputMedia.serializer(), media)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(InlineKeyboardMarkup.serializer(), replyMarkup)
        return api.call("editEphemeralMessageMedia", p, files, Boolean.serializer())
    }

    /**
     * editEphemeralMessageReplyMarkup — Use this method to edit only the reply markup of an ephemeral message. Note that it is not guaranteed that the user will receive the message edit event, especially if they are offline. On success, True is returned.
     * @return Boolean
     */
    suspend fun editEphemeralMessageReplyMarkup(chatId: ChatId, receiverUserId: Long, ephemeralMessageId: Long, replyMarkup: InlineKeyboardMarkup? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["receiver_user_id"] = JsonPrimitive(receiverUserId)
        p["ephemeral_message_id"] = JsonPrimitive(ephemeralMessageId)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(InlineKeyboardMarkup.serializer(), replyMarkup)
        return api.call("editEphemeralMessageReplyMarkup", p, files, Boolean.serializer())
    }

    /**
     * editEphemeralMessageText — Use this method to edit an ephemeral text or rich message. Note that it is not guaranteed that the user will receive the message edit event, especially if they are offline. On success, True is returned.
     * @return Boolean
     */
    suspend fun editEphemeralMessageText(chatId: ChatId, receiverUserId: Long, ephemeralMessageId: Long, text: String? = null, parseMode: String? = null, entities: List<MessageEntity>? = null, richMessage: InputRichMessage? = null, linkPreviewOptions: LinkPreviewOptions? = null, replyMarkup: InlineKeyboardMarkup? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["receiver_user_id"] = JsonPrimitive(receiverUserId)
        p["ephemeral_message_id"] = JsonPrimitive(ephemeralMessageId)
        if (text != null) p["text"] = JsonPrimitive(text)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (entities != null) p["entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), entities)
        if (richMessage != null) p["rich_message"] = TelegramJson.encodeToJsonElement(InputRichMessage.serializer(), richMessage)
        if (linkPreviewOptions != null) p["link_preview_options"] = TelegramJson.encodeToJsonElement(LinkPreviewOptions.serializer(), linkPreviewOptions)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(InlineKeyboardMarkup.serializer(), replyMarkup)
        return api.call("editEphemeralMessageText", p, files, Boolean.serializer())
    }

    /**
     * editForumTopic — Use this method to edit name and icon of a topic in a forum supergroup chat or a private chat with a user. In the case of a supergroup chat the bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator rights, unless it is the creator of the topic. R
     * @return Boolean
     */
    suspend fun editForumTopic(chatId: ChatId, messageThreadId: Long, name: String? = null, iconCustomEmojiId: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (name != null) p["name"] = JsonPrimitive(name)
        if (iconCustomEmojiId != null) p["icon_custom_emoji_id"] = JsonPrimitive(iconCustomEmojiId)
        return api.call("editForumTopic", p, files, Boolean.serializer())
    }

    /**
     * editGeneralForumTopic — Use this method to edit the name of the &#39;General&#39; topic in a forum supergroup chat. The bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator rights. Returns True on success.
     * @return Boolean
     */
    suspend fun editGeneralForumTopic(chatId: ChatId, name: String): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["name"] = JsonPrimitive(name)
        return api.call("editGeneralForumTopic", p, files, Boolean.serializer())
    }

    /**
     * editMessageCaption — Use this method to edit captions of messages. On success, if the edited message is not an inline message, the edited Message is returned, otherwise True is returned. Note that business messages that were not sent by the bot and do not contain an inline keyboard can only be edited within 48 hours fro
     * @return MessageOrTrue
     */
    suspend fun editMessageCaption(businessConnectionId: String? = null, chatId: ChatId? = null, messageId: Long? = null, inlineMessageId: String? = null, caption: String? = null, parseMode: String? = null, captionEntities: List<MessageEntity>? = null, showCaptionAboveMedia: Boolean? = null, replyMarkup: InlineKeyboardMarkup? = null): MessageOrTrue {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (chatId != null) p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        if (messageId != null) p["message_id"] = JsonPrimitive(messageId)
        if (inlineMessageId != null) p["inline_message_id"] = JsonPrimitive(inlineMessageId)
        if (caption != null) p["caption"] = JsonPrimitive(caption)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (captionEntities != null) p["caption_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), captionEntities)
        if (showCaptionAboveMedia != null) p["show_caption_above_media"] = JsonPrimitive(showCaptionAboveMedia)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(InlineKeyboardMarkup.serializer(), replyMarkup)
        return api.call("editMessageCaption", p, files, MessageOrTrue.serializer())
    }

    /**
     * editMessageChecklist — Use this method to edit a checklist on behalf of a connected business account. On success, the edited Message is returned.
     * @return Message
     */
    suspend fun editMessageChecklist(businessConnectionId: String, chatId: ChatId, messageId: Long, checklist: InputChecklist, replyMarkup: InlineKeyboardMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["message_id"] = JsonPrimitive(messageId)
        p["checklist"] = TelegramJson.encodeToJsonElement(InputChecklist.serializer(), checklist)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(InlineKeyboardMarkup.serializer(), replyMarkup)
        return api.call("editMessageChecklist", p, files, Message.serializer())
    }

    /**
     * editMessageLiveLocation — Use this method to edit live location messages. A location can be edited until its live_period expires or editing is explicitly disabled by a call to stopMessageLiveLocation . On success, if the edited message is not an inline message, the edited Message is returned, otherwise True is returned.
     * @return MessageOrTrue
     */
    suspend fun editMessageLiveLocation(latitude: Double, longitude: Double, businessConnectionId: String? = null, chatId: ChatId? = null, messageId: Long? = null, inlineMessageId: String? = null, livePeriod: Long? = null, horizontalAccuracy: Double? = null, heading: Long? = null, proximityAlertRadius: Long? = null, replyMarkup: InlineKeyboardMarkup? = null): MessageOrTrue {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["latitude"] = JsonPrimitive(latitude)
        p["longitude"] = JsonPrimitive(longitude)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (chatId != null) p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        if (messageId != null) p["message_id"] = JsonPrimitive(messageId)
        if (inlineMessageId != null) p["inline_message_id"] = JsonPrimitive(inlineMessageId)
        if (livePeriod != null) p["live_period"] = JsonPrimitive(livePeriod)
        if (horizontalAccuracy != null) p["horizontal_accuracy"] = JsonPrimitive(horizontalAccuracy)
        if (heading != null) p["heading"] = JsonPrimitive(heading)
        if (proximityAlertRadius != null) p["proximity_alert_radius"] = JsonPrimitive(proximityAlertRadius)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(InlineKeyboardMarkup.serializer(), replyMarkup)
        return api.call("editMessageLiveLocation", p, files, MessageOrTrue.serializer())
    }

    /**
     * editMessageMedia — Use this method to edit animation, audio, document, live photo, photo, or video messages, or to replace a text or a rich message with a media. If a message is part of a message album, then it can be edited only to an audio for audio albums, only to a document for document albums and to a photo, a li
     * @return MessageOrTrue
     */
    suspend fun editMessageMedia(media: InputMedia, businessConnectionId: String? = null, chatId: ChatId? = null, messageId: Long? = null, inlineMessageId: String? = null, replyMarkup: InlineKeyboardMarkup? = null): MessageOrTrue {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["media"] = TelegramJson.encodeToJsonElement(InputMedia.serializer(), media)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (chatId != null) p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        if (messageId != null) p["message_id"] = JsonPrimitive(messageId)
        if (inlineMessageId != null) p["inline_message_id"] = JsonPrimitive(inlineMessageId)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(InlineKeyboardMarkup.serializer(), replyMarkup)
        return api.call("editMessageMedia", p, files, MessageOrTrue.serializer())
    }

    /**
     * editMessageReplyMarkup — Use this method to edit only the reply markup of messages. On success, if the edited message is not an inline message, the edited Message is returned, otherwise True is returned. Note that business messages that were not sent by the bot and do not contain an inline keyboard can only be edited within
     * @return MessageOrTrue
     */
    suspend fun editMessageReplyMarkup(businessConnectionId: String? = null, chatId: ChatId? = null, messageId: Long? = null, inlineMessageId: String? = null, replyMarkup: InlineKeyboardMarkup? = null): MessageOrTrue {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (chatId != null) p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        if (messageId != null) p["message_id"] = JsonPrimitive(messageId)
        if (inlineMessageId != null) p["inline_message_id"] = JsonPrimitive(inlineMessageId)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(InlineKeyboardMarkup.serializer(), replyMarkup)
        return api.call("editMessageReplyMarkup", p, files, MessageOrTrue.serializer())
    }

    /**
     * editMessageText — Use this method to edit text, rich and game messages. On success, if the edited message is not an inline message, the edited Message is returned, otherwise True is returned. Note that business messages that were not sent by the bot and do not contain an inline keyboard can only be edited within 48 h
     * @return MessageOrTrue
     */
    suspend fun editMessageText(businessConnectionId: String? = null, chatId: ChatId? = null, messageId: Long? = null, inlineMessageId: String? = null, text: String? = null, parseMode: String? = null, entities: List<MessageEntity>? = null, linkPreviewOptions: LinkPreviewOptions? = null, richMessage: InputRichMessage? = null, replyMarkup: InlineKeyboardMarkup? = null): MessageOrTrue {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (chatId != null) p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        if (messageId != null) p["message_id"] = JsonPrimitive(messageId)
        if (inlineMessageId != null) p["inline_message_id"] = JsonPrimitive(inlineMessageId)
        if (text != null) p["text"] = JsonPrimitive(text)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (entities != null) p["entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), entities)
        if (linkPreviewOptions != null) p["link_preview_options"] = TelegramJson.encodeToJsonElement(LinkPreviewOptions.serializer(), linkPreviewOptions)
        if (richMessage != null) p["rich_message"] = TelegramJson.encodeToJsonElement(InputRichMessage.serializer(), richMessage)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(InlineKeyboardMarkup.serializer(), replyMarkup)
        return api.call("editMessageText", p, files, MessageOrTrue.serializer())
    }

    /**
     * editStory — Edits a story previously posted by the bot on behalf of a managed business account. Requires the can_manage_stories business bot right. Returns Story on success.
     * @return Story
     */
    suspend fun editStory(businessConnectionId: String, storyId: Long, content: InputStoryContent, caption: String? = null, parseMode: String? = null, captionEntities: List<MessageEntity>? = null, areas: List<StoryArea>? = null): Story {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        p["story_id"] = JsonPrimitive(storyId)
        p["content"] = TelegramJson.encodeToJsonElement(InputStoryContent.serializer(), content)
        if (caption != null) p["caption"] = JsonPrimitive(caption)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (captionEntities != null) p["caption_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), captionEntities)
        if (areas != null) p["areas"] = TelegramJson.encodeToJsonElement(ListSerializer(StoryArea.serializer()), areas)
        return api.call("editStory", p, files, Story.serializer())
    }

    /**
     * editUserStarSubscription — Allows the bot to cancel or re-enable extension of a subscription paid in Telegram Stars. Returns True on success.
     * @return Boolean
     */
    suspend fun editUserStarSubscription(userId: Long, telegramPaymentChargeId: String, isCanceled: Boolean): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        p["telegram_payment_charge_id"] = JsonPrimitive(telegramPaymentChargeId)
        p["is_canceled"] = JsonPrimitive(isCanceled)
        return api.call("editUserStarSubscription", p, files, Boolean.serializer())
    }

    /**
     * exportChatInviteLink — Use this method to generate a new primary invite link for a chat; any previously generated primary link is revoked. The bot must be an administrator in the chat for this to work and must have the appropriate administrator rights. Returns the new invite link as String on success.
     * @return String
     */
    suspend fun exportChatInviteLink(chatId: ChatId): String {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        return api.call("exportChatInviteLink", p, files, String.serializer())
    }

    /**
     * forwardMessage — Use this method to forward messages of any kind. Service messages and messages with protected content can&#39;t be forwarded. On success, the sent Message is returned.
     * @return Message
     */
    suspend fun forwardMessage(chatId: ChatId, fromChatId: ChatId, messageId: Long, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, videoStartTimestamp: Long? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["from_chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), fromChatId)
        p["message_id"] = JsonPrimitive(messageId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (videoStartTimestamp != null) p["video_start_timestamp"] = JsonPrimitive(videoStartTimestamp)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        return api.call("forwardMessage", p, files, Message.serializer())
    }

    /**
     * forwardMessages — Use this method to forward multiple messages of any kind. If some of the specified messages can&#39;t be found or forwarded, they are skipped. Service messages and messages with protected content can&#39;t be forwarded. Album grouping is kept for forwarded messages. On success, an Array of MessageId
     * @return List<MessageId>
     */
    suspend fun forwardMessages(chatId: ChatId, fromChatId: ChatId, messageIds: List<Long>, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null): List<MessageId> {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["from_chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), fromChatId)
        p["message_ids"] = TelegramJson.encodeToJsonElement(ListSerializer(Long.serializer()), messageIds)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        return api.call("forwardMessages", p, files, ListSerializer(MessageId.serializer()))
    }

    /**
     * getAvailableGifts — Returns the list of gifts that can be sent by the bot to users and channel chats. Requires no parameters. Returns a Gifts object.
     * @return Gifts
     */
    suspend fun getAvailableGifts(): Gifts {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        return api.call("getAvailableGifts", p, files, Gifts.serializer())
    }

    /**
     * getBusinessAccountGifts — Returns the gifts received and owned by a managed business account. Requires the can_view_gifts_and_stars business bot right. Returns OwnedGifts on success.
     * @return OwnedGifts
     */
    suspend fun getBusinessAccountGifts(businessConnectionId: String, excludeUnsaved: Boolean? = null, excludeSaved: Boolean? = null, excludeUnlimited: Boolean? = null, excludeLimitedUpgradable: Boolean? = null, excludeLimitedNonUpgradable: Boolean? = null, excludeUnique: Boolean? = null, excludeFromBlockchain: Boolean? = null, sortByPrice: Boolean? = null, offset: String? = null, limit: Long? = null): OwnedGifts {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (excludeUnsaved != null) p["exclude_unsaved"] = JsonPrimitive(excludeUnsaved)
        if (excludeSaved != null) p["exclude_saved"] = JsonPrimitive(excludeSaved)
        if (excludeUnlimited != null) p["exclude_unlimited"] = JsonPrimitive(excludeUnlimited)
        if (excludeLimitedUpgradable != null) p["exclude_limited_upgradable"] = JsonPrimitive(excludeLimitedUpgradable)
        if (excludeLimitedNonUpgradable != null) p["exclude_limited_non_upgradable"] = JsonPrimitive(excludeLimitedNonUpgradable)
        if (excludeUnique != null) p["exclude_unique"] = JsonPrimitive(excludeUnique)
        if (excludeFromBlockchain != null) p["exclude_from_blockchain"] = JsonPrimitive(excludeFromBlockchain)
        if (sortByPrice != null) p["sort_by_price"] = JsonPrimitive(sortByPrice)
        if (offset != null) p["offset"] = JsonPrimitive(offset)
        if (limit != null) p["limit"] = JsonPrimitive(limit)
        return api.call("getBusinessAccountGifts", p, files, OwnedGifts.serializer())
    }

    /**
     * getBusinessAccountStarBalance — Returns the amount of Telegram Stars owned by a managed business account. Requires the can_view_gifts_and_stars business bot right. Returns StarAmount on success.
     * @return StarAmount
     */
    suspend fun getBusinessAccountStarBalance(businessConnectionId: String): StarAmount {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        return api.call("getBusinessAccountStarBalance", p, files, StarAmount.serializer())
    }

    /**
     * getBusinessConnection — Use this method to get information about the connection of the bot with a business account. Returns a BusinessConnection object on success.
     * @return BusinessConnection
     */
    suspend fun getBusinessConnection(businessConnectionId: String): BusinessConnection {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        return api.call("getBusinessConnection", p, files, BusinessConnection.serializer())
    }

    /**
     * getChat — Use this method to get up-to-date information about the chat. Returns a ChatFullInfo object on success.
     * @return ChatFullInfo
     */
    suspend fun getChat(chatId: ChatId): ChatFullInfo {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        return api.call("getChat", p, files, ChatFullInfo.serializer())
    }

    /**
     * getChatAdministrators — Use this method to get a list of administrators in a chat. Returns an Array of ChatMember objects.
     * @return List<ChatMember>
     */
    suspend fun getChatAdministrators(chatId: ChatId, returnBots: Boolean? = null): List<ChatMember> {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        if (returnBots != null) p["return_bots"] = JsonPrimitive(returnBots)
        return api.call("getChatAdministrators", p, files, ListSerializer(ChatMember.serializer()))
    }

    /**
     * getChatGifts — Returns the gifts owned by a chat. Returns OwnedGifts on success.
     * @return OwnedGifts
     */
    suspend fun getChatGifts(chatId: ChatId, excludeUnsaved: Boolean? = null, excludeSaved: Boolean? = null, excludeUnlimited: Boolean? = null, excludeLimitedUpgradable: Boolean? = null, excludeLimitedNonUpgradable: Boolean? = null, excludeFromBlockchain: Boolean? = null, excludeUnique: Boolean? = null, sortByPrice: Boolean? = null, offset: String? = null, limit: Long? = null): OwnedGifts {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        if (excludeUnsaved != null) p["exclude_unsaved"] = JsonPrimitive(excludeUnsaved)
        if (excludeSaved != null) p["exclude_saved"] = JsonPrimitive(excludeSaved)
        if (excludeUnlimited != null) p["exclude_unlimited"] = JsonPrimitive(excludeUnlimited)
        if (excludeLimitedUpgradable != null) p["exclude_limited_upgradable"] = JsonPrimitive(excludeLimitedUpgradable)
        if (excludeLimitedNonUpgradable != null) p["exclude_limited_non_upgradable"] = JsonPrimitive(excludeLimitedNonUpgradable)
        if (excludeFromBlockchain != null) p["exclude_from_blockchain"] = JsonPrimitive(excludeFromBlockchain)
        if (excludeUnique != null) p["exclude_unique"] = JsonPrimitive(excludeUnique)
        if (sortByPrice != null) p["sort_by_price"] = JsonPrimitive(sortByPrice)
        if (offset != null) p["offset"] = JsonPrimitive(offset)
        if (limit != null) p["limit"] = JsonPrimitive(limit)
        return api.call("getChatGifts", p, files, OwnedGifts.serializer())
    }

    /**
     * getChatMember — Use this method to get information about a member of a chat. The method is only guaranteed to work for other users if the bot is an administrator in the chat. Returns a ChatMember object on success.
     * @return ChatMember
     */
    suspend fun getChatMember(chatId: ChatId, userId: Long): ChatMember {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["user_id"] = JsonPrimitive(userId)
        return api.call("getChatMember", p, files, ChatMember.serializer())
    }

    /**
     * getChatMemberCount — Use this method to get the number of members in a chat. Returns Integer on success.
     * @return Long
     */
    suspend fun getChatMemberCount(chatId: ChatId): Long {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        return api.call("getChatMemberCount", p, files, Long.serializer())
    }

    /**
     * getChatMenuButton — Use this method to get the current value of the bot&#39;s menu button in a private chat, or the default menu button. Returns MenuButton on success.
     * @return MenuButton
     */
    suspend fun getChatMenuButton(chatId: Long? = null): MenuButton {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (chatId != null) p["chat_id"] = JsonPrimitive(chatId)
        return api.call("getChatMenuButton", p, files, MenuButton.serializer())
    }

    /**
     * getCustomEmojiStickers — Use this method to get information about custom emoji stickers by their identifiers. Returns an Array of Sticker objects.
     * @return List<Sticker>
     */
    suspend fun getCustomEmojiStickers(customEmojiIds: List<String>): List<Sticker> {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["custom_emoji_ids"] = TelegramJson.encodeToJsonElement(ListSerializer(String.serializer()), customEmojiIds)
        return api.call("getCustomEmojiStickers", p, files, ListSerializer(Sticker.serializer()))
    }

    /**
     * getFile — Use this method to get basic information about a file and prepare it for downloading. For the moment, bots can download files of up to 20MB in size. On success, a File object is returned. The file can then be downloaded via the link https://api.telegram.org/file/bot&lt;token&gt;/&lt;file_path&gt; , 
     * @return File
     */
    suspend fun getFile(fileId: String): File {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["file_id"] = JsonPrimitive(fileId)
        return api.call("getFile", p, files, File.serializer())
    }

    /**
     * getForumTopicIconStickers — Use this method to get custom emoji stickers, which can be used as a forum topic icon by any user. Requires no parameters. Returns an Array of Sticker objects.
     * @return List<Sticker>
     */
    suspend fun getForumTopicIconStickers(): List<Sticker> {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        return api.call("getForumTopicIconStickers", p, files, ListSerializer(Sticker.serializer()))
    }

    /**
     * getGameHighScores — Use this method to get data for high score tables. Will return the score of the specified user and several of their neighbors in a game. Returns an Array of GameHighScore objects. This method will currently return scores for the target user, plus two of their closest neighbors on each side. Will als
     * @return List<GameHighScore>
     */
    suspend fun getGameHighScores(userId: Long, chatId: Long? = null, messageId: Long? = null, inlineMessageId: String? = null): List<GameHighScore> {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        if (chatId != null) p["chat_id"] = JsonPrimitive(chatId)
        if (messageId != null) p["message_id"] = JsonPrimitive(messageId)
        if (inlineMessageId != null) p["inline_message_id"] = JsonPrimitive(inlineMessageId)
        return api.call("getGameHighScores", p, files, ListSerializer(GameHighScore.serializer()))
    }

    /**
     * getManagedBotAccessSettings — Use this method to get the access settings of a managed bot. Returns a BotAccessSettings object on success.
     * @return BotAccessSettings
     */
    suspend fun getManagedBotAccessSettings(userId: Long): BotAccessSettings {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        return api.call("getManagedBotAccessSettings", p, files, BotAccessSettings.serializer())
    }

    /**
     * getManagedBotToken — Use this method to get the token of a managed bot. Returns the token as String on success.
     * @return String
     */
    suspend fun getManagedBotToken(userId: Long): String {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        return api.call("getManagedBotToken", p, files, String.serializer())
    }

    /**
     * getMe — A simple method for testing your bot&#39;s authentication token. Requires no parameters. Returns basic information about the bot in form of a User object.
     * @return User
     */
    suspend fun getMe(): User {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        return api.call("getMe", p, files, User.serializer())
    }

    /**
     * getMyCommands — Use this method to get the current list of the bot&#39;s commands for the given scope and user language. Returns an Array of BotCommand objects. If commands aren&#39;t set, an empty list is returned.
     * @return List<BotCommand>
     */
    suspend fun getMyCommands(scope: BotCommandScope? = null, languageCode: String? = null): List<BotCommand> {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (scope != null) p["scope"] = TelegramJson.encodeToJsonElement(BotCommandScope.serializer(), scope)
        if (languageCode != null) p["language_code"] = JsonPrimitive(languageCode)
        return api.call("getMyCommands", p, files, ListSerializer(BotCommand.serializer()))
    }

    /**
     * getMyDefaultAdministratorRights — Use this method to get the current default administrator rights of the bot. Returns ChatAdministratorRights on success.
     * @return ChatAdministratorRights
     */
    suspend fun getMyDefaultAdministratorRights(forChannels: Boolean? = null): ChatAdministratorRights {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (forChannels != null) p["for_channels"] = JsonPrimitive(forChannels)
        return api.call("getMyDefaultAdministratorRights", p, files, ChatAdministratorRights.serializer())
    }

    /**
     * getMyDescription — Use this method to get the current bot description for the given user language. Returns BotDescription on success.
     * @return BotDescription
     */
    suspend fun getMyDescription(languageCode: String? = null): BotDescription {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (languageCode != null) p["language_code"] = JsonPrimitive(languageCode)
        return api.call("getMyDescription", p, files, BotDescription.serializer())
    }

    /**
     * getMyName — Use this method to get the current bot name for the given user language. Returns BotName on success.
     * @return BotName
     */
    suspend fun getMyName(languageCode: String? = null): BotName {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (languageCode != null) p["language_code"] = JsonPrimitive(languageCode)
        return api.call("getMyName", p, files, BotName.serializer())
    }

    /**
     * getMyShortDescription — Use this method to get the current bot short description for the given user language. Returns BotShortDescription on success.
     * @return BotShortDescription
     */
    suspend fun getMyShortDescription(languageCode: String? = null): BotShortDescription {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (languageCode != null) p["language_code"] = JsonPrimitive(languageCode)
        return api.call("getMyShortDescription", p, files, BotShortDescription.serializer())
    }

    /**
     * getMyStarBalance — A method to get the current Telegram Stars balance of the bot. Requires no parameters. On success, returns a StarAmount object.
     * @return StarAmount
     */
    suspend fun getMyStarBalance(): StarAmount {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        return api.call("getMyStarBalance", p, files, StarAmount.serializer())
    }

    /**
     * getStarTransactions — Returns the bot&#39;s Telegram Star transactions in chronological order. On success, returns a StarTransactions object.
     * @return StarTransactions
     */
    suspend fun getStarTransactions(offset: Long? = null, limit: Long? = null): StarTransactions {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (offset != null) p["offset"] = JsonPrimitive(offset)
        if (limit != null) p["limit"] = JsonPrimitive(limit)
        return api.call("getStarTransactions", p, files, StarTransactions.serializer())
    }

    /**
     * getStickerSet — Use this method to get a sticker set. On success, a StickerSet object is returned.
     * @return StickerSet
     */
    suspend fun getStickerSet(name: String): StickerSet {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["name"] = JsonPrimitive(name)
        return api.call("getStickerSet", p, files, StickerSet.serializer())
    }

    /**
     * getUpdates — Use this method to receive incoming updates using long polling ( wiki ). Returns an Array of Update objects.
     * @return List<Update>
     */
    suspend fun getUpdates(offset: Long? = null, limit: Long? = null, timeout: Long? = null, allowedUpdates: List<String>? = null): List<Update> {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (offset != null) p["offset"] = JsonPrimitive(offset)
        if (limit != null) p["limit"] = JsonPrimitive(limit)
        if (timeout != null) p["timeout"] = JsonPrimitive(timeout)
        if (allowedUpdates != null) p["allowed_updates"] = TelegramJson.encodeToJsonElement(ListSerializer(String.serializer()), allowedUpdates)
        return api.call("getUpdates", p, files, ListSerializer(Update.serializer()))
    }

    /**
     * getUserChatBoosts — Use this method to get the list of boosts added to a chat by a user. Requires administrator rights in the chat. Returns a UserChatBoosts object.
     * @return UserChatBoosts
     */
    suspend fun getUserChatBoosts(chatId: ChatId, userId: Long): UserChatBoosts {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["user_id"] = JsonPrimitive(userId)
        return api.call("getUserChatBoosts", p, files, UserChatBoosts.serializer())
    }

    /**
     * getUserGifts — Returns the gifts owned and hosted by a user. Returns OwnedGifts on success.
     * @return OwnedGifts
     */
    suspend fun getUserGifts(userId: Long, excludeUnlimited: Boolean? = null, excludeLimitedUpgradable: Boolean? = null, excludeLimitedNonUpgradable: Boolean? = null, excludeFromBlockchain: Boolean? = null, excludeUnique: Boolean? = null, sortByPrice: Boolean? = null, offset: String? = null, limit: Long? = null): OwnedGifts {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        if (excludeUnlimited != null) p["exclude_unlimited"] = JsonPrimitive(excludeUnlimited)
        if (excludeLimitedUpgradable != null) p["exclude_limited_upgradable"] = JsonPrimitive(excludeLimitedUpgradable)
        if (excludeLimitedNonUpgradable != null) p["exclude_limited_non_upgradable"] = JsonPrimitive(excludeLimitedNonUpgradable)
        if (excludeFromBlockchain != null) p["exclude_from_blockchain"] = JsonPrimitive(excludeFromBlockchain)
        if (excludeUnique != null) p["exclude_unique"] = JsonPrimitive(excludeUnique)
        if (sortByPrice != null) p["sort_by_price"] = JsonPrimitive(sortByPrice)
        if (offset != null) p["offset"] = JsonPrimitive(offset)
        if (limit != null) p["limit"] = JsonPrimitive(limit)
        return api.call("getUserGifts", p, files, OwnedGifts.serializer())
    }

    /**
     * getUserPersonalChatMessages — Use this method to get the last messages from the personal chat (i.e., the chat currently added to their profile) of a given user. On success, an Array of Message objects is returned.
     * @return List<Message>
     */
    suspend fun getUserPersonalChatMessages(userId: Long, limit: Long): List<Message> {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        p["limit"] = JsonPrimitive(limit)
        return api.call("getUserPersonalChatMessages", p, files, ListSerializer(Message.serializer()))
    }

    /**
     * getUserProfileAudios — Use this method to get a list of profile audios for a user. Returns a UserProfileAudios object.
     * @return UserProfileAudios
     */
    suspend fun getUserProfileAudios(userId: Long, offset: Long? = null, limit: Long? = null): UserProfileAudios {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        if (offset != null) p["offset"] = JsonPrimitive(offset)
        if (limit != null) p["limit"] = JsonPrimitive(limit)
        return api.call("getUserProfileAudios", p, files, UserProfileAudios.serializer())
    }

    /**
     * getUserProfilePhotos — Use this method to get a list of profile pictures for a user. Returns a UserProfilePhotos object.
     * @return UserProfilePhotos
     */
    suspend fun getUserProfilePhotos(userId: Long, offset: Long? = null, limit: Long? = null): UserProfilePhotos {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        if (offset != null) p["offset"] = JsonPrimitive(offset)
        if (limit != null) p["limit"] = JsonPrimitive(limit)
        return api.call("getUserProfilePhotos", p, files, UserProfilePhotos.serializer())
    }

    /**
     * getWebhookInfo — Use this method to get current webhook status. Requires no parameters. On success, returns a WebhookInfo object. If the bot is using getUpdates , will return an object with the url field empty.
     * @return WebhookInfo
     */
    suspend fun getWebhookInfo(): WebhookInfo {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        return api.call("getWebhookInfo", p, files, WebhookInfo.serializer())
    }

    /**
     * giftPremiumSubscription — Gifts a Telegram Premium subscription to the given user. Returns True on success.
     * @return Boolean
     */
    suspend fun giftPremiumSubscription(userId: Long, monthCount: Long, starCount: Long, text: String? = null, textParseMode: String? = null, textEntities: List<MessageEntity>? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        p["month_count"] = JsonPrimitive(monthCount)
        p["star_count"] = JsonPrimitive(starCount)
        if (text != null) p["text"] = JsonPrimitive(text)
        if (textParseMode != null) p["text_parse_mode"] = JsonPrimitive(textParseMode)
        if (textEntities != null) p["text_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), textEntities)
        return api.call("giftPremiumSubscription", p, files, Boolean.serializer())
    }

    /**
     * hideGeneralForumTopic — Use this method to hide the &#39;General&#39; topic in a forum supergroup chat. The bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator rights. The topic will be automatically closed if it was open. Returns True on success.
     * @return Boolean
     */
    suspend fun hideGeneralForumTopic(chatId: ChatId): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        return api.call("hideGeneralForumTopic", p, files, Boolean.serializer())
    }

    /**
     * leaveChat — Use this method for your bot to leave a group, supergroup or channel. Returns True on success.
     * @return Boolean
     */
    suspend fun leaveChat(chatId: ChatId): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        return api.call("leaveChat", p, files, Boolean.serializer())
    }

    /**
     * logOut — Use this method to log out from the cloud Bot API server before launching the bot locally. You must log out the bot before running it locally, otherwise there is no guarantee that the bot will receive updates. After a successful call, you can immediately log in on a local server, but will not be abl
     * @return Boolean
     */
    suspend fun logOut(): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        return api.call("logOut", p, files, Boolean.serializer())
    }

    /**
     * pinChatMessage — Use this method to add a message to the list of pinned messages in a chat. In private chats and channel direct messages chats, all non-service messages can be pinned. Conversely, the bot must be an administrator with the &#39;can_pin_messages&#39; right or the &#39;can_edit_messages&#39; right to pi
     * @return Boolean
     */
    suspend fun pinChatMessage(chatId: ChatId, messageId: Long, businessConnectionId: String? = null, disableNotification: Boolean? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["message_id"] = JsonPrimitive(messageId)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        return api.call("pinChatMessage", p, files, Boolean.serializer())
    }

    /**
     * postStory — Posts a story on behalf of a managed business account. Requires the can_manage_stories business bot right. Returns Story on success.
     * @return Story
     */
    suspend fun postStory(businessConnectionId: String, content: InputStoryContent, activePeriod: Long, caption: String? = null, parseMode: String? = null, captionEntities: List<MessageEntity>? = null, areas: List<StoryArea>? = null, postToChatPage: Boolean? = null, protectContent: Boolean? = null): Story {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        p["content"] = TelegramJson.encodeToJsonElement(InputStoryContent.serializer(), content)
        p["active_period"] = JsonPrimitive(activePeriod)
        if (caption != null) p["caption"] = JsonPrimitive(caption)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (captionEntities != null) p["caption_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), captionEntities)
        if (areas != null) p["areas"] = TelegramJson.encodeToJsonElement(ListSerializer(StoryArea.serializer()), areas)
        if (postToChatPage != null) p["post_to_chat_page"] = JsonPrimitive(postToChatPage)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        return api.call("postStory", p, files, Story.serializer())
    }

    /**
     * promoteChatMember — Use this method to promote or demote a user in a supergroup or a channel. The bot must be an administrator in the chat for this to work and must have the appropriate administrator rights. Pass False for all boolean parameters to demote a user. Returns True on success.
     * @return Boolean
     */
    suspend fun promoteChatMember(chatId: ChatId, userId: Long, isAnonymous: Boolean? = null, canManageChat: Boolean? = null, canDeleteMessages: Boolean? = null, canManageVideoChats: Boolean? = null, canRestrictMembers: Boolean? = null, canPromoteMembers: Boolean? = null, canChangeInfo: Boolean? = null, canInviteUsers: Boolean? = null, canPostStories: Boolean? = null, canEditStories: Boolean? = null, canDeleteStories: Boolean? = null, canPostMessages: Boolean? = null, canEditMessages: Boolean? = null, canPinMessages: Boolean? = null, canManageTopics: Boolean? = null, canManageDirectMessages: Boolean? = null, canManageTags: Boolean? = null, canSendWelcomeMessages: Boolean? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["user_id"] = JsonPrimitive(userId)
        if (isAnonymous != null) p["is_anonymous"] = JsonPrimitive(isAnonymous)
        if (canManageChat != null) p["can_manage_chat"] = JsonPrimitive(canManageChat)
        if (canDeleteMessages != null) p["can_delete_messages"] = JsonPrimitive(canDeleteMessages)
        if (canManageVideoChats != null) p["can_manage_video_chats"] = JsonPrimitive(canManageVideoChats)
        if (canRestrictMembers != null) p["can_restrict_members"] = JsonPrimitive(canRestrictMembers)
        if (canPromoteMembers != null) p["can_promote_members"] = JsonPrimitive(canPromoteMembers)
        if (canChangeInfo != null) p["can_change_info"] = JsonPrimitive(canChangeInfo)
        if (canInviteUsers != null) p["can_invite_users"] = JsonPrimitive(canInviteUsers)
        if (canPostStories != null) p["can_post_stories"] = JsonPrimitive(canPostStories)
        if (canEditStories != null) p["can_edit_stories"] = JsonPrimitive(canEditStories)
        if (canDeleteStories != null) p["can_delete_stories"] = JsonPrimitive(canDeleteStories)
        if (canPostMessages != null) p["can_post_messages"] = JsonPrimitive(canPostMessages)
        if (canEditMessages != null) p["can_edit_messages"] = JsonPrimitive(canEditMessages)
        if (canPinMessages != null) p["can_pin_messages"] = JsonPrimitive(canPinMessages)
        if (canManageTopics != null) p["can_manage_topics"] = JsonPrimitive(canManageTopics)
        if (canManageDirectMessages != null) p["can_manage_direct_messages"] = JsonPrimitive(canManageDirectMessages)
        if (canManageTags != null) p["can_manage_tags"] = JsonPrimitive(canManageTags)
        if (canSendWelcomeMessages != null) p["can_send_welcome_messages"] = JsonPrimitive(canSendWelcomeMessages)
        return api.call("promoteChatMember", p, files, Boolean.serializer())
    }

    /**
     * readBusinessMessage — Marks incoming message as read on behalf of a business account. Requires the can_read_messages business bot right. Returns True on success.
     * @return Boolean
     */
    suspend fun readBusinessMessage(businessConnectionId: String, chatId: Long, messageId: Long): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        p["chat_id"] = JsonPrimitive(chatId)
        p["message_id"] = JsonPrimitive(messageId)
        return api.call("readBusinessMessage", p, files, Boolean.serializer())
    }

    /**
     * refundStarPayment — Refunds a successful payment in Telegram Stars . Returns True on success.
     * @return Boolean
     */
    suspend fun refundStarPayment(userId: Long, telegramPaymentChargeId: String): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        p["telegram_payment_charge_id"] = JsonPrimitive(telegramPaymentChargeId)
        return api.call("refundStarPayment", p, files, Boolean.serializer())
    }

    /**
     * removeBusinessAccountProfilePhoto — Removes the current profile photo of a managed business account. Requires the can_edit_profile_photo business bot right. Returns True on success.
     * @return Boolean
     */
    suspend fun removeBusinessAccountProfilePhoto(businessConnectionId: String, isPublic: Boolean? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (isPublic != null) p["is_public"] = JsonPrimitive(isPublic)
        return api.call("removeBusinessAccountProfilePhoto", p, files, Boolean.serializer())
    }

    /**
     * removeChatVerification — Removes verification from a chat that is currently verified on behalf of the organization represented by the bot. Returns True on success.
     * @return Boolean
     */
    suspend fun removeChatVerification(chatId: ChatId): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        return api.call("removeChatVerification", p, files, Boolean.serializer())
    }

    /**
     * removeMyProfilePhoto — Removes the profile photo of the bot. Requires no parameters. Returns True on success.
     * @return Boolean
     */
    suspend fun removeMyProfilePhoto(): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        return api.call("removeMyProfilePhoto", p, files, Boolean.serializer())
    }

    /**
     * removeUserVerification — Removes verification from a user who is currently verified on behalf of the organization represented by the bot. Returns True on success.
     * @return Boolean
     */
    suspend fun removeUserVerification(userId: Long): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        return api.call("removeUserVerification", p, files, Boolean.serializer())
    }

    /**
     * reopenForumTopic — Use this method to reopen a closed topic in a forum supergroup chat. The bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator rights, unless it is the creator of the topic. Returns True on success.
     * @return Boolean
     */
    suspend fun reopenForumTopic(chatId: ChatId, messageThreadId: Long): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["message_thread_id"] = JsonPrimitive(messageThreadId)
        return api.call("reopenForumTopic", p, files, Boolean.serializer())
    }

    /**
     * reopenGeneralForumTopic — Use this method to reopen a closed &#39;General&#39; topic in a forum supergroup chat. The bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator rights. The topic will be automatically unhidden if it was hidden. Returns True on success.
     * @return Boolean
     */
    suspend fun reopenGeneralForumTopic(chatId: ChatId): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        return api.call("reopenGeneralForumTopic", p, files, Boolean.serializer())
    }

    /**
     * replaceManagedBotToken — Use this method to revoke the current token of a managed bot and generate a new one. Returns the new token as String on success.
     * @return String
     */
    suspend fun replaceManagedBotToken(userId: Long): String {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        return api.call("replaceManagedBotToken", p, files, String.serializer())
    }

    /**
     * replaceStickerInSet — Use this method to replace an existing sticker in a sticker set with a new one. The method is equivalent to calling deleteStickerFromSet , then addStickerToSet , then setStickerPositionInSet . Returns True on success.
     * @return Boolean
     */
    suspend fun replaceStickerInSet(userId: Long, name: String, oldSticker: String, sticker: InputSticker): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        p["name"] = JsonPrimitive(name)
        p["old_sticker"] = JsonPrimitive(oldSticker)
        p["sticker"] = TelegramJson.encodeToJsonElement(InputSticker.serializer(), sticker)
        return api.call("replaceStickerInSet", p, files, Boolean.serializer())
    }

    /**
     * repostStory — Reposts a story on behalf of a business account from another business account. Both business accounts must be managed by the same bot, and the story on the source account must have been posted (or reposted) by the bot. Requires the can_manage_stories business bot right for both business accounts. Re
     * @return Story
     */
    suspend fun repostStory(businessConnectionId: String, fromChatId: Long, fromStoryId: Long, activePeriod: Long, postToChatPage: Boolean? = null, protectContent: Boolean? = null): Story {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        p["from_chat_id"] = JsonPrimitive(fromChatId)
        p["from_story_id"] = JsonPrimitive(fromStoryId)
        p["active_period"] = JsonPrimitive(activePeriod)
        if (postToChatPage != null) p["post_to_chat_page"] = JsonPrimitive(postToChatPage)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        return api.call("repostStory", p, files, Story.serializer())
    }

    /**
     * restrictChatMember — Use this method to restrict a user in a supergroup. The bot must be an administrator in the supergroup for this to work and must have the appropriate administrator rights. Pass True for all permissions to lift restrictions from a user. Returns True on success.
     * @return Boolean
     */
    suspend fun restrictChatMember(chatId: ChatId, userId: Long, permissions: ChatPermissions, useIndependentChatPermissions: Boolean? = null, untilDate: Long? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["user_id"] = JsonPrimitive(userId)
        p["permissions"] = TelegramJson.encodeToJsonElement(ChatPermissions.serializer(), permissions)
        if (useIndependentChatPermissions != null) p["use_independent_chat_permissions"] = JsonPrimitive(useIndependentChatPermissions)
        if (untilDate != null) p["until_date"] = JsonPrimitive(untilDate)
        return api.call("restrictChatMember", p, files, Boolean.serializer())
    }

    /**
     * revokeChatInviteLink — Use this method to revoke an invite link created by the bot. If the primary link is revoked, a new link is automatically generated. The bot must be an administrator in the chat for this to work and must have the appropriate administrator rights. Returns the revoked invite link as ChatInviteLink obje
     * @return ChatInviteLink
     */
    suspend fun revokeChatInviteLink(chatId: ChatId, inviteLink: String): ChatInviteLink {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["invite_link"] = JsonPrimitive(inviteLink)
        return api.call("revokeChatInviteLink", p, files, ChatInviteLink.serializer())
    }

    /**
     * savePreparedInlineMessage — Stores a message that can be sent by a user of a Mini App. Returns a PreparedInlineMessage object.
     * @return PreparedInlineMessage
     */
    suspend fun savePreparedInlineMessage(userId: Long, result: InlineQueryResult, allowUserChats: Boolean? = null, allowBotChats: Boolean? = null, allowGroupChats: Boolean? = null, allowChannelChats: Boolean? = null): PreparedInlineMessage {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        p["result"] = TelegramJson.encodeToJsonElement(InlineQueryResult.serializer(), result)
        if (allowUserChats != null) p["allow_user_chats"] = JsonPrimitive(allowUserChats)
        if (allowBotChats != null) p["allow_bot_chats"] = JsonPrimitive(allowBotChats)
        if (allowGroupChats != null) p["allow_group_chats"] = JsonPrimitive(allowGroupChats)
        if (allowChannelChats != null) p["allow_channel_chats"] = JsonPrimitive(allowChannelChats)
        return api.call("savePreparedInlineMessage", p, files, PreparedInlineMessage.serializer())
    }

    /**
     * savePreparedKeyboardButton — Stores a keyboard button that can be used by a user within a Mini App. Returns a PreparedKeyboardButton object.
     * @return PreparedKeyboardButton
     */
    suspend fun savePreparedKeyboardButton(userId: Long, button: KeyboardButton): PreparedKeyboardButton {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        p["button"] = TelegramJson.encodeToJsonElement(KeyboardButton.serializer(), button)
        return api.call("savePreparedKeyboardButton", p, files, PreparedKeyboardButton.serializer())
    }

    /**
     * sendAnimation — Use this method to send animation files (GIF or H.264/MPEG-4 AVC video without sound). On success, the sent Message is returned. Bots can currently send animation files of up to 50 MB in size, this limit may be changed in the future.
     * @return Message
     */
    suspend fun sendAnimation(chatId: ChatId, animation: InputFile, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, ephemeralMessageParameters: EphemeralMessageParameters? = null, duration: Long? = null, width: Long? = null, height: Long? = null, thumbnail: InputFile? = null, caption: String? = null, parseMode: String? = null, captionEntities: List<MessageEntity>? = null, showCaptionAboveMedia: Boolean? = null, hasSpoiler: Boolean? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["animation"] = api.inputFileToJson(animation, files)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (ephemeralMessageParameters != null) p["ephemeral_message_parameters"] = TelegramJson.encodeToJsonElement(EphemeralMessageParameters.serializer(), ephemeralMessageParameters)
        if (duration != null) p["duration"] = JsonPrimitive(duration)
        if (width != null) p["width"] = JsonPrimitive(width)
        if (height != null) p["height"] = JsonPrimitive(height)
        if (thumbnail != null) p["thumbnail"] = api.inputFileToJson(thumbnail, files)
        if (caption != null) p["caption"] = JsonPrimitive(caption)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (captionEntities != null) p["caption_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), captionEntities)
        if (showCaptionAboveMedia != null) p["show_caption_above_media"] = JsonPrimitive(showCaptionAboveMedia)
        if (hasSpoiler != null) p["has_spoiler"] = JsonPrimitive(hasSpoiler)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendAnimation", p, files, Message.serializer())
    }

    /**
     * sendAudio — Use this method to send audio files, if you want Telegram clients to display them in the music player. Your audio must be in the .MP3 or .M4A format. On success, the sent Message is returned. Bots can currently send audio files of up to 50 MB in size, this limit may be changed in the future. For sen
     * @return Message
     */
    suspend fun sendAudio(chatId: ChatId, audio: InputFile, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, ephemeralMessageParameters: EphemeralMessageParameters? = null, caption: String? = null, parseMode: String? = null, captionEntities: List<MessageEntity>? = null, duration: Long? = null, performer: String? = null, title: String? = null, thumbnail: InputFile? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["audio"] = api.inputFileToJson(audio, files)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (ephemeralMessageParameters != null) p["ephemeral_message_parameters"] = TelegramJson.encodeToJsonElement(EphemeralMessageParameters.serializer(), ephemeralMessageParameters)
        if (caption != null) p["caption"] = JsonPrimitive(caption)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (captionEntities != null) p["caption_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), captionEntities)
        if (duration != null) p["duration"] = JsonPrimitive(duration)
        if (performer != null) p["performer"] = JsonPrimitive(performer)
        if (title != null) p["title"] = JsonPrimitive(title)
        if (thumbnail != null) p["thumbnail"] = api.inputFileToJson(thumbnail, files)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendAudio", p, files, Message.serializer())
    }

    /**
     * sendChatAction — Use this method when you need to tell the user that something is happening on the bot&#39;s side. The status is set for 5 seconds or less (when a message arrives from your bot, Telegram clients clear its typing status). Returns True on success. Example: The ImageBot needs some time to process a requ
     * @return Boolean
     */
    suspend fun sendChatAction(chatId: ChatId, action: String, businessConnectionId: String? = null, messageThreadId: Long? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["action"] = JsonPrimitive(action)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        return api.call("sendChatAction", p, files, Boolean.serializer())
    }

    /**
     * sendChatJoinRequestWebApp — Use this method to process a received chat join request query by showing a Mini App to the user before deciding the outcome. Call answerChatJoinRequestQuery to resolve the join request query based on the user interaction with the Mini App. Returns True on success.
     * @return Boolean
     */
    suspend fun sendChatJoinRequestWebApp(chatJoinRequestQueryId: String, webAppUrl: String): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_join_request_query_id"] = JsonPrimitive(chatJoinRequestQueryId)
        p["web_app_url"] = JsonPrimitive(webAppUrl)
        return api.call("sendChatJoinRequestWebApp", p, files, Boolean.serializer())
    }

    /**
     * sendChecklist — Use this method to send a checklist on behalf of a connected business account. On success, the sent Message is returned.
     * @return Message
     */
    suspend fun sendChecklist(businessConnectionId: String, chatId: ChatId, checklist: InputChecklist, disableNotification: Boolean? = null, protectContent: Boolean? = null, messageEffectId: String? = null, replyParameters: ReplyParameters? = null, replyMarkup: InlineKeyboardMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["checklist"] = TelegramJson.encodeToJsonElement(InputChecklist.serializer(), checklist)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(InlineKeyboardMarkup.serializer(), replyMarkup)
        return api.call("sendChecklist", p, files, Message.serializer())
    }

    /**
     * sendContact — Use this method to send phone contacts. On success, the sent Message is returned.
     * @return Message
     */
    suspend fun sendContact(chatId: ChatId, phoneNumber: String, firstName: String, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, ephemeralMessageParameters: EphemeralMessageParameters? = null, lastName: String? = null, vcard: String? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["phone_number"] = JsonPrimitive(phoneNumber)
        p["first_name"] = JsonPrimitive(firstName)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (ephemeralMessageParameters != null) p["ephemeral_message_parameters"] = TelegramJson.encodeToJsonElement(EphemeralMessageParameters.serializer(), ephemeralMessageParameters)
        if (lastName != null) p["last_name"] = JsonPrimitive(lastName)
        if (vcard != null) p["vcard"] = JsonPrimitive(vcard)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendContact", p, files, Message.serializer())
    }

    /**
     * sendDice — Use this method to send an animated emoji that will display a random value. On success, the sent Message is returned.
     * @return Message
     */
    suspend fun sendDice(chatId: ChatId, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, emoji: String? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (emoji != null) p["emoji"] = JsonPrimitive(emoji)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendDice", p, files, Message.serializer())
    }

    /**
     * sendDocument — Use this method to send general files. On success, the sent Message is returned. Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
     * @return Message
     */
    suspend fun sendDocument(chatId: ChatId, document: InputFile, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, ephemeralMessageParameters: EphemeralMessageParameters? = null, thumbnail: InputFile? = null, caption: String? = null, parseMode: String? = null, captionEntities: List<MessageEntity>? = null, disableContentTypeDetection: Boolean? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["document"] = api.inputFileToJson(document, files)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (ephemeralMessageParameters != null) p["ephemeral_message_parameters"] = TelegramJson.encodeToJsonElement(EphemeralMessageParameters.serializer(), ephemeralMessageParameters)
        if (thumbnail != null) p["thumbnail"] = api.inputFileToJson(thumbnail, files)
        if (caption != null) p["caption"] = JsonPrimitive(caption)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (captionEntities != null) p["caption_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), captionEntities)
        if (disableContentTypeDetection != null) p["disable_content_type_detection"] = JsonPrimitive(disableContentTypeDetection)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendDocument", p, files, Message.serializer())
    }

    /**
     * sendGame — Use this method to send a game. On success, the sent Message is returned.
     * @return Message
     */
    suspend fun sendGame(chatId: ChatId, gameShortName: String, businessConnectionId: String? = null, messageThreadId: Long? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, replyParameters: ReplyParameters? = null, replyMarkup: InlineKeyboardMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["game_short_name"] = JsonPrimitive(gameShortName)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(InlineKeyboardMarkup.serializer(), replyMarkup)
        return api.call("sendGame", p, files, Message.serializer())
    }

    /**
     * sendGift — Sends a gift to the given user or channel chat. The gift can&#39;t be converted to Telegram Stars by the receiver. Returns True on success.
     * @return Boolean
     */
    suspend fun sendGift(giftId: String, userId: Long? = null, chatId: ChatId? = null, payForUpgrade: Boolean? = null, text: String? = null, textParseMode: String? = null, textEntities: List<MessageEntity>? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["gift_id"] = JsonPrimitive(giftId)
        if (userId != null) p["user_id"] = JsonPrimitive(userId)
        if (chatId != null) p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        if (payForUpgrade != null) p["pay_for_upgrade"] = JsonPrimitive(payForUpgrade)
        if (text != null) p["text"] = JsonPrimitive(text)
        if (textParseMode != null) p["text_parse_mode"] = JsonPrimitive(textParseMode)
        if (textEntities != null) p["text_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), textEntities)
        return api.call("sendGift", p, files, Boolean.serializer())
    }

    /**
     * sendInvoice — Use this method to send invoices. On success, the sent Message is returned.
     * @return Message
     */
    suspend fun sendInvoice(chatId: ChatId, title: String, description: String, payload: String, currency: String, prices: List<LabeledPrice>, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, providerToken: String? = null, maxTipAmount: Long? = null, suggestedTipAmounts: List<Long>? = null, startParameter: String? = null, providerData: String? = null, photoUrl: String? = null, photoSize: Long? = null, photoWidth: Long? = null, photoHeight: Long? = null, needName: Boolean? = null, needPhoneNumber: Boolean? = null, needEmail: Boolean? = null, needShippingAddress: Boolean? = null, sendPhoneNumberToProvider: Boolean? = null, sendEmailToProvider: Boolean? = null, isFlexible: Boolean? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: InlineKeyboardMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["title"] = JsonPrimitive(title)
        p["description"] = JsonPrimitive(description)
        p["payload"] = JsonPrimitive(payload)
        p["currency"] = JsonPrimitive(currency)
        p["prices"] = TelegramJson.encodeToJsonElement(ListSerializer(LabeledPrice.serializer()), prices)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (providerToken != null) p["provider_token"] = JsonPrimitive(providerToken)
        if (maxTipAmount != null) p["max_tip_amount"] = JsonPrimitive(maxTipAmount)
        if (suggestedTipAmounts != null) p["suggested_tip_amounts"] = TelegramJson.encodeToJsonElement(ListSerializer(Long.serializer()), suggestedTipAmounts)
        if (startParameter != null) p["start_parameter"] = JsonPrimitive(startParameter)
        if (providerData != null) p["provider_data"] = JsonPrimitive(providerData)
        if (photoUrl != null) p["photo_url"] = JsonPrimitive(photoUrl)
        if (photoSize != null) p["photo_size"] = JsonPrimitive(photoSize)
        if (photoWidth != null) p["photo_width"] = JsonPrimitive(photoWidth)
        if (photoHeight != null) p["photo_height"] = JsonPrimitive(photoHeight)
        if (needName != null) p["need_name"] = JsonPrimitive(needName)
        if (needPhoneNumber != null) p["need_phone_number"] = JsonPrimitive(needPhoneNumber)
        if (needEmail != null) p["need_email"] = JsonPrimitive(needEmail)
        if (needShippingAddress != null) p["need_shipping_address"] = JsonPrimitive(needShippingAddress)
        if (sendPhoneNumberToProvider != null) p["send_phone_number_to_provider"] = JsonPrimitive(sendPhoneNumberToProvider)
        if (sendEmailToProvider != null) p["send_email_to_provider"] = JsonPrimitive(sendEmailToProvider)
        if (isFlexible != null) p["is_flexible"] = JsonPrimitive(isFlexible)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(InlineKeyboardMarkup.serializer(), replyMarkup)
        return api.call("sendInvoice", p, files, Message.serializer())
    }

    /**
     * sendLivePhoto — Use this method to send live photos. On success, the sent Message is returned.
     * @return Message
     */
    suspend fun sendLivePhoto(chatId: ChatId, livePhoto: InputFile, photo: InputFile, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, ephemeralMessageParameters: EphemeralMessageParameters? = null, caption: String? = null, parseMode: String? = null, captionEntities: List<MessageEntity>? = null, showCaptionAboveMedia: Boolean? = null, hasSpoiler: Boolean? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["live_photo"] = api.inputFileToJson(livePhoto, files)
        p["photo"] = api.inputFileToJson(photo, files)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (ephemeralMessageParameters != null) p["ephemeral_message_parameters"] = TelegramJson.encodeToJsonElement(EphemeralMessageParameters.serializer(), ephemeralMessageParameters)
        if (caption != null) p["caption"] = JsonPrimitive(caption)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (captionEntities != null) p["caption_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), captionEntities)
        if (showCaptionAboveMedia != null) p["show_caption_above_media"] = JsonPrimitive(showCaptionAboveMedia)
        if (hasSpoiler != null) p["has_spoiler"] = JsonPrimitive(hasSpoiler)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendLivePhoto", p, files, Message.serializer())
    }

    /**
     * sendLocation — Use this method to send point on the map. On success, the sent Message is returned.
     * @return Message
     */
    suspend fun sendLocation(chatId: ChatId, latitude: Double, longitude: Double, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, ephemeralMessageParameters: EphemeralMessageParameters? = null, horizontalAccuracy: Double? = null, livePeriod: Long? = null, heading: Long? = null, proximityAlertRadius: Long? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["latitude"] = JsonPrimitive(latitude)
        p["longitude"] = JsonPrimitive(longitude)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (ephemeralMessageParameters != null) p["ephemeral_message_parameters"] = TelegramJson.encodeToJsonElement(EphemeralMessageParameters.serializer(), ephemeralMessageParameters)
        if (horizontalAccuracy != null) p["horizontal_accuracy"] = JsonPrimitive(horizontalAccuracy)
        if (livePeriod != null) p["live_period"] = JsonPrimitive(livePeriod)
        if (heading != null) p["heading"] = JsonPrimitive(heading)
        if (proximityAlertRadius != null) p["proximity_alert_radius"] = JsonPrimitive(proximityAlertRadius)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendLocation", p, files, Message.serializer())
    }

    /**
     * sendMediaGroup — Use this method to send a group of photos, live photos, videos, documents or audios as an album. Documents and audio files can be only grouped in an album with messages of the same type. On success, an Array of Message objects that were sent is returned.
     * @return List<Message>
     */
    suspend fun sendMediaGroup(chatId: ChatId, media: List<InputMedia>, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, replyParameters: ReplyParameters? = null): List<Message> {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["media"] = TelegramJson.encodeToJsonElement(ListSerializer(InputMedia.serializer()), media)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        return api.call("sendMediaGroup", p, files, ListSerializer(Message.serializer()))
    }

    /**
     * sendMessage — Use this method to send text messages. On success, the sent Message is returned.
     * @return Message
     */
    suspend fun sendMessage(chatId: ChatId, text: String, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, ephemeralMessageParameters: EphemeralMessageParameters? = null, parseMode: String? = null, entities: List<MessageEntity>? = null, linkPreviewOptions: LinkPreviewOptions? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["text"] = JsonPrimitive(text)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (ephemeralMessageParameters != null) p["ephemeral_message_parameters"] = TelegramJson.encodeToJsonElement(EphemeralMessageParameters.serializer(), ephemeralMessageParameters)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (entities != null) p["entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), entities)
        if (linkPreviewOptions != null) p["link_preview_options"] = TelegramJson.encodeToJsonElement(LinkPreviewOptions.serializer(), linkPreviewOptions)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendMessage", p, files, Message.serializer())
    }

    /**
     * sendMessageDraft — Use this method to stream a partial message to a user while the message is being generated. Note that the streamed draft is ephemeral and acts as a temporary 30-second preview - once the output is finalized, you must call sendMessage with the complete message to persist it in the user&#39;s chat. Re
     * @return Boolean
     */
    suspend fun sendMessageDraft(chatId: Long, draftId: Long, messageThreadId: Long? = null, text: String? = null, parseMode: String? = null, entities: List<MessageEntity>? = null, canStop: Boolean? = null, keepOnStop: Boolean? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = JsonPrimitive(chatId)
        p["draft_id"] = JsonPrimitive(draftId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (text != null) p["text"] = JsonPrimitive(text)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (entities != null) p["entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), entities)
        if (canStop != null) p["can_stop"] = JsonPrimitive(canStop)
        if (keepOnStop != null) p["keep_on_stop"] = JsonPrimitive(keepOnStop)
        return api.call("sendMessageDraft", p, files, Boolean.serializer())
    }

    /**
     * sendPaidMedia — Use this method to send paid media. On success, the sent Message is returned.
     * @return Message
     */
    suspend fun sendPaidMedia(chatId: ChatId, starCount: Long, media: List<InputPaidMedia>, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, payload: String? = null, caption: String? = null, parseMode: String? = null, captionEntities: List<MessageEntity>? = null, showCaptionAboveMedia: Boolean? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["star_count"] = JsonPrimitive(starCount)
        p["media"] = TelegramJson.encodeToJsonElement(ListSerializer(InputPaidMedia.serializer()), media)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (payload != null) p["payload"] = JsonPrimitive(payload)
        if (caption != null) p["caption"] = JsonPrimitive(caption)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (captionEntities != null) p["caption_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), captionEntities)
        if (showCaptionAboveMedia != null) p["show_caption_above_media"] = JsonPrimitive(showCaptionAboveMedia)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendPaidMedia", p, files, Message.serializer())
    }

    /**
     * sendPhoto — Use this method to send photos. On success, the sent Message is returned.
     * @return Message
     */
    suspend fun sendPhoto(chatId: ChatId, photo: InputFile, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, ephemeralMessageParameters: EphemeralMessageParameters? = null, caption: String? = null, parseMode: String? = null, captionEntities: List<MessageEntity>? = null, showCaptionAboveMedia: Boolean? = null, hasSpoiler: Boolean? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["photo"] = api.inputFileToJson(photo, files)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (ephemeralMessageParameters != null) p["ephemeral_message_parameters"] = TelegramJson.encodeToJsonElement(EphemeralMessageParameters.serializer(), ephemeralMessageParameters)
        if (caption != null) p["caption"] = JsonPrimitive(caption)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (captionEntities != null) p["caption_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), captionEntities)
        if (showCaptionAboveMedia != null) p["show_caption_above_media"] = JsonPrimitive(showCaptionAboveMedia)
        if (hasSpoiler != null) p["has_spoiler"] = JsonPrimitive(hasSpoiler)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendPhoto", p, files, Message.serializer())
    }

    /**
     * sendPoll — Use this method to send a native poll. On success, the sent Message is returned.
     * @return Message
     */
    suspend fun sendPoll(chatId: ChatId, question: String, options: List<InputPollOption>, businessConnectionId: String? = null, messageThreadId: Long? = null, questionParseMode: String? = null, questionEntities: List<MessageEntity>? = null, isAnonymous: Boolean? = null, type: String? = null, allowsMultipleAnswers: Boolean? = null, allowsRevoting: Boolean? = null, shuffleOptions: Boolean? = null, allowAddingOptions: Boolean? = null, hideResultsUntilCloses: Boolean? = null, membersOnly: Boolean? = null, countryCodes: List<String>? = null, correctOptionIds: List<Long>? = null, explanation: String? = null, explanationParseMode: String? = null, explanationEntities: List<MessageEntity>? = null, explanationMedia: InputPollMedia? = null, openPeriod: Long? = null, closeDate: Long? = null, isClosed: Boolean? = null, description: String? = null, descriptionParseMode: String? = null, descriptionEntities: List<MessageEntity>? = null, media: InputPollMedia? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["question"] = JsonPrimitive(question)
        p["options"] = TelegramJson.encodeToJsonElement(ListSerializer(InputPollOption.serializer()), options)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (questionParseMode != null) p["question_parse_mode"] = JsonPrimitive(questionParseMode)
        if (questionEntities != null) p["question_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), questionEntities)
        if (isAnonymous != null) p["is_anonymous"] = JsonPrimitive(isAnonymous)
        if (type != null) p["type"] = JsonPrimitive(type)
        if (allowsMultipleAnswers != null) p["allows_multiple_answers"] = JsonPrimitive(allowsMultipleAnswers)
        if (allowsRevoting != null) p["allows_revoting"] = JsonPrimitive(allowsRevoting)
        if (shuffleOptions != null) p["shuffle_options"] = JsonPrimitive(shuffleOptions)
        if (allowAddingOptions != null) p["allow_adding_options"] = JsonPrimitive(allowAddingOptions)
        if (hideResultsUntilCloses != null) p["hide_results_until_closes"] = JsonPrimitive(hideResultsUntilCloses)
        if (membersOnly != null) p["members_only"] = JsonPrimitive(membersOnly)
        if (countryCodes != null) p["country_codes"] = TelegramJson.encodeToJsonElement(ListSerializer(String.serializer()), countryCodes)
        if (correctOptionIds != null) p["correct_option_ids"] = TelegramJson.encodeToJsonElement(ListSerializer(Long.serializer()), correctOptionIds)
        if (explanation != null) p["explanation"] = JsonPrimitive(explanation)
        if (explanationParseMode != null) p["explanation_parse_mode"] = JsonPrimitive(explanationParseMode)
        if (explanationEntities != null) p["explanation_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), explanationEntities)
        if (explanationMedia != null) p["explanation_media"] = TelegramJson.encodeToJsonElement(InputPollMedia.serializer(), explanationMedia)
        if (openPeriod != null) p["open_period"] = JsonPrimitive(openPeriod)
        if (closeDate != null) p["close_date"] = JsonPrimitive(closeDate)
        if (isClosed != null) p["is_closed"] = JsonPrimitive(isClosed)
        if (description != null) p["description"] = JsonPrimitive(description)
        if (descriptionParseMode != null) p["description_parse_mode"] = JsonPrimitive(descriptionParseMode)
        if (descriptionEntities != null) p["description_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), descriptionEntities)
        if (media != null) p["media"] = TelegramJson.encodeToJsonElement(InputPollMedia.serializer(), media)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendPoll", p, files, Message.serializer())
    }

    /**
     * sendRichMessage — Use this method to send rich messages. If the message contains a block with a media element, then the bot must have the right to send the media to the chat. On success, the sent Message is returned.
     * @return Message
     */
    suspend fun sendRichMessage(chatId: ChatId, richMessage: InputRichMessage, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, ephemeralMessageParameters: EphemeralMessageParameters? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["rich_message"] = TelegramJson.encodeToJsonElement(InputRichMessage.serializer(), richMessage)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (ephemeralMessageParameters != null) p["ephemeral_message_parameters"] = TelegramJson.encodeToJsonElement(EphemeralMessageParameters.serializer(), ephemeralMessageParameters)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendRichMessage", p, files, Message.serializer())
    }

    /**
     * sendRichMessageDraft — Use this method to stream a partial rich message to a user while the message is being generated. Note that the streamed draft is ephemeral and acts as a temporary 30-second preview - once the output is finalized, you must call sendRichMessage with the complete message to persist it in the user&#39;s
     * @return Boolean
     */
    suspend fun sendRichMessageDraft(chatId: Long, draftId: Long, richMessage: InputRichMessage, messageThreadId: Long? = null, canStop: Boolean? = null, keepOnStop: Boolean? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = JsonPrimitive(chatId)
        p["draft_id"] = JsonPrimitive(draftId)
        p["rich_message"] = TelegramJson.encodeToJsonElement(InputRichMessage.serializer(), richMessage)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (canStop != null) p["can_stop"] = JsonPrimitive(canStop)
        if (keepOnStop != null) p["keep_on_stop"] = JsonPrimitive(keepOnStop)
        return api.call("sendRichMessageDraft", p, files, Boolean.serializer())
    }

    /**
     * sendSticker — Use this method to send static .WEBP, animated .TGS, or video .WEBM stickers. On success, the sent Message is returned.
     * @return Message
     */
    suspend fun sendSticker(chatId: ChatId, sticker: InputFile, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, ephemeralMessageParameters: EphemeralMessageParameters? = null, emoji: String? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["sticker"] = api.inputFileToJson(sticker, files)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (ephemeralMessageParameters != null) p["ephemeral_message_parameters"] = TelegramJson.encodeToJsonElement(EphemeralMessageParameters.serializer(), ephemeralMessageParameters)
        if (emoji != null) p["emoji"] = JsonPrimitive(emoji)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendSticker", p, files, Message.serializer())
    }

    /**
     * sendVenue — Use this method to send information about a venue. On success, the sent Message is returned.
     * @return Message
     */
    suspend fun sendVenue(chatId: ChatId, latitude: Double, longitude: Double, title: String, address: String, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, ephemeralMessageParameters: EphemeralMessageParameters? = null, foursquareId: String? = null, foursquareType: String? = null, googlePlaceId: String? = null, googlePlaceType: String? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["latitude"] = JsonPrimitive(latitude)
        p["longitude"] = JsonPrimitive(longitude)
        p["title"] = JsonPrimitive(title)
        p["address"] = JsonPrimitive(address)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (ephemeralMessageParameters != null) p["ephemeral_message_parameters"] = TelegramJson.encodeToJsonElement(EphemeralMessageParameters.serializer(), ephemeralMessageParameters)
        if (foursquareId != null) p["foursquare_id"] = JsonPrimitive(foursquareId)
        if (foursquareType != null) p["foursquare_type"] = JsonPrimitive(foursquareType)
        if (googlePlaceId != null) p["google_place_id"] = JsonPrimitive(googlePlaceId)
        if (googlePlaceType != null) p["google_place_type"] = JsonPrimitive(googlePlaceType)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendVenue", p, files, Message.serializer())
    }

    /**
     * sendVideo — Use this method to send video files, Telegram clients support MPEG4 videos (other formats may be sent as Document ). On success, the sent Message is returned. Bots can currently send video files of up to 50 MB in size, this limit may be changed in the future.
     * @return Message
     */
    suspend fun sendVideo(chatId: ChatId, video: InputFile, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, ephemeralMessageParameters: EphemeralMessageParameters? = null, duration: Long? = null, width: Long? = null, height: Long? = null, thumbnail: InputFile? = null, cover: InputFile? = null, startTimestamp: Long? = null, caption: String? = null, parseMode: String? = null, captionEntities: List<MessageEntity>? = null, showCaptionAboveMedia: Boolean? = null, hasSpoiler: Boolean? = null, supportsStreaming: Boolean? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["video"] = api.inputFileToJson(video, files)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (ephemeralMessageParameters != null) p["ephemeral_message_parameters"] = TelegramJson.encodeToJsonElement(EphemeralMessageParameters.serializer(), ephemeralMessageParameters)
        if (duration != null) p["duration"] = JsonPrimitive(duration)
        if (width != null) p["width"] = JsonPrimitive(width)
        if (height != null) p["height"] = JsonPrimitive(height)
        if (thumbnail != null) p["thumbnail"] = api.inputFileToJson(thumbnail, files)
        if (cover != null) p["cover"] = api.inputFileToJson(cover, files)
        if (startTimestamp != null) p["start_timestamp"] = JsonPrimitive(startTimestamp)
        if (caption != null) p["caption"] = JsonPrimitive(caption)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (captionEntities != null) p["caption_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), captionEntities)
        if (showCaptionAboveMedia != null) p["show_caption_above_media"] = JsonPrimitive(showCaptionAboveMedia)
        if (hasSpoiler != null) p["has_spoiler"] = JsonPrimitive(hasSpoiler)
        if (supportsStreaming != null) p["supports_streaming"] = JsonPrimitive(supportsStreaming)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendVideo", p, files, Message.serializer())
    }

    /**
     * sendVideoNote — Use this method to send a rounded square MPEG4 video of up to 1 minute long. On success, the sent Message is returned.
     * @return Message
     */
    suspend fun sendVideoNote(chatId: ChatId, videoNote: InputFile, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, ephemeralMessageParameters: EphemeralMessageParameters? = null, duration: Long? = null, length: Long? = null, thumbnail: InputFile? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["video_note"] = api.inputFileToJson(videoNote, files)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (ephemeralMessageParameters != null) p["ephemeral_message_parameters"] = TelegramJson.encodeToJsonElement(EphemeralMessageParameters.serializer(), ephemeralMessageParameters)
        if (duration != null) p["duration"] = JsonPrimitive(duration)
        if (length != null) p["length"] = JsonPrimitive(length)
        if (thumbnail != null) p["thumbnail"] = api.inputFileToJson(thumbnail, files)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendVideoNote", p, files, Message.serializer())
    }

    /**
     * sendVoice — Use this method to send audio files, if you want Telegram clients to display the file as a playable voice message. For this to work, your audio must be in an .OGG file encoded with OPUS, or in .MP3 format, or in .M4A format (other formats may be sent as Audio or Document ). On success, the sent Mess
     * @return Message
     */
    suspend fun sendVoice(chatId: ChatId, voice: InputFile, businessConnectionId: String? = null, messageThreadId: Long? = null, directMessagesTopicId: Long? = null, ephemeralMessageParameters: EphemeralMessageParameters? = null, caption: String? = null, parseMode: String? = null, captionEntities: List<MessageEntity>? = null, duration: Long? = null, disableNotification: Boolean? = null, protectContent: Boolean? = null, allowPaidBroadcast: Boolean? = null, messageEffectId: String? = null, suggestedPostParameters: SuggestedPostParameters? = null, replyParameters: ReplyParameters? = null, replyMarkup: ReplyMarkup? = null): Message {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["voice"] = api.inputFileToJson(voice, files)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageThreadId != null) p["message_thread_id"] = JsonPrimitive(messageThreadId)
        if (directMessagesTopicId != null) p["direct_messages_topic_id"] = JsonPrimitive(directMessagesTopicId)
        if (ephemeralMessageParameters != null) p["ephemeral_message_parameters"] = TelegramJson.encodeToJsonElement(EphemeralMessageParameters.serializer(), ephemeralMessageParameters)
        if (caption != null) p["caption"] = JsonPrimitive(caption)
        if (parseMode != null) p["parse_mode"] = JsonPrimitive(parseMode)
        if (captionEntities != null) p["caption_entities"] = TelegramJson.encodeToJsonElement(ListSerializer(MessageEntity.serializer()), captionEntities)
        if (duration != null) p["duration"] = JsonPrimitive(duration)
        if (disableNotification != null) p["disable_notification"] = JsonPrimitive(disableNotification)
        if (protectContent != null) p["protect_content"] = JsonPrimitive(protectContent)
        if (allowPaidBroadcast != null) p["allow_paid_broadcast"] = JsonPrimitive(allowPaidBroadcast)
        if (messageEffectId != null) p["message_effect_id"] = JsonPrimitive(messageEffectId)
        if (suggestedPostParameters != null) p["suggested_post_parameters"] = TelegramJson.encodeToJsonElement(SuggestedPostParameters.serializer(), suggestedPostParameters)
        if (replyParameters != null) p["reply_parameters"] = TelegramJson.encodeToJsonElement(ReplyParameters.serializer(), replyParameters)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), replyMarkup)
        return api.call("sendVoice", p, files, Message.serializer())
    }

    /**
     * setBusinessAccountBio — Changes the bio of a managed business account. Requires the can_change_bio business bot right. Returns True on success.
     * @return Boolean
     */
    suspend fun setBusinessAccountBio(businessConnectionId: String, bio: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (bio != null) p["bio"] = JsonPrimitive(bio)
        return api.call("setBusinessAccountBio", p, files, Boolean.serializer())
    }

    /**
     * setBusinessAccountGiftSettings — Changes the privacy settings pertaining to incoming gifts in a managed business account. Requires the can_change_gift_settings business bot right. Returns True on success.
     * @return Boolean
     */
    suspend fun setBusinessAccountGiftSettings(businessConnectionId: String, showGiftButton: Boolean, acceptedGiftTypes: AcceptedGiftTypes): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        p["show_gift_button"] = JsonPrimitive(showGiftButton)
        p["accepted_gift_types"] = TelegramJson.encodeToJsonElement(AcceptedGiftTypes.serializer(), acceptedGiftTypes)
        return api.call("setBusinessAccountGiftSettings", p, files, Boolean.serializer())
    }

    /**
     * setBusinessAccountName — Changes the first and last name of a managed business account. Requires the can_change_name business bot right. Returns True on success.
     * @return Boolean
     */
    suspend fun setBusinessAccountName(businessConnectionId: String, firstName: String, lastName: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        p["first_name"] = JsonPrimitive(firstName)
        if (lastName != null) p["last_name"] = JsonPrimitive(lastName)
        return api.call("setBusinessAccountName", p, files, Boolean.serializer())
    }

    /**
     * setBusinessAccountProfilePhoto — Changes the profile photo of a managed business account. Requires the can_edit_profile_photo business bot right. Returns True on success.
     * @return Boolean
     */
    suspend fun setBusinessAccountProfilePhoto(businessConnectionId: String, photo: InputProfilePhoto, isPublic: Boolean? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        p["photo"] = TelegramJson.encodeToJsonElement(InputProfilePhoto.serializer(), photo)
        if (isPublic != null) p["is_public"] = JsonPrimitive(isPublic)
        return api.call("setBusinessAccountProfilePhoto", p, files, Boolean.serializer())
    }

    /**
     * setBusinessAccountUsername — Changes the username of a managed business account. Requires the can_change_username business bot right. Returns True on success.
     * @return Boolean
     */
    suspend fun setBusinessAccountUsername(businessConnectionId: String, username: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (username != null) p["username"] = JsonPrimitive(username)
        return api.call("setBusinessAccountUsername", p, files, Boolean.serializer())
    }

    /**
     * setChatAdministratorCustomTitle — Use this method to set a custom title for an administrator in a supergroup promoted by the bot. Returns True on success.
     * @return Boolean
     */
    suspend fun setChatAdministratorCustomTitle(chatId: ChatId, userId: Long, customTitle: String): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["user_id"] = JsonPrimitive(userId)
        p["custom_title"] = JsonPrimitive(customTitle)
        return api.call("setChatAdministratorCustomTitle", p, files, Boolean.serializer())
    }

    /**
     * setChatDescription — Use this method to change the description of a group, a supergroup or a channel. The bot must be an administrator in the chat for this to work and must have the appropriate administrator rights. Returns True on success.
     * @return Boolean
     */
    suspend fun setChatDescription(chatId: ChatId, description: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        if (description != null) p["description"] = JsonPrimitive(description)
        return api.call("setChatDescription", p, files, Boolean.serializer())
    }

    /**
     * setChatMemberTag — Use this method to set a tag for a regular member in a group or a supergroup. The bot must be an administrator in the chat for this to work and must have the can_manage_tags administrator right. Returns True on success.
     * @return Boolean
     */
    suspend fun setChatMemberTag(chatId: ChatId, userId: Long, tag: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["user_id"] = JsonPrimitive(userId)
        if (tag != null) p["tag"] = JsonPrimitive(tag)
        return api.call("setChatMemberTag", p, files, Boolean.serializer())
    }

    /**
     * setChatMenuButton — Use this method to change the bot&#39;s menu button in a private chat, or the default menu button. Returns True on success.
     * @return Boolean
     */
    suspend fun setChatMenuButton(chatId: Long? = null, menuButton: MenuButton? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (chatId != null) p["chat_id"] = JsonPrimitive(chatId)
        if (menuButton != null) p["menu_button"] = TelegramJson.encodeToJsonElement(MenuButton.serializer(), menuButton)
        return api.call("setChatMenuButton", p, files, Boolean.serializer())
    }

    /**
     * setChatPermissions — Use this method to set default chat permissions for all members. The bot must be an administrator in the group or a supergroup for this to work and must have the can_restrict_members administrator rights. Returns True on success.
     * @return Boolean
     */
    suspend fun setChatPermissions(chatId: ChatId, permissions: ChatPermissions, useIndependentChatPermissions: Boolean? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["permissions"] = TelegramJson.encodeToJsonElement(ChatPermissions.serializer(), permissions)
        if (useIndependentChatPermissions != null) p["use_independent_chat_permissions"] = JsonPrimitive(useIndependentChatPermissions)
        return api.call("setChatPermissions", p, files, Boolean.serializer())
    }

    /**
     * setChatPhoto — Use this method to set a new profile photo for the chat. Photos can&#39;t be changed for private chats. The bot must be an administrator in the chat for this to work and must have the appropriate administrator rights. Returns True on success.
     * @return Boolean
     */
    suspend fun setChatPhoto(chatId: ChatId, photo: InputFile): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["photo"] = api.inputFileToJson(photo, files)
        return api.call("setChatPhoto", p, files, Boolean.serializer())
    }

    /**
     * setChatStickerSet — Use this method to set a new group sticker set for a supergroup. The bot must be an administrator in the chat for this to work and must have the appropriate administrator rights. Use the field can_set_sticker_set optionally returned in getChat requests to check if the bot can use this method. Return
     * @return Boolean
     */
    suspend fun setChatStickerSet(chatId: ChatId, stickerSetName: String): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["sticker_set_name"] = JsonPrimitive(stickerSetName)
        return api.call("setChatStickerSet", p, files, Boolean.serializer())
    }

    /**
     * setChatTitle — Use this method to change the title of a chat. Titles can&#39;t be changed for private chats. The bot must be an administrator in the chat for this to work and must have the appropriate administrator rights. Returns True on success.
     * @return Boolean
     */
    suspend fun setChatTitle(chatId: ChatId, title: String): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["title"] = JsonPrimitive(title)
        return api.call("setChatTitle", p, files, Boolean.serializer())
    }

    /**
     * setCustomEmojiStickerSetThumbnail — Use this method to set the thumbnail of a custom emoji sticker set. Returns True on success.
     * @return Boolean
     */
    suspend fun setCustomEmojiStickerSetThumbnail(name: String, customEmojiId: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["name"] = JsonPrimitive(name)
        if (customEmojiId != null) p["custom_emoji_id"] = JsonPrimitive(customEmojiId)
        return api.call("setCustomEmojiStickerSetThumbnail", p, files, Boolean.serializer())
    }

    /**
     * setGameScore — Use this method to set the score of the specified user in a game message. On success, if the message is not an inline message, the Message is returned, otherwise True is returned. Returns an error, if the new score is not greater than the user&#39;s current score in the chat and force is False .
     * @return MessageOrTrue
     */
    suspend fun setGameScore(userId: Long, score: Long, force: Boolean? = null, disableEditMessage: Boolean? = null, chatId: Long? = null, messageId: Long? = null, inlineMessageId: String? = null): MessageOrTrue {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        p["score"] = JsonPrimitive(score)
        if (force != null) p["force"] = JsonPrimitive(force)
        if (disableEditMessage != null) p["disable_edit_message"] = JsonPrimitive(disableEditMessage)
        if (chatId != null) p["chat_id"] = JsonPrimitive(chatId)
        if (messageId != null) p["message_id"] = JsonPrimitive(messageId)
        if (inlineMessageId != null) p["inline_message_id"] = JsonPrimitive(inlineMessageId)
        return api.call("setGameScore", p, files, MessageOrTrue.serializer())
    }

    /**
     * setManagedBotAccessSettings — Use this method to change the access settings of a managed bot. Returns True on success.
     * @return Boolean
     */
    suspend fun setManagedBotAccessSettings(userId: Long, isAccessRestricted: Boolean, addedUserIds: List<Long>? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        p["is_access_restricted"] = JsonPrimitive(isAccessRestricted)
        if (addedUserIds != null) p["added_user_ids"] = TelegramJson.encodeToJsonElement(ListSerializer(Long.serializer()), addedUserIds)
        return api.call("setManagedBotAccessSettings", p, files, Boolean.serializer())
    }

    /**
     * setMessageReaction — Use this method to change the chosen reactions on a message. Service messages of some types can&#39;t be reacted to. Automatically forwarded messages from a channel to its discussion group have the same available reactions as messages in the channel. Bots can&#39;t use paid reactions. Returns True o
     * @return Boolean
     */
    suspend fun setMessageReaction(chatId: ChatId, messageId: Long, reaction: List<ReactionType>? = null, isBig: Boolean? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["message_id"] = JsonPrimitive(messageId)
        if (reaction != null) p["reaction"] = TelegramJson.encodeToJsonElement(ListSerializer(ReactionType.serializer()), reaction)
        if (isBig != null) p["is_big"] = JsonPrimitive(isBig)
        return api.call("setMessageReaction", p, files, Boolean.serializer())
    }

    /**
     * setMyCommands — Use this method to change the list of the bot&#39;s commands. See this manual for more details about bot commands. Returns True on success.
     * @return Boolean
     */
    suspend fun setMyCommands(commands: List<BotCommand>, scope: BotCommandScope? = null, languageCode: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["commands"] = TelegramJson.encodeToJsonElement(ListSerializer(BotCommand.serializer()), commands)
        if (scope != null) p["scope"] = TelegramJson.encodeToJsonElement(BotCommandScope.serializer(), scope)
        if (languageCode != null) p["language_code"] = JsonPrimitive(languageCode)
        return api.call("setMyCommands", p, files, Boolean.serializer())
    }

    /**
     * setMyDefaultAdministratorRights — Use this method to change the default administrator rights requested by the bot when it&#39;s added as an administrator to groups or channels. These rights will be suggested to users, but they are free to modify the list before adding the bot. Returns True on success.
     * @return Boolean
     */
    suspend fun setMyDefaultAdministratorRights(rights: ChatAdministratorRights? = null, forChannels: Boolean? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (rights != null) p["rights"] = TelegramJson.encodeToJsonElement(ChatAdministratorRights.serializer(), rights)
        if (forChannels != null) p["for_channels"] = JsonPrimitive(forChannels)
        return api.call("setMyDefaultAdministratorRights", p, files, Boolean.serializer())
    }

    /**
     * setMyDescription — Use this method to change the bot&#39;s description, which is shown in the chat with the bot if the chat is empty. Returns True on success.
     * @return Boolean
     */
    suspend fun setMyDescription(description: String? = null, languageCode: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (description != null) p["description"] = JsonPrimitive(description)
        if (languageCode != null) p["language_code"] = JsonPrimitive(languageCode)
        return api.call("setMyDescription", p, files, Boolean.serializer())
    }

    /**
     * setMyName — Use this method to change the bot&#39;s name. Returns True on success.
     * @return Boolean
     */
    suspend fun setMyName(name: String? = null, languageCode: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (name != null) p["name"] = JsonPrimitive(name)
        if (languageCode != null) p["language_code"] = JsonPrimitive(languageCode)
        return api.call("setMyName", p, files, Boolean.serializer())
    }

    /**
     * setMyProfilePhoto — Changes the profile photo of the bot. Returns True on success.
     * @return Boolean
     */
    suspend fun setMyProfilePhoto(photo: InputProfilePhoto): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["photo"] = TelegramJson.encodeToJsonElement(InputProfilePhoto.serializer(), photo)
        return api.call("setMyProfilePhoto", p, files, Boolean.serializer())
    }

    /**
     * setMyShortDescription — Use this method to change the bot&#39;s short description, which is shown on the bot&#39;s profile page and is sent together with the link when users share the bot. Returns True on success.
     * @return Boolean
     */
    suspend fun setMyShortDescription(shortDescription: String? = null, languageCode: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (shortDescription != null) p["short_description"] = JsonPrimitive(shortDescription)
        if (languageCode != null) p["language_code"] = JsonPrimitive(languageCode)
        return api.call("setMyShortDescription", p, files, Boolean.serializer())
    }

    /**
     * setPassportDataErrors — Informs a user that some of the Telegram Passport elements they provided contains errors. The user will not be able to re-submit their Passport to you until the errors are fixed (the contents of the field for which you returned the error must change). Returns True on success. Use this if the data su
     * @return Boolean
     */
    suspend fun setPassportDataErrors(userId: Long, errors: List<PassportElementError>): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        p["errors"] = TelegramJson.encodeToJsonElement(ListSerializer(PassportElementError.serializer()), errors)
        return api.call("setPassportDataErrors", p, files, Boolean.serializer())
    }

    /**
     * setStickerEmojiList — Use this method to change the list of emoji assigned to a regular or custom emoji sticker. The sticker must belong to a sticker set created by the bot. Returns True on success.
     * @return Boolean
     */
    suspend fun setStickerEmojiList(sticker: String, emojiList: List<String>): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["sticker"] = JsonPrimitive(sticker)
        p["emoji_list"] = TelegramJson.encodeToJsonElement(ListSerializer(String.serializer()), emojiList)
        return api.call("setStickerEmojiList", p, files, Boolean.serializer())
    }

    /**
     * setStickerKeywords — Use this method to change search keywords assigned to a regular or custom emoji sticker. The sticker must belong to a sticker set created by the bot. Returns True on success.
     * @return Boolean
     */
    suspend fun setStickerKeywords(sticker: String, keywords: List<String>? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["sticker"] = JsonPrimitive(sticker)
        if (keywords != null) p["keywords"] = TelegramJson.encodeToJsonElement(ListSerializer(String.serializer()), keywords)
        return api.call("setStickerKeywords", p, files, Boolean.serializer())
    }

    /**
     * setStickerMaskPosition — Use this method to change the mask position of a mask sticker. The sticker must belong to a sticker set that was created by the bot. Returns True on success.
     * @return Boolean
     */
    suspend fun setStickerMaskPosition(sticker: String, maskPosition: MaskPosition? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["sticker"] = JsonPrimitive(sticker)
        if (maskPosition != null) p["mask_position"] = TelegramJson.encodeToJsonElement(MaskPosition.serializer(), maskPosition)
        return api.call("setStickerMaskPosition", p, files, Boolean.serializer())
    }

    /**
     * setStickerPositionInSet — Use this method to move a sticker in a set created by the bot to a specific position. Returns True on success.
     * @return Boolean
     */
    suspend fun setStickerPositionInSet(sticker: String, position: Long): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["sticker"] = JsonPrimitive(sticker)
        p["position"] = JsonPrimitive(position)
        return api.call("setStickerPositionInSet", p, files, Boolean.serializer())
    }

    /**
     * setStickerSetThumbnail — Use this method to set the thumbnail of a regular or mask sticker set. The format of the thumbnail file must match the format of the stickers in the set. Returns True on success.
     * @return Boolean
     */
    suspend fun setStickerSetThumbnail(name: String, userId: Long, format: String, thumbnail: InputFile? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["name"] = JsonPrimitive(name)
        p["user_id"] = JsonPrimitive(userId)
        p["format"] = JsonPrimitive(format)
        if (thumbnail != null) p["thumbnail"] = api.inputFileToJson(thumbnail, files)
        return api.call("setStickerSetThumbnail", p, files, Boolean.serializer())
    }

    /**
     * setStickerSetTitle — Use this method to set the title of a created sticker set. Returns True on success.
     * @return Boolean
     */
    suspend fun setStickerSetTitle(name: String, title: String): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["name"] = JsonPrimitive(name)
        p["title"] = JsonPrimitive(title)
        return api.call("setStickerSetTitle", p, files, Boolean.serializer())
    }

    /**
     * setUserEmojiStatus — Changes the emoji status for a given user that previously allowed the bot to manage their emoji status via the Mini App method requestEmojiStatusAccess . Returns True on success.
     * @return Boolean
     */
    suspend fun setUserEmojiStatus(userId: Long, emojiStatusCustomEmojiId: String? = null, emojiStatusExpirationDate: Long? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        if (emojiStatusCustomEmojiId != null) p["emoji_status_custom_emoji_id"] = JsonPrimitive(emojiStatusCustomEmojiId)
        if (emojiStatusExpirationDate != null) p["emoji_status_expiration_date"] = JsonPrimitive(emojiStatusExpirationDate)
        return api.call("setUserEmojiStatus", p, files, Boolean.serializer())
    }

    /**
     * setWebhook — Use this method to specify a URL and receive incoming updates via an outgoing webhook. Whenever there is an update for the bot, we will send an HTTPS POST request to the specified URL, containing a JSON-serialized Update . In case of an unsuccessful request (a request with response HTTP status code 
     * @return Boolean
     */
    suspend fun setWebhook(url: String, certificate: InputFile? = null, ipAddress: String? = null, maxConnections: Long? = null, allowedUpdates: List<String>? = null, dropPendingUpdates: Boolean? = null, secretToken: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["url"] = JsonPrimitive(url)
        if (certificate != null) p["certificate"] = api.inputFileToJson(certificate, files)
        if (ipAddress != null) p["ip_address"] = JsonPrimitive(ipAddress)
        if (maxConnections != null) p["max_connections"] = JsonPrimitive(maxConnections)
        if (allowedUpdates != null) p["allowed_updates"] = TelegramJson.encodeToJsonElement(ListSerializer(String.serializer()), allowedUpdates)
        if (dropPendingUpdates != null) p["drop_pending_updates"] = JsonPrimitive(dropPendingUpdates)
        if (secretToken != null) p["secret_token"] = JsonPrimitive(secretToken)
        return api.call("setWebhook", p, files, Boolean.serializer())
    }

    /**
     * stopMessageLiveLocation — Use this method to stop updating a live location message before live_period expires. On success, if the message is not an inline message, the edited Message is returned, otherwise True is returned.
     * @return MessageOrTrue
     */
    suspend fun stopMessageLiveLocation(businessConnectionId: String? = null, chatId: ChatId? = null, messageId: Long? = null, inlineMessageId: String? = null, replyMarkup: InlineKeyboardMarkup? = null): MessageOrTrue {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (chatId != null) p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        if (messageId != null) p["message_id"] = JsonPrimitive(messageId)
        if (inlineMessageId != null) p["inline_message_id"] = JsonPrimitive(inlineMessageId)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(InlineKeyboardMarkup.serializer(), replyMarkup)
        return api.call("stopMessageLiveLocation", p, files, MessageOrTrue.serializer())
    }

    /**
     * stopPoll — Use this method to stop a poll which was sent by the bot. On success, the stopped Poll is returned.
     * @return Poll
     */
    suspend fun stopPoll(chatId: ChatId, messageId: Long, businessConnectionId: String? = null, replyMarkup: InlineKeyboardMarkup? = null): Poll {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["message_id"] = JsonPrimitive(messageId)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (replyMarkup != null) p["reply_markup"] = TelegramJson.encodeToJsonElement(InlineKeyboardMarkup.serializer(), replyMarkup)
        return api.call("stopPoll", p, files, Poll.serializer())
    }

    /**
     * transferBusinessAccountStars — Transfers Telegram Stars from the business account balance to the bot&#39;s balance. Requires the can_transfer_stars business bot right. Returns True on success.
     * @return Boolean
     */
    suspend fun transferBusinessAccountStars(businessConnectionId: String, starCount: Long): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        p["star_count"] = JsonPrimitive(starCount)
        return api.call("transferBusinessAccountStars", p, files, Boolean.serializer())
    }

    /**
     * transferGift — Transfers an owned unique gift to another user. Requires the can_transfer_and_upgrade_gifts business bot right. Requires can_transfer_stars business bot right if the transfer is paid. Returns True on success.
     * @return Boolean
     */
    suspend fun transferGift(businessConnectionId: String, ownedGiftId: String, newOwnerChatId: Long, starCount: Long? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        p["owned_gift_id"] = JsonPrimitive(ownedGiftId)
        p["new_owner_chat_id"] = JsonPrimitive(newOwnerChatId)
        if (starCount != null) p["star_count"] = JsonPrimitive(starCount)
        return api.call("transferGift", p, files, Boolean.serializer())
    }

    /**
     * unbanChatMember — Use this method to unban a previously banned user in a supergroup or channel. The user will not return to the group or channel automatically, but will be able to join via link, etc. The bot must be an administrator for this to work. By default, this method guarantees that after the call the user is 
     * @return Boolean
     */
    suspend fun unbanChatMember(chatId: ChatId, userId: Long, onlyIfBanned: Boolean? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["user_id"] = JsonPrimitive(userId)
        if (onlyIfBanned != null) p["only_if_banned"] = JsonPrimitive(onlyIfBanned)
        return api.call("unbanChatMember", p, files, Boolean.serializer())
    }

    /**
     * unbanChatSenderChat — Use this method to unban a previously banned channel chat in a supergroup or channel. The bot must be an administrator for this to work and must have the appropriate administrator rights. Returns True on success.
     * @return Boolean
     */
    suspend fun unbanChatSenderChat(chatId: ChatId, senderChatId: Long): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["sender_chat_id"] = JsonPrimitive(senderChatId)
        return api.call("unbanChatSenderChat", p, files, Boolean.serializer())
    }

    /**
     * unhideGeneralForumTopic — Use this method to unhide the &#39;General&#39; topic in a forum supergroup chat. The bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator rights. Returns True on success.
     * @return Boolean
     */
    suspend fun unhideGeneralForumTopic(chatId: ChatId): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        return api.call("unhideGeneralForumTopic", p, files, Boolean.serializer())
    }

    /**
     * unpinAllChatMessages — Use this method to clear the list of pinned messages in a chat. In private chats and channel direct messages chats, no additional rights are required to unpin all pinned messages. Conversely, the bot must be an administrator with the &#39;can_pin_messages&#39; right or the &#39;can_edit_messages&#39
     * @return Boolean
     */
    suspend fun unpinAllChatMessages(chatId: ChatId): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        return api.call("unpinAllChatMessages", p, files, Boolean.serializer())
    }

    /**
     * unpinAllForumTopicMessages — Use this method to clear the list of pinned messages in a forum topic in a forum supergroup chat or a private chat with a user. In the case of a supergroup chat the bot must be an administrator in the chat for this to work and must have the can_pin_messages administrator right in the supergroup. Ret
     * @return Boolean
     */
    suspend fun unpinAllForumTopicMessages(chatId: ChatId, messageThreadId: Long): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        p["message_thread_id"] = JsonPrimitive(messageThreadId)
        return api.call("unpinAllForumTopicMessages", p, files, Boolean.serializer())
    }

    /**
     * unpinAllGeneralForumTopicMessages — Use this method to clear the list of pinned messages in a General forum topic. The bot must be an administrator in the chat for this to work and must have the can_pin_messages administrator right in the supergroup. Returns True on success.
     * @return Boolean
     */
    suspend fun unpinAllGeneralForumTopicMessages(chatId: ChatId): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        return api.call("unpinAllGeneralForumTopicMessages", p, files, Boolean.serializer())
    }

    /**
     * unpinChatMessage — Use this method to remove a message from the list of pinned messages in a chat. In private chats and channel direct messages chats, all messages can be unpinned. Conversely, the bot must be an administrator with the &#39;can_pin_messages&#39; right or the &#39;can_edit_messages&#39; right to unpin m
     * @return Boolean
     */
    suspend fun unpinChatMessage(chatId: ChatId, businessConnectionId: String? = null, messageId: Long? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        if (businessConnectionId != null) p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        if (messageId != null) p["message_id"] = JsonPrimitive(messageId)
        return api.call("unpinChatMessage", p, files, Boolean.serializer())
    }

    /**
     * upgradeGift — Upgrades a given regular gift to a unique gift. Requires the can_transfer_and_upgrade_gifts business bot right. Additionally requires the can_transfer_stars business bot right if the upgrade is paid. Returns True on success.
     * @return Boolean
     */
    suspend fun upgradeGift(businessConnectionId: String, ownedGiftId: String, keepOriginalDetails: Boolean? = null, starCount: Long? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["business_connection_id"] = JsonPrimitive(businessConnectionId)
        p["owned_gift_id"] = JsonPrimitive(ownedGiftId)
        if (keepOriginalDetails != null) p["keep_original_details"] = JsonPrimitive(keepOriginalDetails)
        if (starCount != null) p["star_count"] = JsonPrimitive(starCount)
        return api.call("upgradeGift", p, files, Boolean.serializer())
    }

    /**
     * uploadStickerFile — Use this method to upload a file with a sticker for later use in the createNewStickerSet , addStickerToSet , or replaceStickerInSet methods (the file can be used multiple times). Returns the uploaded File on success.
     * @return File
     */
    suspend fun uploadStickerFile(userId: Long, sticker: InputFile, stickerFormat: String): File {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        p["sticker"] = api.inputFileToJson(sticker, files)
        p["sticker_format"] = JsonPrimitive(stickerFormat)
        return api.call("uploadStickerFile", p, files, File.serializer())
    }

    /**
     * verifyChat — Verifies a chat on behalf of the organization which is represented by the bot. Returns True on success.
     * @return Boolean
     */
    suspend fun verifyChat(chatId: ChatId, customDescription: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["chat_id"] = TelegramJson.encodeToJsonElement(ChatId.serializer(), chatId)
        if (customDescription != null) p["custom_description"] = JsonPrimitive(customDescription)
        return api.call("verifyChat", p, files, Boolean.serializer())
    }

    /**
     * verifyUser — Verifies a user on behalf of the organization which is represented by the bot. Returns True on success.
     * @return Boolean
     */
    suspend fun verifyUser(userId: Long, customDescription: String? = null): Boolean {
        val files = mutableMapOf<String, ApiClient.FilePayload>()
        val p = mutableMapOf<String, JsonElement>()
        p["user_id"] = JsonPrimitive(userId)
        if (customDescription != null) p["custom_description"] = JsonPrimitive(customDescription)
        return api.call("verifyUser", p, files, Boolean.serializer())
    }
}
