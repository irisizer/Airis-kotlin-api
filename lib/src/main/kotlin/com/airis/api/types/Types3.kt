package com.airis.api.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Telegram Bot API 10.3 types (part 3/4).
 * Auto-generated from https://core.telegram.org/bots/api (Bot API 10.3, Aug 24 2026).
 * Optimized with kotlinx.serialization: explicitNulls=false, ignoreUnknownKeys=true.
 */

@Serializable
data class Link(
    @SerialName("url")
    val url: String
)

@Serializable
data class LinkPreviewOptions(
    @SerialName("is_disabled")
    val isDisabled: Boolean? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("prefer_small_media")
    val preferSmallMedia: Boolean? = null,
    @SerialName("prefer_large_media")
    val preferLargeMedia: Boolean? = null,
    @SerialName("show_above_text")
    val showAboveText: Boolean? = null
)

@Serializable
data class LivePhoto(
    @SerialName("photo")
    val photo: List<PhotoSize>? = null,
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
    @SerialName("mime_type")
    val mimeType: String? = null,
    @SerialName("file_size")
    val fileSize: Long? = null
)

@Serializable
data class Location(
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
)

@Serializable
data class LocationAddress(
    @SerialName("country_code")
    val countryCode: String,
    @SerialName("state")
    val state: String? = null,
    @SerialName("city")
    val city: String? = null,
    @SerialName("street")
    val street: String? = null
)

@Serializable
data class LoginUrl(
    @SerialName("url")
    val url: String,
    @SerialName("forward_text")
    val forwardText: String? = null,
    @SerialName("bot_username")
    val botUsername: String? = null,
    @SerialName("request_write_access")
    val requestWriteAccess: Boolean? = null
)

@Serializable
data class ManagedBotCreated(
    @SerialName("bot")
    val bot: User
)

@Serializable
data class ManagedBotUpdated(
    @SerialName("user")
    val user: User,
    @SerialName("bot")
    val bot: User
)

@Serializable
data class MaskPosition(
    @SerialName("point")
    val point: String,
    @SerialName("x_shift")
    val xShift: Double,
    @SerialName("y_shift")
    val yShift: Double,
    @SerialName("scale")
    val scale: Double
)

@Serializable
data class MenuButtonCommands(
    @SerialName("type")
    val type: String = "commands"
) : MenuButton

@Serializable
data class MenuButtonDefault(
    @SerialName("type")
    val type: String = "default"
) : MenuButton

@Serializable
data class MenuButtonWebApp(
    @SerialName("type")
    val type: String = "web_app",
    @SerialName("text")
    val text: String,
    @SerialName("web_app")
    val webApp: WebAppInfo
) : MenuButton

@Serializable
data class Message(
    @SerialName("message_id")
    val messageId: Long,
    @SerialName("message_thread_id")
    val messageThreadId: Long? = null,
    @SerialName("direct_messages_topic")
    val directMessagesTopic: DirectMessagesTopic? = null,
    @SerialName("from")
    val from: User? = null,
    @SerialName("sender_chat")
    val senderChat: Chat? = null,
    @SerialName("sender_boost_count")
    val senderBoostCount: Long? = null,
    @SerialName("sender_business_bot")
    val senderBusinessBot: User? = null,
    @SerialName("sender_tag")
    val senderTag: String? = null,
    @SerialName("receiver_user")
    val receiverUser: User? = null,
    @SerialName("ephemeral_message_id")
    val ephemeralMessageId: Long? = null,
    @SerialName("date")
    val date: Long,
    @SerialName("guest_query_id")
    val guestQueryId: String? = null,
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,
    @SerialName("chat")
    val chat: Chat,
    @SerialName("forward_origin")
    val forwardOrigin: MessageOrigin? = null,
    @SerialName("is_topic_message")
    val isTopicMessage: Boolean? = null,
    @SerialName("is_automatic_forward")
    val isAutomaticForward: Boolean? = null,
    @SerialName("reply_to_message")
    val replyToMessage: Message? = null,
    @SerialName("external_reply")
    val externalReply: ExternalReplyInfo? = null,
    @SerialName("quote")
    val quote: TextQuote? = null,
    @SerialName("reply_to_story")
    val replyToStory: Story? = null,
    @SerialName("reply_to_checklist_task_id")
    val replyToChecklistTaskId: Long? = null,
    @SerialName("reply_to_poll_option_id")
    val replyToPollOptionId: String? = null,
    @SerialName("via_bot")
    val viaBot: User? = null,
    @SerialName("guest_bot_caller_user")
    val guestBotCallerUser: User? = null,
    @SerialName("guest_bot_caller_chat")
    val guestBotCallerChat: Chat? = null,
    @SerialName("edit_date")
    val editDate: Long? = null,
    @SerialName("has_protected_content")
    val hasProtectedContent: Boolean? = null,
    @SerialName("is_from_offline")
    val isFromOffline: Boolean? = null,
    @SerialName("is_paid_post")
    val isPaidPost: Boolean? = null,
    @SerialName("media_group_id")
    val mediaGroupId: String? = null,
    @SerialName("author_signature")
    val authorSignature: String? = null,
    @SerialName("paid_star_count")
    val paidStarCount: Long? = null,
    @SerialName("text")
    val text: String? = null,
    @SerialName("entities")
    val entities: List<MessageEntity>? = null,
    @SerialName("link_preview_options")
    val linkPreviewOptions: LinkPreviewOptions? = null,
    @SerialName("suggested_post_info")
    val suggestedPostInfo: SuggestedPostInfo? = null,
    @SerialName("effect_id")
    val effectId: String? = null,
    @SerialName("rich_message")
    val richMessage: RichMessage? = null,
    @SerialName("animation")
    val animation: Animation? = null,
    @SerialName("audio")
    val audio: Audio? = null,
    @SerialName("document")
    val document: Document? = null,
    @SerialName("live_photo")
    val livePhoto: LivePhoto? = null,
    @SerialName("paid_media")
    val paidMedia: PaidMediaInfo? = null,
    @SerialName("photo")
    val photo: List<PhotoSize>? = null,
    @SerialName("sticker")
    val sticker: Sticker? = null,
    @SerialName("story")
    val story: Story? = null,
    @SerialName("video")
    val video: Video? = null,
    @SerialName("video_note")
    val videoNote: VideoNote? = null,
    @SerialName("voice")
    val voice: Voice? = null,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,
    @SerialName("has_media_spoiler")
    val hasMediaSpoiler: Boolean? = null,
    @SerialName("checklist")
    val checklist: Checklist? = null,
    @SerialName("contact")
    val contact: Contact? = null,
    @SerialName("dice")
    val dice: Dice? = null,
    @SerialName("game")
    val game: Game? = null,
    @SerialName("poll")
    val poll: Poll? = null,
    @SerialName("venue")
    val venue: Venue? = null,
    @SerialName("location")
    val location: Location? = null,
    @SerialName("new_chat_members")
    val newChatMembers: List<User>? = null,
    @SerialName("left_chat_member")
    val leftChatMember: User? = null,
    @SerialName("chat_owner_left")
    val chatOwnerLeft: ChatOwnerLeft? = null,
    @SerialName("chat_owner_changed")
    val chatOwnerChanged: ChatOwnerChanged? = null,
    @SerialName("new_chat_title")
    val newChatTitle: String? = null,
    @SerialName("new_chat_photo")
    val newChatPhoto: List<PhotoSize>? = null,
    @SerialName("delete_chat_photo")
    val deleteChatPhoto: Boolean? = null,
    @SerialName("group_chat_created")
    val groupChatCreated: Boolean? = null,
    @SerialName("supergroup_chat_created")
    val supergroupChatCreated: Boolean? = null,
    @SerialName("channel_chat_created")
    val channelChatCreated: Boolean? = null,
    @SerialName("message_auto_delete_timer_changed")
    val messageAutoDeleteTimerChanged: MessageAutoDeleteTimerChanged? = null,
    @SerialName("migrate_to_chat_id")
    val migrateToChatId: Long? = null,
    @SerialName("migrate_from_chat_id")
    val migrateFromChatId: Long? = null,
    @SerialName("pinned_message")
    val pinnedMessage: MaybeInaccessibleMessage? = null,
    @SerialName("invoice")
    val invoice: Invoice? = null,
    @SerialName("successful_payment")
    val successfulPayment: SuccessfulPayment? = null,
    @SerialName("refunded_payment")
    val refundedPayment: RefundedPayment? = null,
    @SerialName("users_shared")
    val usersShared: UsersShared? = null,
    @SerialName("chat_shared")
    val chatShared: ChatShared? = null,
    @SerialName("gift")
    val gift: GiftInfo? = null,
    @SerialName("unique_gift")
    val uniqueGift: UniqueGiftInfo? = null,
    @SerialName("gift_upgrade_sent")
    val giftUpgradeSent: GiftInfo? = null,
    @SerialName("connected_website")
    val connectedWebsite: String? = null,
    @SerialName("write_access_allowed")
    val writeAccessAllowed: WriteAccessAllowed? = null,
    @SerialName("passport_data")
    val passportData: PassportData? = null,
    @SerialName("proximity_alert_triggered")
    val proximityAlertTriggered: ProximityAlertTriggered? = null,
    @SerialName("boost_added")
    val boostAdded: ChatBoostAdded? = null,
    @SerialName("chat_background_set")
    val chatBackgroundSet: ChatBackground? = null,
    @SerialName("checklist_tasks_done")
    val checklistTasksDone: ChecklistTasksDone? = null,
    @SerialName("checklist_tasks_added")
    val checklistTasksAdded: ChecklistTasksAdded? = null,
    @SerialName("community_chat_added")
    val communityChatAdded: CommunityChatAdded? = null,
    @SerialName("community_chat_joined")
    val communityChatJoined: CommunityChatJoined? = null,
    @SerialName("community_chat_removed")
    val communityChatRemoved: CommunityChatRemoved? = null,
    @SerialName("direct_message_price_changed")
    val directMessagePriceChanged: DirectMessagePriceChanged? = null,
    @SerialName("forum_topic_created")
    val forumTopicCreated: ForumTopicCreated? = null,
    @SerialName("forum_topic_edited")
    val forumTopicEdited: ForumTopicEdited? = null,
    @SerialName("forum_topic_closed")
    val forumTopicClosed: ForumTopicClosed? = null,
    @SerialName("forum_topic_reopened")
    val forumTopicReopened: ForumTopicReopened? = null,
    @SerialName("general_forum_topic_hidden")
    val generalForumTopicHidden: GeneralForumTopicHidden? = null,
    @SerialName("general_forum_topic_unhidden")
    val generalForumTopicUnhidden: GeneralForumTopicUnhidden? = null,
    @SerialName("giveaway_created")
    val giveawayCreated: GiveawayCreated? = null,
    @SerialName("giveaway")
    val giveaway: Giveaway? = null,
    @SerialName("giveaway_winners")
    val giveawayWinners: GiveawayWinners? = null,
    @SerialName("giveaway_completed")
    val giveawayCompleted: GiveawayCompleted? = null,
    @SerialName("managed_bot_created")
    val managedBotCreated: ManagedBotCreated? = null,
    @SerialName("paid_message_price_changed")
    val paidMessagePriceChanged: PaidMessagePriceChanged? = null,
    @SerialName("poll_option_added")
    val pollOptionAdded: PollOptionAdded? = null,
    @SerialName("poll_option_deleted")
    val pollOptionDeleted: PollOptionDeleted? = null,
    @SerialName("suggested_post_approved")
    val suggestedPostApproved: SuggestedPostApproved? = null,
    @SerialName("suggested_post_approval_failed")
    val suggestedPostApprovalFailed: SuggestedPostApprovalFailed? = null,
    @SerialName("suggested_post_declined")
    val suggestedPostDeclined: SuggestedPostDeclined? = null,
    @SerialName("suggested_post_paid")
    val suggestedPostPaid: SuggestedPostPaid? = null,
    @SerialName("suggested_post_refunded")
    val suggestedPostRefunded: SuggestedPostRefunded? = null,
    @SerialName("video_chat_scheduled")
    val videoChatScheduled: VideoChatScheduled? = null,
    @SerialName("video_chat_started")
    val videoChatStarted: VideoChatStarted? = null,
    @SerialName("video_chat_ended")
    val videoChatEnded: VideoChatEnded? = null,
    @SerialName("video_chat_participants_invited")
    val videoChatParticipantsInvited: VideoChatParticipantsInvited? = null,
    @SerialName("web_app_data")
    val webAppData: WebAppData? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null
) : MaybeInaccessibleMessage

@Serializable
data class MessageAutoDeleteTimerChanged(
    @SerialName("message_auto_delete_time")
    val messageAutoDeleteTime: Long
)

@Serializable
data class MessageEntity(
    @SerialName("type")
    val type: String,
    @SerialName("offset")
    val offset: Long,
    @SerialName("length")
    val length: Long,
    @SerialName("url")
    val url: String? = null,
    @SerialName("user")
    val user: User? = null,
    @SerialName("language")
    val language: String? = null,
    @SerialName("custom_emoji_id")
    val customEmojiId: String? = null,
    @SerialName("unix_time")
    val unixTime: Long? = null,
    @SerialName("date_time_format")
    val dateTimeFormat: String? = null
)

@Serializable
data class MessageGenerationStopped(
    @SerialName("chat")
    val chat: Chat,
    @SerialName("message_thread_id")
    val messageThreadId: Long? = null,
    @SerialName("draft_id")
    val draftId: Long
)

@Serializable
data class MessageId(
    @SerialName("message_id")
    val messageId: Long
)

@Serializable
data class MessageOriginChannel(
    @SerialName("type")
    val type: String = "channel",
    @SerialName("date")
    val date: Long,
    @SerialName("chat")
    val chat: Chat,
    @SerialName("message_id")
    val messageId: Long,
    @SerialName("author_signature")
    val authorSignature: String? = null
) : MessageOrigin

@Serializable
data class MessageOriginChat(
    @SerialName("type")
    val type: String = "chat",
    @SerialName("date")
    val date: Long,
    @SerialName("sender_chat")
    val senderChat: Chat,
    @SerialName("author_signature")
    val authorSignature: String? = null
) : MessageOrigin

@Serializable
data class MessageOriginHiddenUser(
    @SerialName("type")
    val type: String = "hidden_user",
    @SerialName("date")
    val date: Long,
    @SerialName("sender_user_name")
    val senderUserName: String
) : MessageOrigin

@Serializable
data class MessageOriginUser(
    @SerialName("type")
    val type: String = "user",
    @SerialName("date")
    val date: Long,
    @SerialName("sender_user")
    val senderUser: User
) : MessageOrigin

@Serializable
data class MessageReactionCountUpdated(
    @SerialName("chat")
    val chat: Chat,
    @SerialName("message_id")
    val messageId: Long,
    @SerialName("date")
    val date: Long,
    @SerialName("reactions")
    val reactions: List<ReactionCount>
)

@Serializable
data class MessageReactionUpdated(
    @SerialName("chat")
    val chat: Chat,
    @SerialName("message_id")
    val messageId: Long,
    @SerialName("user")
    val user: User? = null,
    @SerialName("actor_chat")
    val actorChat: Chat? = null,
    @SerialName("date")
    val date: Long,
    @SerialName("old_reaction")
    val oldReaction: List<ReactionType>,
    @SerialName("new_reaction")
    val newReaction: List<ReactionType>
)

@Serializable
data class OrderInfo(
    @SerialName("name")
    val name: String? = null,
    @SerialName("phone_number")
    val phoneNumber: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("shipping_address")
    val shippingAddress: ShippingAddress? = null
)

@Serializable
data class OwnedGiftRegular(
    @SerialName("type")
    val type: String = "regular",
    @SerialName("gift")
    val gift: Gift,
    @SerialName("owned_gift_id")
    val ownedGiftId: String? = null,
    @SerialName("sender_user")
    val senderUser: User? = null,
    @SerialName("send_date")
    val sendDate: Long,
    @SerialName("text")
    val text: String? = null,
    @SerialName("entities")
    val entities: List<MessageEntity>? = null,
    @SerialName("is_private")
    val isPrivate: Boolean? = null,
    @SerialName("is_saved")
    val isSaved: Boolean? = null,
    @SerialName("can_be_upgraded")
    val canBeUpgraded: Boolean? = null,
    @SerialName("was_refunded")
    val wasRefunded: Boolean? = null,
    @SerialName("convert_star_count")
    val convertStarCount: Long? = null,
    @SerialName("prepaid_upgrade_star_count")
    val prepaidUpgradeStarCount: Long? = null,
    @SerialName("is_upgrade_separate")
    val isUpgradeSeparate: Boolean? = null,
    @SerialName("unique_gift_number")
    val uniqueGiftNumber: Long? = null
) : OwnedGift

@Serializable
data class OwnedGiftUnique(
    @SerialName("type")
    val type: String = "unique",
    @SerialName("gift")
    val gift: UniqueGift,
    @SerialName("owned_gift_id")
    val ownedGiftId: String? = null,
    @SerialName("sender_user")
    val senderUser: User? = null,
    @SerialName("send_date")
    val sendDate: Long,
    @SerialName("is_saved")
    val isSaved: Boolean? = null,
    @SerialName("can_be_transferred")
    val canBeTransferred: Boolean? = null,
    @SerialName("transfer_star_count")
    val transferStarCount: Long? = null,
    @SerialName("next_transfer_date")
    val nextTransferDate: Long? = null
) : OwnedGift

@Serializable
data class OwnedGifts(
    @SerialName("total_count")
    val totalCount: Long,
    @SerialName("gifts")
    val gifts: List<OwnedGift>,
    @SerialName("next_offset")
    val nextOffset: String? = null
)

@Serializable
data class PaidMediaInfo(
    @SerialName("star_count")
    val starCount: Long,
    @SerialName("paid_media")
    val paidMedia: List<PaidMedia>
)

@Serializable
data class PaidMediaLivePhoto(
    @SerialName("type")
    val type: String = "live_photo",
    @SerialName("live_photo")
    val livePhoto: LivePhoto
) : PaidMedia

@Serializable
data class PaidMediaPhoto(
    @SerialName("type")
    val type: String = "photo",
    @SerialName("photo")
    val photo: List<PhotoSize>
) : PaidMedia

@Serializable
data class PaidMediaPreview(
    @SerialName("type")
    val type: String = "preview",
    @SerialName("width")
    val width: Long? = null,
    @SerialName("height")
    val height: Long? = null,
    @SerialName("duration")
    val duration: Long? = null
) : PaidMedia

@Serializable
data class PaidMediaPurchased(
    @SerialName("from")
    val from: User,
    @SerialName("paid_media_payload")
    val paidMediaPayload: String
)

@Serializable
data class PaidMediaVideo(
    @SerialName("type")
    val type: String = "video",
    @SerialName("video")
    val video: Video
) : PaidMedia

@Serializable
data class PaidMessagePriceChanged(
    @SerialName("paid_message_star_count")
    val paidMessageStarCount: Long
)

@Serializable
data class PassportData(
    @SerialName("data")
    val `data`: List<EncryptedPassportElement>,
    @SerialName("credentials")
    val credentials: EncryptedCredentials
)

@Serializable
data class PassportElementErrorDataField(
    @SerialName("source")
    val source: String = "data",
    @SerialName("type")
    val type: String,
    @SerialName("field_name")
    val fieldName: String,
    @SerialName("data_hash")
    val dataHash: String,
    @SerialName("message")
    val message: String
) : PassportElementError

@Serializable
data class PassportElementErrorFile(
    @SerialName("source")
    val source: String = "file",
    @SerialName("type")
    val type: String,
    @SerialName("file_hash")
    val fileHash: String,
    @SerialName("message")
    val message: String
) : PassportElementError

@Serializable
data class PassportElementErrorFiles(
    @SerialName("source")
    val source: String = "files",
    @SerialName("type")
    val type: String,
    @SerialName("file_hashes")
    val fileHashes: List<String>,
    @SerialName("message")
    val message: String
) : PassportElementError

@Serializable
data class PassportElementErrorFrontSide(
    @SerialName("source")
    val source: String = "front_side",
    @SerialName("type")
    val type: String,
    @SerialName("file_hash")
    val fileHash: String,
    @SerialName("message")
    val message: String
) : PassportElementError

@Serializable
data class PassportElementErrorReverseSide(
    @SerialName("source")
    val source: String = "reverse_side",
    @SerialName("type")
    val type: String,
    @SerialName("file_hash")
    val fileHash: String,
    @SerialName("message")
    val message: String
) : PassportElementError

@Serializable
data class PassportElementErrorSelfie(
    @SerialName("source")
    val source: String = "selfie",
    @SerialName("type")
    val type: String,
    @SerialName("file_hash")
    val fileHash: String,
    @SerialName("message")
    val message: String
) : PassportElementError

@Serializable
data class PassportElementErrorTranslationFile(
    @SerialName("source")
    val source: String = "translation_file",
    @SerialName("type")
    val type: String,
    @SerialName("file_hash")
    val fileHash: String,
    @SerialName("message")
    val message: String
) : PassportElementError

@Serializable
data class PassportElementErrorTranslationFiles(
    @SerialName("source")
    val source: String = "translation_files",
    @SerialName("type")
    val type: String,
    @SerialName("file_hashes")
    val fileHashes: List<String>,
    @SerialName("message")
    val message: String
) : PassportElementError

@Serializable
data class PassportElementErrorUnspecified(
    @SerialName("source")
    val source: String = "unspecified",
    @SerialName("type")
    val type: String,
    @SerialName("element_hash")
    val elementHash: String,
    @SerialName("message")
    val message: String
) : PassportElementError

@Serializable
data class PassportFile(
    @SerialName("file_id")
    val fileId: String,
    @SerialName("file_unique_id")
    val fileUniqueId: String,
    @SerialName("file_size")
    val fileSize: Long,
    @SerialName("file_date")
    val fileDate: Long
)

@Serializable
data class PhotoSize(
    @SerialName("file_id")
    val fileId: String,
    @SerialName("file_unique_id")
    val fileUniqueId: String,
    @SerialName("width")
    val width: Long,
    @SerialName("height")
    val height: Long,
    @SerialName("file_size")
    val fileSize: Long? = null
)

@Serializable
data class Poll(
    @SerialName("id")
    val id: String,
    @SerialName("question")
    val question: String,
    @SerialName("question_entities")
    val questionEntities: List<MessageEntity>? = null,
    @SerialName("options")
    val options: List<PollOption>,
    @SerialName("total_voter_count")
    val totalVoterCount: Long,
    @SerialName("is_closed")
    val isClosed: Boolean,
    @SerialName("is_anonymous")
    val isAnonymous: Boolean,
    @SerialName("type")
    val type: String,
    @SerialName("allows_multiple_answers")
    val allowsMultipleAnswers: Boolean,
    @SerialName("allows_revoting")
    val allowsRevoting: Boolean,
    @SerialName("members_only")
    val membersOnly: Boolean,
    @SerialName("country_codes")
    val countryCodes: List<String>? = null,
    @SerialName("correct_option_ids")
    val correctOptionIds: List<Long>? = null,
    @SerialName("explanation")
    val explanation: String? = null,
    @SerialName("explanation_entities")
    val explanationEntities: List<MessageEntity>? = null,
    @SerialName("explanation_media")
    val explanationMedia: PollMedia? = null,
    @SerialName("open_period")
    val openPeriod: Long? = null,
    @SerialName("close_date")
    val closeDate: Long? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("description_entities")
    val descriptionEntities: List<MessageEntity>? = null,
    @SerialName("media")
    val media: PollMedia? = null
)

@Serializable
data class PollAnswer(
    @SerialName("poll_id")
    val pollId: String,
    @SerialName("voter_chat")
    val voterChat: Chat? = null,
    @SerialName("user")
    val user: User? = null,
    @SerialName("option_ids")
    val optionIds: List<Long>,
    @SerialName("option_persistent_ids")
    val optionPersistentIds: List<String>
)

@Serializable
data class PollMedia(
    @SerialName("animation")
    val animation: Animation? = null,
    @SerialName("audio")
    val audio: Audio? = null,
    @SerialName("document")
    val document: Document? = null,
    @SerialName("link")
    val link: Link? = null,
    @SerialName("live_photo")
    val livePhoto: LivePhoto? = null,
    @SerialName("location")
    val location: Location? = null,
    @SerialName("photo")
    val photo: List<PhotoSize>? = null,
    @SerialName("sticker")
    val sticker: Sticker? = null,
    @SerialName("venue")
    val venue: Venue? = null,
    @SerialName("video")
    val video: Video? = null
)

@Serializable
data class PollOption(
    @SerialName("persistent_id")
    val persistentId: String,
    @SerialName("text")
    val text: String,
    @SerialName("text_entities")
    val textEntities: List<MessageEntity>? = null,
    @SerialName("media")
    val media: PollMedia? = null,
    @SerialName("voter_count")
    val voterCount: Long,
    @SerialName("added_by_user")
    val addedByUser: User? = null,
    @SerialName("added_by_chat")
    val addedByChat: Chat? = null,
    @SerialName("addition_date")
    val additionDate: Long? = null
)

@Serializable
data class PollOptionAdded(
    @SerialName("poll_message")
    val pollMessage: MaybeInaccessibleMessage? = null,
    @SerialName("option_persistent_id")
    val optionPersistentId: String,
    @SerialName("option_text")
    val optionText: String,
    @SerialName("option_text_entities")
    val optionTextEntities: List<MessageEntity>? = null
)

@Serializable
data class PollOptionDeleted(
    @SerialName("poll_message")
    val pollMessage: MaybeInaccessibleMessage? = null,
    @SerialName("option_persistent_id")
    val optionPersistentId: String,
    @SerialName("option_text")
    val optionText: String,
    @SerialName("option_text_entities")
    val optionTextEntities: List<MessageEntity>? = null
)

@Serializable
data class PreCheckoutQuery(
    @SerialName("id")
    val id: String,
    @SerialName("from")
    val from: User,
    @SerialName("currency")
    val currency: String,
    @SerialName("total_amount")
    val totalAmount: Long,
    @SerialName("invoice_payload")
    val invoicePayload: String,
    @SerialName("shipping_option_id")
    val shippingOptionId: String? = null,
    @SerialName("order_info")
    val orderInfo: OrderInfo? = null
)

@Serializable
data class PreparedInlineMessage(
    @SerialName("id")
    val id: String,
    @SerialName("expiration_date")
    val expirationDate: Long
)

@Serializable
data class PreparedKeyboardButton(
    @SerialName("id")
    val id: String
)

@Serializable
data class ProximityAlertTriggered(
    @SerialName("traveler")
    val traveler: User,
    @SerialName("watcher")
    val watcher: User,
    @SerialName("distance")
    val distance: Long
)

@Serializable
data class ReactionCount(
    @SerialName("type")
    val type: ReactionType,
    @SerialName("total_count")
    val totalCount: Long
)

@Serializable
data class ReactionTypeCustomEmoji(
    @SerialName("type")
    val type: String = "custom_emoji",
    @SerialName("custom_emoji_id")
    val customEmojiId: String
) : ReactionType

@Serializable
data class ReactionTypeEmoji(
    @SerialName("type")
    val type: String = "emoji",
    @SerialName("emoji")
    val emoji: String
) : ReactionType

@Serializable
data class ReactionTypePaid(
    @SerialName("type")
    val type: String = "paid"
) : ReactionType

@Serializable
data class RefundedPayment(
    @SerialName("currency")
    val currency: String,
    @SerialName("total_amount")
    val totalAmount: Long,
    @SerialName("invoice_payload")
    val invoicePayload: String,
    @SerialName("telegram_payment_charge_id")
    val telegramPaymentChargeId: String,
    @SerialName("provider_payment_charge_id")
    val providerPaymentChargeId: String? = null
)

@Serializable
data class ReplyKeyboardMarkup(
    @SerialName("keyboard")
    val keyboard: List<List<KeyboardButton>>,
    @SerialName("is_persistent")
    val isPersistent: Boolean? = null,
    @SerialName("resize_keyboard")
    val resizeKeyboard: Boolean? = null,
    @SerialName("one_time_keyboard")
    val oneTimeKeyboard: Boolean? = null,
    @SerialName("input_field_placeholder")
    val inputFieldPlaceholder: String? = null,
    @SerialName("selective")
    val selective: Boolean? = null,
    @SerialName("force_reply")
    val forceReply: Boolean? = null
) : ReplyMarkup

@Serializable
data class ReplyKeyboardRemove(
    @SerialName("remove_keyboard")
    val removeKeyboard: Boolean,
    @SerialName("selective")
    val selective: Boolean? = null
) : ReplyMarkup

@Serializable
data class ReplyParameters(
    @SerialName("message_id")
    val messageId: Long? = null,
    @SerialName("chat_id")
    val chatId: ChatId? = null,
    @SerialName("ephemeral_message_id")
    val ephemeralMessageId: Long? = null,
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,
    @SerialName("quote")
    val quote: String? = null,
    @SerialName("quote_parse_mode")
    val quoteParseMode: String? = null,
    @SerialName("quote_entities")
    val quoteEntities: List<MessageEntity>? = null,
    @SerialName("quote_position")
    val quotePosition: Long? = null,
    @SerialName("checklist_task_id")
    val checklistTaskId: Long? = null,
    @SerialName("poll_option_id")
    val pollOptionId: String? = null
)

@Serializable
data class ResponseParameters(
    @SerialName("migrate_to_chat_id")
    val migrateToChatId: Long? = null,
    @SerialName("retry_after")
    val retryAfter: Long? = null
)

@Serializable
data class RevenueWithdrawalStateFailed(
    @SerialName("type")
    val type: String = "failed"
) : RevenueWithdrawalState

@Serializable
data class RevenueWithdrawalStatePending(
    @SerialName("type")
    val type: String = "pending"
) : RevenueWithdrawalState

@Serializable
data class RevenueWithdrawalStateSucceeded(
    @SerialName("type")
    val type: String = "succeeded",
    @SerialName("date")
    val date: Long,
    @SerialName("url")
    val url: String
) : RevenueWithdrawalState

@Serializable
data class RichBlockAnchor(
    @SerialName("type")
    val type: String = "anchor",
    @SerialName("name")
    val name: String
) : RichBlock

@Serializable
data class RichBlockAnimation(
    @SerialName("type")
    val type: String = "animation",
    @SerialName("animation")
    val animation: Animation,
    @SerialName("has_spoiler")
    val hasSpoiler: Boolean? = null,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : RichBlock

@Serializable
data class RichBlockAudio(
    @SerialName("type")
    val type: String = "audio",
    @SerialName("audio")
    val audio: Audio,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : RichBlock

@Serializable
data class RichBlockBlockQuotation(
    @SerialName("type")
    val type: String = "blockquote",
    @SerialName("blocks")
    val blocks: List<RichBlock>,
    @SerialName("credit")
    val credit: RichText? = null
) : RichBlock

@Serializable
data class RichBlockButtons(
    @SerialName("type")
    val type: String = "buttons",
    @SerialName("buttons")
    val buttons: List<RichMessageButton>,
    @SerialName("align")
    val align: String? = null
) : RichBlock

@Serializable
data class RichBlockCaption(
    @SerialName("text")
    val text: RichText,
    @SerialName("credit")
    val credit: RichText? = null
)

@Serializable
data class RichBlockCollage(
    @SerialName("type")
    val type: String = "collage",
    @SerialName("blocks")
    val blocks: List<RichBlock>,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : RichBlock

@Serializable
data class RichBlockDetails(
    @SerialName("type")
    val type: String = "details",
    @SerialName("summary")
    val summary: RichText,
    @SerialName("blocks")
    val blocks: List<RichBlock>,
    @SerialName("is_open")
    val isOpen: Boolean? = null
) : RichBlock

@Serializable
data class RichBlockDivider(
    @SerialName("type")
    val type: String = "divider"
) : RichBlock

@Serializable
data class RichBlockDocument(
    @SerialName("type")
    val type: String = "document",
    @SerialName("document")
    val document: Document,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : RichBlock

@Serializable
data class RichBlockExpandableBlockQuotation(
    @SerialName("type")
    val type: String = "expandable_blockquote",
    @SerialName("text")
    val text: RichText,
    @SerialName("credit")
    val credit: RichText? = null
) : RichBlock

@Serializable
data class RichBlockFooter(
    @SerialName("type")
    val type: String = "footer",
    @SerialName("text")
    val text: RichText
) : RichBlock

@Serializable
data class RichBlockList(
    @SerialName("type")
    val type: String = "list",
    @SerialName("items")
    val items: List<RichBlockListItem>
) : RichBlock

@Serializable
data class RichBlockListItem(
    @SerialName("label")
    val label: String,
    @SerialName("blocks")
    val blocks: List<RichBlock>,
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
data class RichBlockMap(
    @SerialName("type")
    val type: String = "map",
    @SerialName("location")
    val location: Location,
    @SerialName("zoom")
    val zoom: Long,
    @SerialName("width")
    val width: Long,
    @SerialName("height")
    val height: Long,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : RichBlock

@Serializable
data class RichBlockMathematicalExpression(
    @SerialName("type")
    val type: String = "mathematical_expression",
    @SerialName("expression")
    val expression: String
) : RichBlock

@Serializable
data class RichBlockParagraph(
    @SerialName("type")
    val type: String = "paragraph",
    @SerialName("text")
    val text: RichText
) : RichBlock

@Serializable
data class RichBlockPhoto(
    @SerialName("type")
    val type: String = "photo",
    @SerialName("photo")
    val photo: List<PhotoSize>,
    @SerialName("has_spoiler")
    val hasSpoiler: Boolean? = null,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : RichBlock

@Serializable
data class RichBlockPreformatted(
    @SerialName("type")
    val type: String = "pre",
    @SerialName("text")
    val text: RichText,
    @SerialName("language")
    val language: String? = null
) : RichBlock

@Serializable
data class RichBlockPullQuotation(
    @SerialName("type")
    val type: String = "pullquote",
    @SerialName("text")
    val text: RichText,
    @SerialName("credit")
    val credit: RichText? = null
) : RichBlock

@Serializable
data class RichBlockSectionHeading(
    @SerialName("type")
    val type: String = "heading",
    @SerialName("text")
    val text: RichText,
    @SerialName("size")
    val size: Long
) : RichBlock

@Serializable
data class RichBlockSlideshow(
    @SerialName("type")
    val type: String = "slideshow",
    @SerialName("blocks")
    val blocks: List<RichBlock>,
    @SerialName("caption")
    val caption: RichBlockCaption? = null
) : RichBlock

@Serializable
data class RichBlockTable(
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
) : RichBlock
