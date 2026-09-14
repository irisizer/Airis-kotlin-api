package com.airis.api.types

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.jsonPrimitive

/**
 * Telegram Bot API 10.3 types (part 4/4).
 * Auto-generated from https://core.telegram.org/bots/api (Bot API 10.3, Aug 24 2026).
 * Optimized with kotlinx.serialization: explicitNulls=false, ignoreUnknownKeys=true.
 */

@Serializable
data class RichBlockTableCell(
    @SerialName("text")
    val text: RichText? = null,
    @SerialName("is_header")
    val isHeader: Boolean? = null,
    @SerialName("colspan")
    val colspan: Long? = null,
    @SerialName("rowspan")
    val rowspan: Long? = null,
    @SerialName("align")
    val align: String,
    @SerialName("valign")
    val valign: String
)

@Serializable
data class RichBlockThinking(
    @SerialName("type")
    val type: String = "thinking",
    @SerialName("text")
    val text: RichText
) : RichBlock

@Serializable
data class RichBlockVideo(
    @SerialName("type")
    val type: String = "video",
    @SerialName("video")
    val video: Video,
    @SerialName("has_spoiler")
    val hasSpoiler: Boolean? = null,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : RichBlock

@Serializable
data class RichBlockVoiceNote(
    @SerialName("type")
    val type: String = "voice_note",
    @SerialName("voice_note")
    val voiceNote: Voice,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : RichBlock

@Serializable
data class RichMessage(
    @SerialName("blocks")
    val blocks: List<RichBlock>,
    @SerialName("is_rtl")
    val isRtl: Boolean? = null
)

@Serializable
data class RichMessageButton(
    @SerialName("text")
    val text: RichText,
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
    @SerialName("disabled")
    val disabled: DisabledButton? = null
)

@Serializable
data class RichTextAnchor(
    @SerialName("type")
    val type: String = "anchor",
    @SerialName("name")
    val name: String
) : RichText

@Serializable
data class RichTextAnchorLink(
    @SerialName("type")
    val type: String = "anchor_link",
    @SerialName("text")
    val text: RichText,
    @SerialName("anchor_name")
    val anchorName: String
) : RichText

/**
 * Plain-text span: on the wire RichText can be a bare String
 * (https://core.telegram.org/bots/api#richtext — "a String for plain text").
 * Custom serializer reads/writes the raw string (not an object).
 */
@Serializable(with = RichTextPlainSerializer::class)
data class RichTextPlain(
    val text: String
) : RichText

object RichTextPlainSerializer : KSerializer<RichTextPlain> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("RichTextPlain", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: RichTextPlain) {
        encoder.encodeString(value.text)
    }

    override fun deserialize(decoder: Decoder): RichTextPlain {
        require(decoder is JsonDecoder)
        return RichTextPlain(decoder.decodeJsonElement().jsonPrimitive.content)
    }
}

/**
 * Nested span list: on the wire RichText can be an Array of RichText
 * (https://core.telegram.org/bots/api#richtext). Decoded recursively.
 */
@Serializable(with = RichTextArraySerializer::class)
data class RichTextArray(
    val items: List<RichText>
) : RichText

object RichTextArraySerializer : KSerializer<RichTextArray> {
    override val descriptor: SerialDescriptor =
        SerialDescriptor("RichTextArray", kotlinx.serialization.builtins.ListSerializer(RichTextSerializer).descriptor)

    override fun serialize(encoder: Encoder, value: RichTextArray) {
        require(encoder is JsonEncoder)
        encoder.encodeJsonElement(
            JsonArray(value.items.map { encoder.json.encodeToJsonElement(RichTextSerializer, it) })
        )
    }

    override fun deserialize(decoder: Decoder): RichTextArray {
        require(decoder is JsonDecoder)
        val arr = decoder.decodeJsonElement() as? JsonArray
            ?: throw IllegalArgumentException("Expected JsonArray for RichText")
        return RichTextArray(arr.map { decoder.json.decodeFromJsonElement(RichTextSerializer, it) })
    }
}

@Serializable
data class RichTextBankCardNumber(
    @SerialName("type")
    val type: String = "bank_card_number",
    @SerialName("text")
    val text: RichText,
    @SerialName("bank_card_number")
    val bankCardNumber: String
) : RichText

@Serializable
data class RichTextBold(
    @SerialName("type")
    val type: String = "bold",
    @SerialName("text")
    val text: RichText
) : RichText

@Serializable
data class RichTextBotCommand(
    @SerialName("type")
    val type: String = "bot_command",
    @SerialName("text")
    val text: RichText,
    @SerialName("bot_command")
    val botCommand: String
) : RichText

@Serializable
data class RichTextButton(
    @SerialName("type")
    val type: String = "button",
    @SerialName("button")
    val button: RichMessageButton
) : RichText

@Serializable
data class RichTextCashtag(
    @SerialName("type")
    val type: String = "cashtag",
    @SerialName("text")
    val text: RichText,
    @SerialName("cashtag")
    val cashtag: String
) : RichText

@Serializable
data class RichTextCode(
    @SerialName("type")
    val type: String = "code",
    @SerialName("text")
    val text: RichText
) : RichText

@Serializable
data class RichTextCustomEmoji(
    @SerialName("type")
    val type: String = "custom_emoji",
    @SerialName("custom_emoji_id")
    val customEmojiId: String,
    @SerialName("alternative_text")
    val alternativeText: String
) : RichText

@Serializable
data class RichTextDateTime(
    @SerialName("type")
    val type: String = "date_time",
    @SerialName("text")
    val text: RichText,
    @SerialName("unix_time")
    val unixTime: Long,
    @SerialName("date_time_format")
    val dateTimeFormat: String
) : RichText

@Serializable
data class RichTextEmailAddress(
    @SerialName("type")
    val type: String = "email_address",
    @SerialName("text")
    val text: RichText,
    @SerialName("email_address")
    val emailAddress: String
) : RichText

@Serializable
data class RichTextHashtag(
    @SerialName("type")
    val type: String = "hashtag",
    @SerialName("text")
    val text: RichText,
    @SerialName("hashtag")
    val hashtag: String
) : RichText

@Serializable
data class RichTextItalic(
    @SerialName("type")
    val type: String = "italic",
    @SerialName("text")
    val text: RichText
) : RichText

@Serializable
data class RichTextMarked(
    @SerialName("type")
    val type: String = "marked",
    @SerialName("text")
    val text: RichText
) : RichText

@Serializable
data class RichTextMathematicalExpression(
    @SerialName("type")
    val type: String = "mathematical_expression",
    @SerialName("expression")
    val expression: String
) : RichText

@Serializable
data class RichTextMention(
    @SerialName("type")
    val type: String = "mention",
    @SerialName("text")
    val text: RichText,
    @SerialName("username")
    val username: String
) : RichText

@Serializable
data class RichTextPhoneNumber(
    @SerialName("type")
    val type: String = "phone_number",
    @SerialName("text")
    val text: RichText,
    @SerialName("phone_number")
    val phoneNumber: String
) : RichText

@Serializable
data class RichTextReference(
    @SerialName("type")
    val type: String = "reference",
    @SerialName("text")
    val text: RichText,
    @SerialName("name")
    val name: String
) : RichText

@Serializable
data class RichTextReferenceLink(
    @SerialName("type")
    val type: String = "reference_link",
    @SerialName("text")
    val text: RichText,
    @SerialName("reference_name")
    val referenceName: String
) : RichText

@Serializable
data class RichTextSpoiler(
    @SerialName("type")
    val type: String = "spoiler",
    @SerialName("text")
    val text: RichText
) : RichText

@Serializable
data class RichTextStrikethrough(
    @SerialName("type")
    val type: String = "strikethrough",
    @SerialName("text")
    val text: RichText
) : RichText

@Serializable
data class RichTextSubscript(
    @SerialName("type")
    val type: String = "subscript",
    @SerialName("text")
    val text: RichText
) : RichText

@Serializable
data class RichTextSuperscript(
    @SerialName("type")
    val type: String = "superscript",
    @SerialName("text")
    val text: RichText
) : RichText

@Serializable
data class RichTextTextMention(
    @SerialName("type")
    val type: String = "text_mention",
    @SerialName("text")
    val text: RichText,
    @SerialName("user")
    val user: User
) : RichText

@Serializable
data class RichTextUnderline(
    @SerialName("type")
    val type: String = "underline",
    @SerialName("text")
    val text: RichText
) : RichText

@Serializable
data class RichTextUrl(
    @SerialName("type")
    val type: String = "url",
    @SerialName("text")
    val text: RichText,
    @SerialName("url")
    val url: String
) : RichText

@Serializable
data class SentGuestMessage(
    @SerialName("inline_message_id")
    val inlineMessageId: String
)

@Serializable
data class SentWebAppMessage(
    @SerialName("inline_message_id")
    val inlineMessageId: String? = null
)

@Serializable
data class SharedUser(
    @SerialName("user_id")
    val userId: Long,
    @SerialName("first_name")
    val firstName: String? = null,
    @SerialName("last_name")
    val lastName: String? = null,
    @SerialName("username")
    val username: String? = null,
    @SerialName("photo")
    val photo: List<PhotoSize>? = null
)

@Serializable
data class ShippingAddress(
    @SerialName("country_code")
    val countryCode: String,
    @SerialName("state")
    val state: String,
    @SerialName("city")
    val city: String,
    @SerialName("street_line1")
    val streetLine1: String,
    @SerialName("street_line2")
    val streetLine2: String,
    @SerialName("post_code")
    val postCode: String
)

@Serializable
data class ShippingOption(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("prices")
    val prices: List<LabeledPrice>
)

@Serializable
data class ShippingQuery(
    @SerialName("id")
    val id: String,
    @SerialName("from")
    val from: User,
    @SerialName("invoice_payload")
    val invoicePayload: String,
    @SerialName("shipping_address")
    val shippingAddress: ShippingAddress
)

@Serializable
data class StarAmount(
    @SerialName("amount")
    val amount: Long,
    @SerialName("nanostar_amount")
    val nanostarAmount: Long? = null
)

@Serializable
data class StarTransaction(
    @SerialName("id")
    val id: String,
    @SerialName("amount")
    val amount: Long,
    @SerialName("nanostar_amount")
    val nanostarAmount: Long? = null,
    @SerialName("date")
    val date: Long,
    @SerialName("source")
    val source: TransactionPartner? = null,
    @SerialName("receiver")
    val `receiver`: TransactionPartner? = null
)

@Serializable
data class StarTransactions(
    @SerialName("transactions")
    val transactions: List<StarTransaction>
)

@Serializable
data class Sticker(
    @SerialName("file_id")
    val fileId: String,
    @SerialName("file_unique_id")
    val fileUniqueId: String,
    @SerialName("type")
    val type: String,
    @SerialName("width")
    val width: Long,
    @SerialName("height")
    val height: Long,
    @SerialName("is_animated")
    val isAnimated: Boolean,
    @SerialName("is_video")
    val isVideo: Boolean,
    @SerialName("thumbnail")
    val thumbnail: PhotoSize? = null,
    @SerialName("emoji")
    val emoji: String? = null,
    @SerialName("set_name")
    val setName: String? = null,
    @SerialName("premium_animation")
    val premiumAnimation: File? = null,
    @SerialName("mask_position")
    val maskPosition: MaskPosition? = null,
    @SerialName("custom_emoji_id")
    val customEmojiId: String? = null,
    @SerialName("needs_repainting")
    val needsRepainting: Boolean? = null,
    @SerialName("file_size")
    val fileSize: Long? = null
)

@Serializable
data class StickerSet(
    @SerialName("name")
    val name: String,
    @SerialName("title")
    val title: String,
    @SerialName("sticker_type")
    val stickerType: String,
    @SerialName("stickers")
    val stickers: List<Sticker>,
    @SerialName("thumbnail")
    val thumbnail: PhotoSize? = null
)

@Serializable
data class Story(
    @SerialName("chat")
    val chat: Chat,
    @SerialName("id")
    val id: Long
)

@Serializable
data class StoryArea(
    @SerialName("position")
    val position: StoryAreaPosition,
    @SerialName("type")
    val type: StoryAreaType
)

@Serializable
data class StoryAreaPosition(
    @SerialName("x_percentage")
    val xPercentage: Double,
    @SerialName("y_percentage")
    val yPercentage: Double,
    @SerialName("width_percentage")
    val widthPercentage: Double,
    @SerialName("height_percentage")
    val heightPercentage: Double,
    @SerialName("rotation_angle")
    val rotationAngle: Double,
    @SerialName("corner_radius_percentage")
    val cornerRadiusPercentage: Double
)

@Serializable
data class StoryAreaTypeLink(
    @SerialName("type")
    val type: String = "link",
    @SerialName("url")
    val url: String
) : StoryAreaType

@Serializable
data class StoryAreaTypeLocation(
    @SerialName("type")
    val type: String = "location",
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("address")
    val address: LocationAddress? = null
) : StoryAreaType

@Serializable
data class StoryAreaTypeSuggestedReaction(
    @SerialName("type")
    val type: String = "suggested_reaction",
    @SerialName("reaction_type")
    val reactionType: ReactionType,
    @SerialName("is_dark")
    val isDark: Boolean? = null,
    @SerialName("is_flipped")
    val isFlipped: Boolean? = null
) : StoryAreaType

@Serializable
data class StoryAreaTypeUniqueGift(
    @SerialName("type")
    val type: String = "unique_gift",
    @SerialName("name")
    val name: String
) : StoryAreaType

@Serializable
data class StoryAreaTypeWeather(
    @SerialName("type")
    val type: String = "weather",
    @SerialName("temperature")
    val temperature: Double,
    @SerialName("emoji")
    val emoji: String,
    @SerialName("background_color")
    val backgroundColor: Long
) : StoryAreaType

@Serializable
data class SuccessfulPayment(
    @SerialName("currency")
    val currency: String,
    @SerialName("total_amount")
    val totalAmount: Long,
    @SerialName("invoice_payload")
    val invoicePayload: String,
    @SerialName("subscription_expiration_date")
    val subscriptionExpirationDate: Long? = null,
    @SerialName("is_recurring")
    val isRecurring: Boolean? = null,
    @SerialName("is_first_recurring")
    val isFirstRecurring: Boolean? = null,
    @SerialName("shipping_option_id")
    val shippingOptionId: String? = null,
    @SerialName("order_info")
    val orderInfo: OrderInfo? = null,
    @SerialName("telegram_payment_charge_id")
    val telegramPaymentChargeId: String,
    @SerialName("provider_payment_charge_id")
    val providerPaymentChargeId: String
)

@Serializable
data class SuggestedPostApprovalFailed(
    @SerialName("suggested_post_message")
    val suggestedPostMessage: Message? = null,
    @SerialName("price")
    val price: SuggestedPostPrice
)

@Serializable
data class SuggestedPostApproved(
    @SerialName("suggested_post_message")
    val suggestedPostMessage: Message? = null,
    @SerialName("price")
    val price: SuggestedPostPrice? = null,
    @SerialName("send_date")
    val sendDate: Long
)

@Serializable
data class SuggestedPostDeclined(
    @SerialName("suggested_post_message")
    val suggestedPostMessage: Message? = null,
    @SerialName("comment")
    val comment: String? = null
)

@Serializable
data class SuggestedPostInfo(
    @SerialName("state")
    val state: String,
    @SerialName("price")
    val price: SuggestedPostPrice? = null,
    @SerialName("send_date")
    val sendDate: Long? = null
)

@Serializable
data class SuggestedPostPaid(
    @SerialName("suggested_post_message")
    val suggestedPostMessage: Message? = null,
    @SerialName("currency")
    val currency: String,
    @SerialName("amount")
    val amount: Long? = null,
    @SerialName("star_amount")
    val starAmount: StarAmount? = null
)

@Serializable
data class SuggestedPostParameters(
    @SerialName("price")
    val price: SuggestedPostPrice? = null,
    @SerialName("send_date")
    val sendDate: Long? = null
)

@Serializable
data class SuggestedPostPrice(
    @SerialName("currency")
    val currency: String,
    @SerialName("amount")
    val amount: Long
)

@Serializable
data class SuggestedPostRefunded(
    @SerialName("suggested_post_message")
    val suggestedPostMessage: Message? = null,
    @SerialName("reason")
    val reason: String
)

@Serializable
data class SwitchInlineQueryChosenChat(
    @SerialName("query")
    val query: String? = null,
    @SerialName("allow_user_chats")
    val allowUserChats: Boolean? = null,
    @SerialName("allow_bot_chats")
    val allowBotChats: Boolean? = null,
    @SerialName("allow_group_chats")
    val allowGroupChats: Boolean? = null,
    @SerialName("allow_channel_chats")
    val allowChannelChats: Boolean? = null
)

@Serializable
data class TextQuote(
    @SerialName("text")
    val text: String,
    @SerialName("entities")
    val entities: List<MessageEntity>? = null,
    @SerialName("position")
    val position: Long,
    @SerialName("is_manual")
    val isManual: Boolean? = null
)

@Serializable
data class TransactionPartnerAffiliateProgram(
    @SerialName("type")
    val type: String = "affiliate_program",
    @SerialName("sponsor_user")
    val sponsorUser: User? = null,
    @SerialName("commission_per_mille")
    val commissionPerMille: Long
) : TransactionPartner

@Serializable
data class TransactionPartnerChat(
    @SerialName("type")
    val type: String = "chat",
    @SerialName("chat")
    val chat: Chat,
    @SerialName("gift")
    val gift: Gift? = null
) : TransactionPartner

@Serializable
data class TransactionPartnerFragment(
    @SerialName("type")
    val type: String = "fragment",
    @SerialName("withdrawal_state")
    val withdrawalState: RevenueWithdrawalState? = null
) : TransactionPartner

@Serializable
data class TransactionPartnerOther(
    @SerialName("type")
    val type: String = "other"
) : TransactionPartner

@Serializable
data class TransactionPartnerTelegramAds(
    @SerialName("type")
    val type: String = "telegram_ads"
) : TransactionPartner

@Serializable
data class TransactionPartnerTelegramApi(
    @SerialName("type")
    val type: String = "telegram_api",
    @SerialName("request_count")
    val requestCount: Long
) : TransactionPartner

@Serializable
data class TransactionPartnerUser(
    @SerialName("type")
    val type: String = "user",
    @SerialName("transaction_type")
    val transactionType: String,
    @SerialName("user")
    val user: User,
    @SerialName("affiliate")
    val affiliate: AffiliateInfo? = null,
    @SerialName("invoice_payload")
    val invoicePayload: String? = null,
    @SerialName("subscription_period")
    val subscriptionPeriod: Long? = null,
    @SerialName("paid_media")
    val paidMedia: List<PaidMedia>? = null,
    @SerialName("paid_media_payload")
    val paidMediaPayload: String? = null,
    @SerialName("gift")
    val gift: Gift? = null,
    @SerialName("premium_subscription_duration")
    val premiumSubscriptionDuration: Long? = null
) : TransactionPartner

@Serializable
data class UniqueGift(
    @SerialName("gift_id")
    val giftId: String,
    @SerialName("base_name")
    val baseName: String,
    @SerialName("name")
    val name: String,
    @SerialName("number")
    val number: Long,
    @SerialName("model")
    val model: UniqueGiftModel,
    @SerialName("symbol")
    val symbol: UniqueGiftSymbol,
    @SerialName("backdrop")
    val backdrop: UniqueGiftBackdrop,
    @SerialName("is_premium")
    val isPremium: Boolean? = null,
    @SerialName("is_burned")
    val isBurned: Boolean? = null,
    @SerialName("is_from_blockchain")
    val isFromBlockchain: Boolean? = null,
    @SerialName("colors")
    val colors: UniqueGiftColors? = null,
    @SerialName("publisher_chat")
    val publisherChat: Chat? = null
)

@Serializable
data class UniqueGiftBackdrop(
    @SerialName("name")
    val name: String,
    @SerialName("colors")
    val colors: UniqueGiftBackdropColors,
    @SerialName("rarity_per_mille")
    val rarityPerMille: Long
)

@Serializable
data class UniqueGiftBackdropColors(
    @SerialName("center_color")
    val centerColor: Long,
    @SerialName("edge_color")
    val edgeColor: Long,
    @SerialName("symbol_color")
    val symbolColor: Long,
    @SerialName("text_color")
    val textColor: Long
)

@Serializable
data class UniqueGiftColors(
    @SerialName("model_custom_emoji_id")
    val modelCustomEmojiId: String,
    @SerialName("symbol_custom_emoji_id")
    val symbolCustomEmojiId: String,
    @SerialName("light_theme_main_color")
    val lightThemeMainColor: Long,
    @SerialName("light_theme_other_colors")
    val lightThemeOtherColors: List<Long>,
    @SerialName("dark_theme_main_color")
    val darkThemeMainColor: Long,
    @SerialName("dark_theme_other_colors")
    val darkThemeOtherColors: List<Long>
)

@Serializable
data class UniqueGiftInfo(
    @SerialName("gift")
    val gift: UniqueGift,
    @SerialName("origin")
    val origin: String,
    @SerialName("text")
    val text: String? = null,
    @SerialName("entities")
    val entities: List<MessageEntity>? = null,
    @SerialName("is_private")
    val isPrivate: Boolean? = null,
    @SerialName("last_resale_currency")
    val lastResaleCurrency: String? = null,
    @SerialName("last_resale_amount")
    val lastResaleAmount: Long? = null,
    @SerialName("owned_gift_id")
    val ownedGiftId: String? = null,
    @SerialName("transfer_star_count")
    val transferStarCount: Long? = null,
    @SerialName("next_transfer_date")
    val nextTransferDate: Long? = null
)

@Serializable
data class UniqueGiftModel(
    @SerialName("name")
    val name: String,
    @SerialName("sticker")
    val sticker: Sticker,
    @SerialName("rarity_per_mille")
    val rarityPerMille: Long,
    @SerialName("rarity")
    val rarity: String? = null
)

@Serializable
data class UniqueGiftSymbol(
    @SerialName("name")
    val name: String,
    @SerialName("sticker")
    val sticker: Sticker,
    @SerialName("rarity_per_mille")
    val rarityPerMille: Long
)

@Serializable
data class Update(
    @SerialName("update_id")
    val updateId: Long,
    @SerialName("message")
    val message: Message? = null,
    @SerialName("edited_message")
    val editedMessage: Message? = null,
    @SerialName("channel_post")
    val channelPost: Message? = null,
    @SerialName("edited_channel_post")
    val editedChannelPost: Message? = null,
    @SerialName("business_connection")
    val businessConnection: BusinessConnection? = null,
    @SerialName("business_message")
    val businessMessage: Message? = null,
    @SerialName("edited_business_message")
    val editedBusinessMessage: Message? = null,
    @SerialName("deleted_business_messages")
    val deletedBusinessMessages: BusinessMessagesDeleted? = null,
    @SerialName("guest_message")
    val guestMessage: Message? = null,
    @SerialName("message_reaction")
    val messageReaction: MessageReactionUpdated? = null,
    @SerialName("message_reaction_count")
    val messageReactionCount: MessageReactionCountUpdated? = null,
    @SerialName("inline_query")
    val inlineQuery: InlineQuery? = null,
    @SerialName("chosen_inline_result")
    val chosenInlineResult: ChosenInlineResult? = null,
    @SerialName("callback_query")
    val callbackQuery: CallbackQuery? = null,
    @SerialName("shipping_query")
    val shippingQuery: ShippingQuery? = null,
    @SerialName("pre_checkout_query")
    val preCheckoutQuery: PreCheckoutQuery? = null,
    @SerialName("purchased_paid_media")
    val purchasedPaidMedia: PaidMediaPurchased? = null,
    @SerialName("poll")
    val poll: Poll? = null,
    @SerialName("poll_answer")
    val pollAnswer: PollAnswer? = null,
    @SerialName("my_chat_member")
    val myChatMember: ChatMemberUpdated? = null,
    @SerialName("chat_member")
    val chatMember: ChatMemberUpdated? = null,
    @SerialName("chat_join_request")
    val chatJoinRequest: ChatJoinRequest? = null,
    @SerialName("chat_boost")
    val chatBoost: ChatBoostUpdated? = null,
    @SerialName("removed_chat_boost")
    val removedChatBoost: ChatBoostRemoved? = null,
    @SerialName("managed_bot")
    val managedBot: ManagedBotUpdated? = null,
    @SerialName("subscription")
    val subscription: BotSubscriptionUpdated? = null,
    @SerialName("stopped_message_generation")
    val stoppedMessageGeneration: MessageGenerationStopped? = null
)

@Serializable
data class User(
    @SerialName("id")
    val id: Long,
    @SerialName("is_bot")
    val isBot: Boolean,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String? = null,
    @SerialName("username")
    val username: String? = null,
    @SerialName("language_code")
    val languageCode: String? = null,
    @SerialName("is_premium")
    val isPremium: Boolean? = null,
    @SerialName("added_to_attachment_menu")
    val addedToAttachmentMenu: Boolean? = null,
    @SerialName("can_join_groups")
    val canJoinGroups: Boolean? = null,
    @SerialName("can_read_all_group_messages")
    val canReadAllGroupMessages: Boolean? = null,
    @SerialName("supports_guest_queries")
    val supportsGuestQueries: Boolean? = null,
    @SerialName("supports_inline_queries")
    val supportsInlineQueries: Boolean? = null,
    @SerialName("can_connect_to_business")
    val canConnectToBusiness: Boolean? = null,
    @SerialName("has_main_web_app")
    val hasMainWebApp: Boolean? = null,
    @SerialName("has_topics_enabled")
    val hasTopicsEnabled: Boolean? = null,
    @SerialName("allows_users_to_create_topics")
    val allowsUsersToCreateTopics: Boolean? = null,
    @SerialName("can_manage_bots")
    val canManageBots: Boolean? = null,
    @SerialName("supports_join_request_queries")
    val supportsJoinRequestQueries: Boolean? = null
)

@Serializable
data class UserChatBoosts(
    @SerialName("boosts")
    val boosts: List<ChatBoost>
)

@Serializable
data class UserProfileAudios(
    @SerialName("total_count")
    val totalCount: Long,
    @SerialName("audios")
    val audios: List<Audio>
)

@Serializable
data class UserProfilePhotos(
    @SerialName("total_count")
    val totalCount: Long,
    @SerialName("photos")
    val photos: List<List<PhotoSize>>
)

@Serializable
data class UserRating(
    @SerialName("level")
    val level: Long,
    @SerialName("rating")
    val rating: Long,
    @SerialName("current_level_rating")
    val currentLevelRating: Long,
    @SerialName("next_level_rating")
    val nextLevelRating: Long? = null
)

@Serializable
data class UsersShared(
    @SerialName("request_id")
    val requestId: Long,
    @SerialName("users")
    val users: List<SharedUser>
)

@Serializable
data class Venue(
    @SerialName("location")
    val location: Location,
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
)

@Serializable
data class Video(
    @SerialName("file_id")
    val fileId: String,
    @SerialName("file_unique_id")
    val fileUniqueId: String,
    @SerialName("width")
    val width: Long,
    @SerialName("height")
    val height: Long,
    @SerialName("duration")
    val duration: Long,
    @SerialName("thumbnail")
    val thumbnail: PhotoSize? = null,
    @SerialName("cover")
    val cover: List<PhotoSize>? = null,
    @SerialName("start_timestamp")
    val startTimestamp: Long? = null,
    @SerialName("qualities")
    val qualities: List<VideoQuality>? = null,
    @SerialName("file_name")
    val fileName: String? = null,
    @SerialName("mime_type")
    val mimeType: String? = null,
    @SerialName("file_size")
    val fileSize: Long? = null
)

@Serializable
data class VideoChatEnded(
    @SerialName("duration")
    val duration: Long
)

@Serializable
data class VideoChatParticipantsInvited(
    @SerialName("users")
    val users: List<User>
)

@Serializable
data class VideoChatScheduled(
    @SerialName("start_date")
    val startDate: Long
)

@Serializable
data class VideoChatStarted(
    @SerialName("_placeholder")
    val placeholder: String? = null
)

@Serializable
data class VideoNote(
    @SerialName("file_id")
    val fileId: String,
    @SerialName("file_unique_id")
    val fileUniqueId: String,
    @SerialName("length")
    val length: Long,
    @SerialName("duration")
    val duration: Long,
    @SerialName("thumbnail")
    val thumbnail: PhotoSize? = null,
    @SerialName("file_size")
    val fileSize: Long? = null
)

@Serializable
data class VideoQuality(
    @SerialName("file_id")
    val fileId: String,
    @SerialName("file_unique_id")
    val fileUniqueId: String,
    @SerialName("width")
    val width: Long,
    @SerialName("height")
    val height: Long,
    @SerialName("codec")
    val codec: String,
    @SerialName("file_size")
    val fileSize: Long? = null
)

@Serializable
data class Voice(
    @SerialName("file_id")
    val fileId: String,
    @SerialName("file_unique_id")
    val fileUniqueId: String,
    @SerialName("duration")
    val duration: Long,
    @SerialName("mime_type")
    val mimeType: String? = null,
    @SerialName("file_size")
    val fileSize: Long? = null
)

@Serializable
data class WebAppData(
    @SerialName("data")
    val `data`: String,
    @SerialName("button_text")
    val buttonText: String
)

@Serializable
data class WebAppInfo(
    @SerialName("url")
    val url: String
)

@Serializable
data class WebhookInfo(
    @SerialName("url")
    val url: String,
    @SerialName("has_custom_certificate")
    val hasCustomCertificate: Boolean,
    @SerialName("pending_update_count")
    val pendingUpdateCount: Long,
    @SerialName("ip_address")
    val ipAddress: String? = null,
    @SerialName("last_error_date")
    val lastErrorDate: Long? = null,
    @SerialName("last_error_message")
    val lastErrorMessage: String? = null,
    @SerialName("last_synchronization_error_date")
    val lastSynchronizationErrorDate: Long? = null,
    @SerialName("max_connections")
    val maxConnections: Long? = null,
    @SerialName("allowed_updates")
    val allowedUpdates: List<String>? = null
)

@Serializable
data class WriteAccessAllowed(
    @SerialName("from_request")
    val fromRequest: Boolean? = null,
    @SerialName("web_app_name")
    val webAppName: String? = null,
    @SerialName("from_attachment_menu")
    val fromAttachmentMenu: Boolean? = null
)
