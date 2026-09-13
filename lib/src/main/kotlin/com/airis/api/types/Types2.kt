package com.airis.api.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Telegram Bot API 10.3 types (part 2/4).
 * Auto-generated from https://core.telegram.org/bots/api (Bot API 10.3, Aug 24 2026).
 * Optimized with kotlinx.serialization: explicitNulls=false, ignoreUnknownKeys=true.
 */

@Serializable
data class Giveaway(
    @SerialName("chats")
    val chats: List<Chat>,
    @SerialName("winners_selection_date")
    val winnersSelectionDate: Long,
    @SerialName("winner_count")
    val winnerCount: Long,
    @SerialName("only_new_members")
    val onlyNewMembers: Boolean? = null,
    @SerialName("has_public_winners")
    val hasPublicWinners: Boolean? = null,
    @SerialName("prize_description")
    val prizeDescription: String? = null,
    @SerialName("country_codes")
    val countryCodes: List<String>? = null,
    @SerialName("prize_star_count")
    val prizeStarCount: Long? = null,
    @SerialName("premium_subscription_month_count")
    val premiumSubscriptionMonthCount: Long? = null
)

@Serializable
data class GiveawayCompleted(
    @SerialName("winner_count")
    val winnerCount: Long,
    @SerialName("unclaimed_prize_count")
    val unclaimedPrizeCount: Long? = null,
    @SerialName("giveaway_message")
    val giveawayMessage: Message? = null,
    @SerialName("is_star_giveaway")
    val isStarGiveaway: Boolean? = null
)

@Serializable
data class GiveawayCreated(
    @SerialName("prize_star_count")
    val prizeStarCount: Long? = null
)

@Serializable
data class GiveawayWinners(
    @SerialName("chat")
    val chat: Chat,
    @SerialName("giveaway_message_id")
    val giveawayMessageId: Long,
    @SerialName("winners_selection_date")
    val winnersSelectionDate: Long,
    @SerialName("winner_count")
    val winnerCount: Long,
    @SerialName("winners")
    val winners: List<User>,
    @SerialName("additional_chat_count")
    val additionalChatCount: Long? = null,
    @SerialName("prize_star_count")
    val prizeStarCount: Long? = null,
    @SerialName("premium_subscription_month_count")
    val premiumSubscriptionMonthCount: Long? = null,
    @SerialName("unclaimed_prize_count")
    val unclaimedPrizeCount: Long? = null,
    @SerialName("only_new_members")
    val onlyNewMembers: Boolean? = null,
    @SerialName("was_refunded")
    val wasRefunded: Boolean? = null,
    @SerialName("prize_description")
    val prizeDescription: String? = null
)

@Serializable
data class InaccessibleMessage(
    @SerialName("chat")
    val chat: Chat,
    @SerialName("message_id")
    val messageId: Long,
    @SerialName("date")
    val date: Long
) : MaybeInaccessibleMessage

@Serializable
data class InlineKeyboardButton(
    @SerialName("text")
    val text: String,
    @SerialName("icon_custom_emoji_id")
    val iconCustomEmojiId: String? = null,
    @SerialName("style")
    val style: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("callback_data")
    val callbackData: String? = null,
    @SerialName("web_app")
    val webApp: WebAppInfo? = null,
    @SerialName("login_url")
    val loginUrl: LoginUrl? = null,
    @SerialName("switch_inline_query")
    val switchInlineQuery: String? = null,
    @SerialName("switch_inline_query_current_chat")
    val switchInlineQueryCurrentChat: String? = null,
    @SerialName("switch_inline_query_chosen_chat")
    val switchInlineQueryChosenChat: SwitchInlineQueryChosenChat? = null,
    @SerialName("copy_text")
    val copyText: CopyTextButton? = null,
    @SerialName("callback_game")
    val callbackGame: CallbackGame? = null,
    @SerialName("pay")
    val pay: Boolean? = null,
    @SerialName("disabled")
    val disabled: DisabledButton? = null
)

@Serializable
data class InlineKeyboardMarkup(
    @SerialName("inline_keyboard")
    val inlineKeyboard: List<List<InlineKeyboardButton>>,
    @SerialName("force_reply")
    val forceReply: Boolean? = null
) : ReplyMarkup

@Serializable
data class InlineQuery(
    @SerialName("id")
    val id: String,
    @SerialName("from")
    val from: User,
    @SerialName("query")
    val query: String,
    @SerialName("offset")
    val offset: String,
    @SerialName("chat_type")
    val chatType: String? = null,
    @SerialName("location")
    val location: Location? = null
)

@Serializable
data class InlineQueryResultArticle(
    @SerialName("type")
    val type: String = "article",
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String? = null,
    @SerialName("thumbnail_width")
    val thumbnailWidth: Long? = null,
    @SerialName("thumbnail_height")
    val thumbnailHeight: Long? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultAudio(
    @SerialName("type")
    val type: String = "audio",
    @SerialName("id")
    val id: String,
    @SerialName("audio_url")
    val audioUrl: String,
    @SerialName("title")
    val title: String,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("performer")
    val performer: String? = null,
    @SerialName("audio_duration")
    val audioDuration: Long? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultCachedAudio(
    @SerialName("type")
    val type: String,
    @SerialName("id")
    val id: String,
    @SerialName("audio_file_id")
    val audioFileId: String,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultCachedDocument(
    @SerialName("type")
    val type: String,
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("document_file_id")
    val documentFileId: String,
    @SerialName("description")
    val description: String? = null,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultCachedGif(
    @SerialName("type")
    val type: String,
    @SerialName("id")
    val id: String,
    @SerialName("gif_file_id")
    val gifFileId: String,
    @SerialName("title")
    val title: String? = null,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultCachedMpeg4Gif(
    @SerialName("type")
    val type: String,
    @SerialName("id")
    val id: String,
    @SerialName("mpeg4_file_id")
    val mpeg4FileId: String,
    @SerialName("title")
    val title: String? = null,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultCachedPhoto(
    @SerialName("type")
    val type: String,
    @SerialName("id")
    val id: String,
    @SerialName("photo_file_id")
    val photoFileId: String,
    @SerialName("title")
    val title: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultCachedSticker(
    @SerialName("type")
    val type: String = "sticker",
    @SerialName("id")
    val id: String,
    @SerialName("sticker_file_id")
    val stickerFileId: String,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultCachedVideo(
    @SerialName("type")
    val type: String,
    @SerialName("id")
    val id: String,
    @SerialName("video_file_id")
    val videoFileId: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String? = null,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultCachedVoice(
    @SerialName("type")
    val type: String,
    @SerialName("id")
    val id: String,
    @SerialName("voice_file_id")
    val voiceFileId: String,
    @SerialName("title")
    val title: String,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultContact(
    @SerialName("type")
    val type: String = "contact",
    @SerialName("id")
    val id: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String? = null,
    @SerialName("vcard")
    val vcard: String? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String? = null,
    @SerialName("thumbnail_width")
    val thumbnailWidth: Long? = null,
    @SerialName("thumbnail_height")
    val thumbnailHeight: Long? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultDocument(
    @SerialName("type")
    val type: String = "document",
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("document_url")
    val documentUrl: String,
    @SerialName("mime_type")
    val mimeType: String,
    @SerialName("description")
    val description: String? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String? = null,
    @SerialName("thumbnail_width")
    val thumbnailWidth: Long? = null,
    @SerialName("thumbnail_height")
    val thumbnailHeight: Long? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultGame(
    @SerialName("type")
    val type: String = "game",
    @SerialName("id")
    val id: String,
    @SerialName("game_short_name")
    val gameShortName: String,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultGif(
    @SerialName("type")
    val type: String = "gif",
    @SerialName("id")
    val id: String,
    @SerialName("gif_url")
    val gifUrl: String,
    @SerialName("gif_width")
    val gifWidth: Long? = null,
    @SerialName("gif_height")
    val gifHeight: Long? = null,
    @SerialName("gif_duration")
    val gifDuration: Long? = null,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String,
    @SerialName("thumbnail_mime_type")
    val thumbnailMimeType: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultLocation(
    @SerialName("type")
    val type: String = "location",
    @SerialName("id")
    val id: String,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("title")
    val title: String,
    @SerialName("horizontal_accuracy")
    val horizontalAccuracy: Double? = null,
    @SerialName("live_period")
    val livePeriod: Long? = null,
    @SerialName("heading")
    val heading: Long? = null,
    @SerialName("proximity_alert_radius")
    val proximityAlertRadius: Long? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String? = null,
    @SerialName("thumbnail_width")
    val thumbnailWidth: Long? = null,
    @SerialName("thumbnail_height")
    val thumbnailHeight: Long? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultMpeg4Gif(
    @SerialName("type")
    val type: String = "mpeg4_gif",
    @SerialName("id")
    val id: String,
    @SerialName("mpeg4_url")
    val mpeg4Url: String,
    @SerialName("mpeg4_width")
    val mpeg4Width: Long? = null,
    @SerialName("mpeg4_height")
    val mpeg4Height: Long? = null,
    @SerialName("mpeg4_duration")
    val mpeg4Duration: Long? = null,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String,
    @SerialName("thumbnail_mime_type")
    val thumbnailMimeType: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultPhoto(
    @SerialName("type")
    val type: String = "photo",
    @SerialName("id")
    val id: String,
    @SerialName("photo_url")
    val photoUrl: String,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String,
    @SerialName("photo_width")
    val photoWidth: Long? = null,
    @SerialName("photo_height")
    val photoHeight: Long? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultVenue(
    @SerialName("type")
    val type: String = "venue",
    @SerialName("id")
    val id: String,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("title")
    val title: String,
    @SerialName("address")
    val address: String,
    @SerialName("foursquare_id")
    val foursquareId: String? = null,
    @SerialName("foursquare_type")
    val foursquareType: String? = null,
    @SerialName("google_place_id")
    val googlePlaceId: String? = null,
    @SerialName("google_place_type")
    val googlePlaceType: String? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String? = null,
    @SerialName("thumbnail_width")
    val thumbnailWidth: Long? = null,
    @SerialName("thumbnail_height")
    val thumbnailHeight: Long? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultVideo(
    @SerialName("type")
    val type: String = "video",
    @SerialName("id")
    val id: String,
    @SerialName("video_url")
    val videoUrl: String,
    @SerialName("mime_type")
    val mimeType: String,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String,
    @SerialName("title")
    val title: String,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,
    @SerialName("video_width")
    val videoWidth: Long? = null,
    @SerialName("video_height")
    val videoHeight: Long? = null,
    @SerialName("video_duration")
    val videoDuration: Long? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultVoice(
    @SerialName("type")
    val type: String = "voice",
    @SerialName("id")
    val id: String,
    @SerialName("voice_url")
    val voiceUrl: String,
    @SerialName("title")
    val title: String,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("voice_duration")
    val voiceDuration: Long? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content")
    val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult

@Serializable
data class InlineQueryResultsButton(
    @SerialName("text")
    val text: String,
    @SerialName("web_app")
    val webApp: WebAppInfo? = null,
    @SerialName("start_parameter")
    val startParameter: String? = null
)

@Serializable
data class InputChecklist(
    @SerialName("title")
    val title: String,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("title_entities")
    val titleEntities: List<MessageEntity>? = null,
    @SerialName("tasks")
    val tasks: List<InputChecklistTask>,
    @SerialName("others_can_add_tasks")
    val othersCanAddTasks: Boolean? = null,
    @SerialName("others_can_mark_tasks_as_done")
    val othersCanMarkTasksAsDone: Boolean? = null
)

@Serializable
data class InputChecklistTask(
    @SerialName("id")
    val id: Long,
    @SerialName("text")
    val text: String,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("text_entities")
    val textEntities: List<MessageEntity>? = null
)

@Serializable
data class InputContactMessageContent(
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String? = null,
    @SerialName("vcard")
    val vcard: String? = null
) : InputMessageContent

@Serializable
data class InputInvoiceMessageContent(
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("payload")
    val payload: String,
    @SerialName("provider_token")
    val providerToken: String? = null,
    @SerialName("currency")
    val currency: String,
    @SerialName("prices")
    val prices: List<LabeledPrice>,
    @SerialName("max_tip_amount")
    val maxTipAmount: Long? = null,
    @SerialName("suggested_tip_amounts")
    val suggestedTipAmounts: List<Long>? = null,
    @SerialName("provider_data")
    val providerData: String? = null,
    @SerialName("photo_url")
    val photoUrl: String? = null,
    @SerialName("photo_size")
    val photoSize: Long? = null,
    @SerialName("photo_width")
    val photoWidth: Long? = null,
    @SerialName("photo_height")
    val photoHeight: Long? = null,
    @SerialName("need_name")
    val needName: Boolean? = null,
    @SerialName("need_phone_number")
    val needPhoneNumber: Boolean? = null,
    @SerialName("need_email")
    val needEmail: Boolean? = null,
    @SerialName("need_shipping_address")
    val needShippingAddress: Boolean? = null,
    @SerialName("send_phone_number_to_provider")
    val sendPhoneNumberToProvider: Boolean? = null,
    @SerialName("send_email_to_provider")
    val sendEmailToProvider: Boolean? = null,
    @SerialName("is_flexible")
    val isFlexible: Boolean? = null
) : InputMessageContent

@Serializable
data class InputLocationMessageContent(
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("horizontal_accuracy")
    val horizontalAccuracy: Double? = null,
    @SerialName("live_period")
    val livePeriod: Long? = null,
    @SerialName("heading")
    val heading: Long? = null,
    @SerialName("proximity_alert_radius")
    val proximityAlertRadius: Long? = null
) : InputMessageContent

@Serializable
data class InputMediaAnimation(
    @SerialName("type")
    val type: String = "animation",
    @SerialName("media")
    val media: String,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,
    @SerialName("width")
    val width: Long? = null,
    @SerialName("height")
    val height: Long? = null,
    @SerialName("duration")
    val duration: Long? = null,
    @SerialName("has_spoiler")
    val hasSpoiler: Boolean? = null
) : InputPollMedia, InputPollOptionMedia, InputMedia

@Serializable
data class InputMediaAudio(
    @SerialName("type")
    val type: String = "audio",
    @SerialName("media")
    val media: String,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("duration")
    val duration: Long? = null,
    @SerialName("performer")
    val performer: String? = null,
    @SerialName("title")
    val title: String? = null
) : InputPollMedia, InputMedia

@Serializable
data class InputMediaDocument(
    @SerialName("type")
    val type: String = "document",
    @SerialName("media")
    val media: String,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("disable_content_type_detection")
    val disableContentTypeDetection: Boolean? = null
) : InputPollMedia, InputMedia

@Serializable
data class InputMediaLink(
    @SerialName("type")
    val type: String = "link",
    @SerialName("url")
    val url: String
) : InputPollOptionMedia

@Serializable
data class InputMediaLivePhoto(
    @SerialName("type")
    val type: String = "live_photo",
    @SerialName("media")
    val media: String,
    @SerialName("photo")
    val photo: String,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,
    @SerialName("has_spoiler")
    val hasSpoiler: Boolean? = null
) : InputPollMedia, InputPollOptionMedia, InputMedia

@Serializable
data class InputMediaLocation(
    @SerialName("type")
    val type: String = "location",
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("horizontal_accuracy")
    val horizontalAccuracy: Double? = null
) : InputPollMedia, InputPollOptionMedia

@Serializable
data class InputMediaPhoto(
    @SerialName("type")
    val type: String = "photo",
    @SerialName("media")
    val media: String,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,
    @SerialName("has_spoiler")
    val hasSpoiler: Boolean? = null
) : InputPollMedia, InputPollOptionMedia, InputMedia

@Serializable
data class InputMediaSticker(
    @SerialName("type")
    val type: String = "sticker",
    @SerialName("media")
    val media: String,
    @SerialName("emoji")
    val emoji: String? = null
) : InputPollOptionMedia

@Serializable
data class InputMediaVenue(
    @SerialName("type")
    val type: String = "venue",
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("title")
    val title: String,
    @SerialName("address")
    val address: String,
    @SerialName("foursquare_id")
    val foursquareId: String? = null,
    @SerialName("foursquare_type")
    val foursquareType: String? = null,
    @SerialName("google_place_id")
    val googlePlaceId: String? = null,
    @SerialName("google_place_type")
    val googlePlaceType: String? = null
) : InputPollMedia, InputPollOptionMedia

@Serializable
data class InputMediaVideo(
    @SerialName("type")
    val type: String = "video",
    @SerialName("media")
    val media: String,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
    @SerialName("cover")
    val cover: String? = null,
    @SerialName("start_timestamp")
    val startTimestamp: Long? = null,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,
    @SerialName("width")
    val width: Long? = null,
    @SerialName("height")
    val height: Long? = null,
    @SerialName("duration")
    val duration: Long? = null,
    @SerialName("supports_streaming")
    val supportsStreaming: Boolean? = null,
    @SerialName("has_spoiler")
    val hasSpoiler: Boolean? = null
) : InputPollMedia, InputPollOptionMedia, InputMedia

@Serializable
data class InputMediaVoiceNote(
    @SerialName("type")
    val type: String,
    @SerialName("media")
    val media: String,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("duration")
    val duration: Long? = null
)

@Serializable
data class InputPaidMediaLivePhoto(
    @SerialName("type")
    val type: String = "live_photo",
    @SerialName("media")
    val media: String,
    @SerialName("photo")
    val photo: String
) : InputPaidMedia

@Serializable
data class InputPaidMediaPhoto(
    @SerialName("type")
    val type: String = "photo",
    @SerialName("media")
    val media: String
) : InputPaidMedia

@Serializable
data class InputPaidMediaVideo(
    @SerialName("type")
    val type: String = "video",
    @SerialName("media")
    val media: String,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
    @SerialName("cover")
    val cover: String? = null,
    @SerialName("start_timestamp")
    val startTimestamp: Long? = null,
    @SerialName("width")
    val width: Long? = null,
    @SerialName("height")
    val height: Long? = null,
    @SerialName("duration")
    val duration: Long? = null,
    @SerialName("supports_streaming")
    val supportsStreaming: Boolean? = null
) : InputPaidMedia

@Serializable
data class InputPollOption(
    @SerialName("text")
    val text: String,
    @SerialName("text_parse_mode")
    val textParseMode: String? = null,
    @SerialName("text_entities")
    val textEntities: List<MessageEntity>? = null,
    @SerialName("media")
    val media: InputPollOptionMedia? = null
)

@Serializable
data class InputProfilePhotoAnimated(
    @SerialName("type")
    val type: String = "animated",
    @SerialName("animation")
    val animation: String,
    @SerialName("main_frame_timestamp")
    val mainFrameTimestamp: Double? = null
) : InputProfilePhoto

@Serializable
data class InputProfilePhotoStatic(
    @SerialName("type")
    val type: String = "static",
    @SerialName("photo")
    val photo: String
) : InputProfilePhoto

@Serializable
data class InputRichBlockAnchor(
    @SerialName("type")
    val type: String = "anchor",
    @SerialName("name")
    val name: String
) : InputRichBlock

@Serializable
data class InputRichBlockAnimation(
    @SerialName("type")
    val type: String = "animation",
    @SerialName("animation")
    val animation: InputMediaAnimation,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : InputRichBlock

@Serializable
data class InputRichBlockAudio(
    @SerialName("type")
    val type: String = "audio",
    @SerialName("audio")
    val audio: InputMediaAudio,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : InputRichBlock

@Serializable
data class InputRichBlockBlockQuotation(
    @SerialName("type")
    val type: String = "blockquote",
    @SerialName("blocks")
    val blocks: List<InputRichBlock>,
    @SerialName("credit")
    val credit: RichText? = null
) : InputRichBlock

@Serializable
data class InputRichBlockButtons(
    @SerialName("type")
    val type: String = "buttons",
    @SerialName("buttons")
    val buttons: List<RichMessageButton>,
    @SerialName("align")
    val align: String? = null
) : InputRichBlock

@Serializable
data class InputRichBlockCollage(
    @SerialName("type")
    val type: String = "collage",
    @SerialName("blocks")
    val blocks: List<InputRichBlock>,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : InputRichBlock

@Serializable
data class InputRichBlockDetails(
    @SerialName("type")
    val type: String = "details",
    @SerialName("summary")
    val summary: RichText,
    @SerialName("blocks")
    val blocks: List<InputRichBlock>,
    @SerialName("is_open")
    val isOpen: Boolean? = null
) : InputRichBlock

@Serializable
data class InputRichBlockDivider(
    @SerialName("type")
    val type: String = "divider"
) : InputRichBlock

@Serializable
data class InputRichBlockDocument(
    @SerialName("type")
    val type: String = "document",
    @SerialName("document")
    val document: InputMediaDocument,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : InputRichBlock

@Serializable
data class InputRichBlockExpandableBlockQuotation(
    @SerialName("type")
    val type: String = "expandable_blockquote",
    @SerialName("text")
    val text: RichText,
    @SerialName("credit")
    val credit: RichText? = null
) : InputRichBlock

@Serializable
data class InputRichBlockFooter(
    @SerialName("type")
    val type: String = "footer",
    @SerialName("text")
    val text: RichText
) : InputRichBlock

@Serializable
data class InputRichBlockList(
    @SerialName("type")
    val type: String = "list",
    @SerialName("items")
    val items: List<InputRichBlockListItem>
) : InputRichBlock

@Serializable
data class InputRichBlockListItem(
    @SerialName("blocks")
    val blocks: List<InputRichBlock>,
    @SerialName("has_checkbox")
    val hasCheckbox: Boolean? = null,
    @SerialName("is_checked")
    val isChecked: Boolean? = null,
    @SerialName("value")
    val value: Long? = null,
    @SerialName("type")
    val type: String? = null
)

@Serializable
data class InputRichBlockMap(
    @SerialName("type")
    val type: String = "map",
    @SerialName("location")
    val location: Location,
    @SerialName("zoom")
    val zoom: Long? = null,
    @SerialName("width")
    val width: Long? = null,
    @SerialName("height")
    val height: Long? = null,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : InputRichBlock

@Serializable
data class InputRichBlockMathematicalExpression(
    @SerialName("type")
    val type: String = "mathematical_expression",
    @SerialName("expression")
    val expression: String
) : InputRichBlock

@Serializable
data class InputRichBlockParagraph(
    @SerialName("type")
    val type: String = "paragraph",
    @SerialName("text")
    val text: RichText
) : InputRichBlock

@Serializable
data class InputRichBlockPhoto(
    @SerialName("type")
    val type: String = "photo",
    @SerialName("photo")
    val photo: InputMediaPhoto,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : InputRichBlock

@Serializable
data class InputRichBlockPreformatted(
    @SerialName("type")
    val type: String = "pre",
    @SerialName("text")
    val text: RichText,
    @SerialName("language")
    val language: String? = null
) : InputRichBlock

@Serializable
data class InputRichBlockPullQuotation(
    @SerialName("type")
    val type: String = "pullquote",
    @SerialName("text")
    val text: RichText,
    @SerialName("credit")
    val credit: RichText? = null
) : InputRichBlock

@Serializable
data class InputRichBlockSectionHeading(
    @SerialName("type")
    val type: String = "heading",
    @SerialName("text")
    val text: RichText,
    @SerialName("size")
    val size: Long
) : InputRichBlock

@Serializable
data class InputRichBlockSlideshow(
    @SerialName("type")
    val type: String = "slideshow",
    @SerialName("blocks")
    val blocks: List<InputRichBlock>,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : InputRichBlock

@Serializable
data class InputRichBlockTable(
    @SerialName("type")
    val type: String = "table",
    @SerialName("cells")
    val cells: List<List<RichBlockTableCell>>,
    @SerialName("is_bordered")
    val isBordered: Boolean? = null,
    @SerialName("is_striped")
    val isStriped: Boolean? = null,
    @SerialName("is_compact")
    val isCompact: Boolean? = null,
    @SerialName("caption")
    val caption: RichText? = null
) : InputRichBlock

@Serializable
data class InputRichBlockThinking(
    @SerialName("type")
    val type: String = "thinking",
    @SerialName("text")
    val text: RichText
) : InputRichBlock

@Serializable
data class InputRichBlockVideo(
    @SerialName("type")
    val type: String = "video",
    @SerialName("video")
    val video: InputMediaVideo,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : InputRichBlock

@Serializable
data class InputRichBlockVoiceNote(
    @SerialName("type")
    val type: String = "voice_note",
    @SerialName("voice_note")
    val voiceNote: InputMediaVoiceNote,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : InputRichBlock

@Serializable
data class InputRichMessage(
    @SerialName("blocks")
    val blocks: List<InputRichBlock>? = null,
    @SerialName("html")
    val html: String? = null,
    @SerialName("markdown")
    val markdown: String? = null,
    @SerialName("media")
    val media: List<InputRichMessageMedia>? = null,
    @SerialName("is_rtl")
    val isRtl: Boolean? = null,
    @SerialName("skip_entity_detection")
    val skipEntityDetection: Boolean? = null
)

@Serializable
data class InputRichMessageContent(
    @SerialName("rich_message")
    val richMessage: InputRichMessage
) : InputMessageContent

@Serializable
data class InputRichMessageMedia(
    @SerialName("id")
    val id: String,
    @SerialName("media")
    val media: InputMedia
)

@Serializable
data class InputSticker(
    @SerialName("sticker")
    val sticker: String,
    @SerialName("format")
    val format: String,
    @SerialName("emoji_list")
    val emojiList: List<String>,
    @SerialName("mask_position")
    val maskPosition: MaskPosition? = null,
    @SerialName("keywords")
    val keywords: List<String>? = null
)

@Serializable
data class InputStoryContentPhoto(
    @SerialName("type")
    val type: String = "photo",
    @SerialName("photo")
    val photo: String
) : InputStoryContent

@Serializable
data class InputStoryContentVideo(
    @SerialName("type")
    val type: String = "video",
    @SerialName("video")
    val video: String,
    @SerialName("duration")
    val duration: Double? = null,
    @SerialName("cover_frame_timestamp")
    val coverFrameTimestamp: Double? = null,
    @SerialName("is_animation")
    val isAnimation: Boolean? = null
) : InputStoryContent

@Serializable
data class InputTextMessageContent(
    @SerialName("message_text")
    val messageText: String,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    @SerialName("entities")
    val entities: List<MessageEntity>? = null,
    @SerialName("link_preview_options")
    val linkPreviewOptions: LinkPreviewOptions? = null
) : InputMessageContent

@Serializable
data class InputVenueMessageContent(
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("title")
    val title: String,
    @SerialName("address")
    val address: String,
    @SerialName("foursquare_id")
    val foursquareId: String? = null,
    @SerialName("foursquare_type")
    val foursquareType: String? = null,
    @SerialName("google_place_id")
    val googlePlaceId: String? = null,
    @SerialName("google_place_type")
    val googlePlaceType: String? = null
) : InputMessageContent

@Serializable
data class Invoice(
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("start_parameter")
    val startParameter: String,
    @SerialName("currency")
    val currency: String,
    @SerialName("total_amount")
    val totalAmount: Long
)

@Serializable
data class KeyboardButton(
    @SerialName("text")
    val text: String,
    @SerialName("icon_custom_emoji_id")
    val iconCustomEmojiId: String? = null,
    @SerialName("style")
    val style: String? = null,
    @SerialName("request_users")
    val requestUsers: KeyboardButtonRequestUsers? = null,
    @SerialName("request_chat")
    val requestChat: KeyboardButtonRequestChat? = null,
    @SerialName("request_managed_bot")
    val requestManagedBot: KeyboardButtonRequestManagedBot? = null,
    @SerialName("request_contact")
    val requestContact: Boolean? = null,
    @SerialName("request_location")
    val requestLocation: Boolean? = null,
    @SerialName("request_poll")
    val requestPoll: KeyboardButtonPollType? = null,
    @SerialName("web_app")
    val webApp: WebAppInfo? = null
)

@Serializable
data class KeyboardButtonPollType(
    @SerialName("type")
    val type: String? = null
)

@Serializable
data class KeyboardButtonRequestChat(
    @SerialName("request_id")
    val requestId: Long,
    @SerialName("chat_is_channel")
    val chatIsChannel: Boolean,
    @SerialName("chat_is_forum")
    val chatIsForum: Boolean? = null,
    @SerialName("chat_has_username")
    val chatHasUsername: Boolean? = null,
    @SerialName("chat_is_created")
    val chatIsCreated: Boolean? = null,
    @SerialName("user_administrator_rights")
    val userAdministratorRights: ChatAdministratorRights? = null,
    @SerialName("bot_administrator_rights")
    val botAdministratorRights: ChatAdministratorRights? = null,
    @SerialName("bot_is_member")
    val botIsMember: Boolean? = null,
    @SerialName("request_title")
    val requestTitle: Boolean? = null,
    @SerialName("request_username")
    val requestUsername: Boolean? = null,
    @SerialName("request_photo")
    val requestPhoto: Boolean? = null
)

@Serializable
data class KeyboardButtonRequestManagedBot(
    @SerialName("request_id")
    val requestId: Long,
    @SerialName("suggested_name")
    val suggestedName: String? = null,
    @SerialName("suggested_username")
    val suggestedUsername: String? = null
)

@Serializable
data class KeyboardButtonRequestUsers(
    @SerialName("request_id")
    val requestId: Long,
    @SerialName("user_is_bot")
    val userIsBot: Boolean? = null,
    @SerialName("user_is_premium")
    val userIsPremium: Boolean? = null,
    @SerialName("max_quantity")
    val maxQuantity: Long? = null,
    @SerialName("request_name")
    val requestName: Boolean? = null,
    @SerialName("request_username")
    val requestUsername: Boolean? = null,
    @SerialName("request_photo")
    val requestPhoto: Boolean? = null
)

@Serializable
data class LabeledPrice(
    @SerialName("label")
    val label: String,
    @SerialName("amount")
    val amount: Long
)
