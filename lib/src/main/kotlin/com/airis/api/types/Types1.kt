package com.airis.api.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Telegram Bot API 10.3 types (part 1/4).
 * Auto-generated from https://core.telegram.org/bots/api (Bot API 10.3, Aug 24 2026).
 * Optimized with kotlinx.serialization: explicitNulls=false, ignoreUnknownKeys=true.
 */

@Serializable
data class AcceptedGiftTypes(
    @SerialName("unlimited_gifts")
    val unlimitedGifts: Boolean,
    @SerialName("limited_gifts")
    val limitedGifts: Boolean,
    @SerialName("unique_gifts")
    val uniqueGifts: Boolean,
    @SerialName("premium_subscription")
    val premiumSubscription: Boolean,
    @SerialName("gifts_from_channels")
    val giftsFromChannels: Boolean
)

@Serializable
data class AffiliateInfo(
    @SerialName("affiliate_user")
    val affiliateUser: User? = null,
    @SerialName("affiliate_chat")
    val affiliateChat: Chat? = null,
    @SerialName("commission_per_mille")
    val commissionPerMille: Long,
    @SerialName("amount")
    val amount: Long,
    @SerialName("nanostar_amount")
    val nanostarAmount: Long? = null
)

@Serializable
data class Animation(
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
    @SerialName("file_name")
    val fileName: String? = null,
    @SerialName("mime_type")
    val mimeType: String? = null,
    @SerialName("file_size")
    val fileSize: Long? = null
)

@Serializable
data class Audio(
    @SerialName("file_id")
    val fileId: String,
    @SerialName("file_unique_id")
    val fileUniqueId: String,
    @SerialName("duration")
    val duration: Long,
    @SerialName("performer")
    val performer: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("file_name")
    val fileName: String? = null,
    @SerialName("mime_type")
    val mimeType: String? = null,
    @SerialName("file_size")
    val fileSize: Long? = null,
    @SerialName("thumbnail")
    val thumbnail: PhotoSize? = null
)

@Serializable
data class BackgroundFillFreeformGradient(
    @SerialName("type")
    val type: String = "freeform_gradient",
    @SerialName("colors")
    val colors: List<Long>
) : BackgroundFill

@Serializable
data class BackgroundFillGradient(
    @SerialName("type")
    val type: String = "gradient",
    @SerialName("top_color")
    val topColor: Long,
    @SerialName("bottom_color")
    val bottomColor: Long,
    @SerialName("rotation_angle")
    val rotationAngle: Long
) : BackgroundFill

@Serializable
data class BackgroundFillSolid(
    @SerialName("type")
    val type: String = "solid",
    @SerialName("color")
    val color: Long
) : BackgroundFill

@Serializable
data class BackgroundTypeChatTheme(
    @SerialName("type")
    val type: String = "chat_theme",
    @SerialName("theme_name")
    val themeName: String
) : BackgroundType

@Serializable
data class BackgroundTypeFill(
    @SerialName("type")
    val type: String = "fill",
    @SerialName("fill")
    val fill: BackgroundFill,
    @SerialName("dark_theme_dimming")
    val darkThemeDimming: Long
) : BackgroundType

@Serializable
data class BackgroundTypePattern(
    @SerialName("type")
    val type: String = "pattern",
    @SerialName("document")
    val document: Document,
    @SerialName("fill")
    val fill: BackgroundFill,
    @SerialName("intensity")
    val intensity: Long,
    @SerialName("is_inverted")
    val isInverted: Boolean? = null,
    @SerialName("is_moving")
    val isMoving: Boolean? = null
) : BackgroundType

@Serializable
data class BackgroundTypeWallpaper(
    @SerialName("type")
    val type: String = "wallpaper",
    @SerialName("document")
    val document: Document,
    @SerialName("dark_theme_dimming")
    val darkThemeDimming: Long,
    @SerialName("is_blurred")
    val isBlurred: Boolean? = null,
    @SerialName("is_moving")
    val isMoving: Boolean? = null
) : BackgroundType

@Serializable
data class Birthdate(
    @SerialName("day")
    val day: Long,
    @SerialName("month")
    val month: Long,
    @SerialName("year")
    val year: Long? = null
)

@Serializable
data class BotAccessSettings(
    @SerialName("is_access_restricted")
    val isAccessRestricted: Boolean,
    @SerialName("added_users")
    val addedUsers: List<User>? = null
)

@Serializable
data class BotCommand(
    @SerialName("command")
    val command: String,
    @SerialName("description")
    val description: String,
    @SerialName("is_ephemeral")
    val isEphemeral: Boolean? = null
)

@Serializable
data class BotCommandScopeAllChatAdministrators(
    @SerialName("type")
    val type: String = "all_chat_administrators"
) : BotCommandScope

@Serializable
data class BotCommandScopeAllGroupChats(
    @SerialName("type")
    val type: String = "all_group_chats"
) : BotCommandScope

@Serializable
data class BotCommandScopeAllPrivateChats(
    @SerialName("type")
    val type: String = "all_private_chats"
) : BotCommandScope

@Serializable
data class BotCommandScopeChat(
    @SerialName("type")
    val type: String = "chat",
    @SerialName("chat_id")
    val chatId: ChatId
) : BotCommandScope

@Serializable
data class BotCommandScopeChatAdministrators(
    @SerialName("type")
    val type: String = "chat_administrators",
    @SerialName("chat_id")
    val chatId: ChatId
) : BotCommandScope

@Serializable
data class BotCommandScopeChatMember(
    @SerialName("type")
    val type: String = "chat_member",
    @SerialName("chat_id")
    val chatId: ChatId,
    @SerialName("user_id")
    val userId: Long
) : BotCommandScope

@Serializable
data class BotCommandScopeDefault(
    @SerialName("type")
    val type: String = "default"
) : BotCommandScope

@Serializable
data class BotDescription(
    @SerialName("description")
    val description: String
)

@Serializable
data class BotName(
    @SerialName("name")
    val name: String
)

@Serializable
data class BotShortDescription(
    @SerialName("short_description")
    val shortDescription: String
)

@Serializable
data class BotSubscriptionUpdated(
    @SerialName("user")
    val user: User,
    @SerialName("invoice_payload")
    val invoicePayload: String,
    @SerialName("state")
    val state: String
)

@Serializable
data class BusinessBotRights(
    @SerialName("can_reply")
    val canReply: Boolean? = null,
    @SerialName("can_read_messages")
    val canReadMessages: Boolean? = null,
    @SerialName("can_delete_sent_messages")
    val canDeleteSentMessages: Boolean? = null,
    @SerialName("can_delete_all_messages")
    val canDeleteAllMessages: Boolean? = null,
    @SerialName("can_edit_name")
    val canEditName: Boolean? = null,
    @SerialName("can_edit_bio")
    val canEditBio: Boolean? = null,
    @SerialName("can_edit_profile_photo")
    val canEditProfilePhoto: Boolean? = null,
    @SerialName("can_edit_username")
    val canEditUsername: Boolean? = null,
    @SerialName("can_change_gift_settings")
    val canChangeGiftSettings: Boolean? = null,
    @SerialName("can_view_gifts_and_stars")
    val canViewGiftsAndStars: Boolean? = null,
    @SerialName("can_convert_gifts_to_stars")
    val canConvertGiftsToStars: Boolean? = null,
    @SerialName("can_transfer_and_upgrade_gifts")
    val canTransferAndUpgradeGifts: Boolean? = null,
    @SerialName("can_transfer_stars")
    val canTransferStars: Boolean? = null,
    @SerialName("can_manage_stories")
    val canManageStories: Boolean? = null
)

@Serializable
data class BusinessConnection(
    @SerialName("id")
    val id: String,
    @SerialName("user")
    val user: User,
    @SerialName("user_chat_id")
    val userChatId: Long,
    @SerialName("date")
    val date: Long,
    @SerialName("rights")
    val rights: BusinessBotRights? = null,
    @SerialName("is_enabled")
    val isEnabled: Boolean
)

@Serializable
data class BusinessIntro(
    @SerialName("title")
    val title: String? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("sticker")
    val sticker: Sticker? = null
)

@Serializable
data class BusinessLocation(
    @SerialName("address")
    val address: String,
    @SerialName("location")
    val location: Location? = null
)

@Serializable
data class BusinessMessagesDeleted(
    @SerialName("business_connection_id")
    val businessConnectionId: String,
    @SerialName("chat")
    val chat: Chat,
    @SerialName("message_ids")
    val messageIds: List<Long>
)

@Serializable
data class BusinessOpeningHours(
    @SerialName("time_zone_name")
    val timeZoneName: String,
    @SerialName("opening_hours")
    val openingHours: List<BusinessOpeningHoursInterval>
)

@Serializable
data class BusinessOpeningHoursInterval(
    @SerialName("opening_minute")
    val openingMinute: Long,
    @SerialName("closing_minute")
    val closingMinute: Long
)

@Serializable
data class CallbackGame(
    @SerialName("_placeholder")
    val placeholder: String? = null
)

@Serializable
data class CallbackQuery(
    @SerialName("id")
    val id: String,
    @SerialName("from")
    val from: User,
    @SerialName("message")
    val message: MaybeInaccessibleMessage? = null,
    @SerialName("inline_message_id")
    val inlineMessageId: String? = null,
    @SerialName("chat_instance")
    val chatInstance: String,
    @SerialName("data")
    val `data`: String? = null,
    @SerialName("game_short_name")
    val gameShortName: String? = null
)

@Serializable
data class Chat(
    @SerialName("id")
    val id: Long,
    @SerialName("type")
    val type: String,
    @SerialName("title")
    val title: String? = null,
    @SerialName("username")
    val username: String? = null,
    @SerialName("first_name")
    val firstName: String? = null,
    @SerialName("last_name")
    val lastName: String? = null,
    @SerialName("is_forum")
    val isForum: Boolean? = null,
    @SerialName("is_direct_messages")
    val isDirectMessages: Boolean? = null
)

@Serializable
data class ChatAdministratorRights(
    @SerialName("is_anonymous")
    val isAnonymous: Boolean,
    @SerialName("can_manage_chat")
    val canManageChat: Boolean,
    @SerialName("can_delete_messages")
    val canDeleteMessages: Boolean,
    @SerialName("can_manage_video_chats")
    val canManageVideoChats: Boolean,
    @SerialName("can_restrict_members")
    val canRestrictMembers: Boolean,
    @SerialName("can_promote_members")
    val canPromoteMembers: Boolean,
    @SerialName("can_change_info")
    val canChangeInfo: Boolean,
    @SerialName("can_invite_users")
    val canInviteUsers: Boolean,
    @SerialName("can_post_stories")
    val canPostStories: Boolean,
    @SerialName("can_edit_stories")
    val canEditStories: Boolean,
    @SerialName("can_delete_stories")
    val canDeleteStories: Boolean,
    @SerialName("can_post_messages")
    val canPostMessages: Boolean? = null,
    @SerialName("can_edit_messages")
    val canEditMessages: Boolean? = null,
    @SerialName("can_pin_messages")
    val canPinMessages: Boolean? = null,
    @SerialName("can_manage_topics")
    val canManageTopics: Boolean? = null,
    @SerialName("can_manage_direct_messages")
    val canManageDirectMessages: Boolean? = null,
    @SerialName("can_manage_tags")
    val canManageTags: Boolean? = null,
    @SerialName("can_send_welcome_messages")
    val canSendWelcomeMessages: Boolean
)

@Serializable
data class ChatBackground(
    @SerialName("type")
    val type: BackgroundType
)

@Serializable
data class ChatBoost(
    @SerialName("boost_id")
    val boostId: String,
    @SerialName("add_date")
    val addDate: Long,
    @SerialName("expiration_date")
    val expirationDate: Long,
    @SerialName("source")
    val source: ChatBoostSource
)

@Serializable
data class ChatBoostAdded(
    @SerialName("boost_count")
    val boostCount: Long
)

@Serializable
data class ChatBoostRemoved(
    @SerialName("chat")
    val chat: Chat,
    @SerialName("boost_id")
    val boostId: String,
    @SerialName("remove_date")
    val removeDate: Long,
    @SerialName("source")
    val source: ChatBoostSource
)

@Serializable
data class ChatBoostSourceGiftCode(
    @SerialName("source")
    val source: String = "gift_code",
    @SerialName("user")
    val user: User
) : ChatBoostSource

@Serializable
data class ChatBoostSourceGiveaway(
    @SerialName("source")
    val source: String = "giveaway",
    @SerialName("giveaway_message_id")
    val giveawayMessageId: Long,
    @SerialName("user")
    val user: User? = null,
    @SerialName("prize_star_count")
    val prizeStarCount: Long? = null,
    @SerialName("is_unclaimed")
    val isUnclaimed: Boolean? = null
) : ChatBoostSource

@Serializable
data class ChatBoostSourcePremium(
    @SerialName("source")
    val source: String = "premium",
    @SerialName("user")
    val user: User
) : ChatBoostSource

@Serializable
data class ChatBoostUpdated(
    @SerialName("chat")
    val chat: Chat,
    @SerialName("boost")
    val boost: ChatBoost
)

@Serializable
data class ChatFullInfo(
    @SerialName("id")
    val id: Long,
    @SerialName("type")
    val type: String,
    @SerialName("title")
    val title: String? = null,
    @SerialName("username")
    val username: String? = null,
    @SerialName("first_name")
    val firstName: String? = null,
    @SerialName("last_name")
    val lastName: String? = null,
    @SerialName("is_forum")
    val isForum: Boolean? = null,
    @SerialName("is_direct_messages")
    val isDirectMessages: Boolean? = null,
    @SerialName("accent_color_id")
    val accentColorId: Long,
    @SerialName("max_reaction_count")
    val maxReactionCount: Long,
    @SerialName("photo")
    val photo: ChatPhoto? = null,
    @SerialName("active_usernames")
    val activeUsernames: List<String>? = null,
    @SerialName("birthdate")
    val birthdate: Birthdate? = null,
    @SerialName("business_intro")
    val businessIntro: BusinessIntro? = null,
    @SerialName("business_location")
    val businessLocation: BusinessLocation? = null,
    @SerialName("business_opening_hours")
    val businessOpeningHours: BusinessOpeningHours? = null,
    @SerialName("personal_chat")
    val personalChat: Chat? = null,
    @SerialName("parent_chat")
    val parentChat: Chat? = null,
    @SerialName("available_reactions")
    val availableReactions: List<ReactionType>? = null,
    @SerialName("background_custom_emoji_id")
    val backgroundCustomEmojiId: String? = null,
    @SerialName("profile_accent_color_id")
    val profileAccentColorId: Long? = null,
    @SerialName("profile_background_custom_emoji_id")
    val profileBackgroundCustomEmojiId: String? = null,
    @SerialName("emoji_status_custom_emoji_id")
    val emojiStatusCustomEmojiId: String? = null,
    @SerialName("emoji_status_expiration_date")
    val emojiStatusExpirationDate: Long? = null,
    @SerialName("bio")
    val bio: String? = null,
    @SerialName("has_private_forwards")
    val hasPrivateForwards: Boolean? = null,
    @SerialName("has_restricted_voice_and_video_messages")
    val hasRestrictedVoiceAndVideoMessages: Boolean? = null,
    @SerialName("join_to_send_messages")
    val joinToSendMessages: Boolean? = null,
    @SerialName("join_by_request")
    val joinByRequest: Boolean? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("invite_link")
    val inviteLink: String? = null,
    @SerialName("pinned_message")
    val pinnedMessage: Message? = null,
    @SerialName("permissions")
    val permissions: ChatPermissions? = null,
    @SerialName("accepted_gift_types")
    val acceptedGiftTypes: AcceptedGiftTypes,
    @SerialName("can_send_paid_media")
    val canSendPaidMedia: Boolean? = null,
    @SerialName("slow_mode_delay")
    val slowModeDelay: Long? = null,
    @SerialName("unrestrict_boost_count")
    val unrestrictBoostCount: Long? = null,
    @SerialName("message_auto_delete_time")
    val messageAutoDeleteTime: Long? = null,
    @SerialName("has_aggressive_anti_spam_enabled")
    val hasAggressiveAntiSpamEnabled: Boolean? = null,
    @SerialName("has_hidden_members")
    val hasHiddenMembers: Boolean? = null,
    @SerialName("has_protected_content")
    val hasProtectedContent: Boolean? = null,
    @SerialName("has_visible_history")
    val hasVisibleHistory: Boolean? = null,
    @SerialName("sticker_set_name")
    val stickerSetName: String? = null,
    @SerialName("can_set_sticker_set")
    val canSetStickerSet: Boolean? = null,
    @SerialName("custom_emoji_sticker_set_name")
    val customEmojiStickerSetName: String? = null,
    @SerialName("linked_chat_id")
    val linkedChatId: Long? = null,
    @SerialName("location")
    val location: ChatLocation? = null,
    @SerialName("rating")
    val rating: UserRating? = null,
    @SerialName("first_profile_audio")
    val firstProfileAudio: Audio? = null,
    @SerialName("unique_gift_colors")
    val uniqueGiftColors: UniqueGiftColors? = null,
    @SerialName("paid_message_star_count")
    val paidMessageStarCount: Long? = null,
    @SerialName("guard_bot")
    val guardBot: User? = null,
    @SerialName("community")
    val community: Community? = null
)

@Serializable
data class ChatInviteLink(
    @SerialName("invite_link")
    val inviteLink: String,
    @SerialName("creator")
    val creator: User,
    @SerialName("creates_join_request")
    val createsJoinRequest: Boolean,
    @SerialName("is_primary")
    val isPrimary: Boolean,
    @SerialName("is_revoked")
    val isRevoked: Boolean,
    @SerialName("name")
    val name: String? = null,
    @SerialName("expire_date")
    val expireDate: Long? = null,
    @SerialName("member_limit")
    val memberLimit: Long? = null,
    @SerialName("pending_join_request_count")
    val pendingJoinRequestCount: Long? = null,
    @SerialName("subscription_period")
    val subscriptionPeriod: Long? = null,
    @SerialName("subscription_price")
    val subscriptionPrice: Long? = null
)

@Serializable
data class ChatJoinRequest(
    @SerialName("chat")
    val chat: Chat,
    @SerialName("from")
    val from: User,
    @SerialName("user_chat_id")
    val userChatId: Long,
    @SerialName("date")
    val date: Long,
    @SerialName("bio")
    val bio: String? = null,
    @SerialName("invite_link")
    val inviteLink: ChatInviteLink? = null,
    @SerialName("query_id")
    val queryId: String? = null
)

@Serializable
data class ChatLocation(
    @SerialName("location")
    val location: Location,
    @SerialName("address")
    val address: String
)

@Serializable
data class ChatMemberAdministrator(
    @SerialName("status")
    val status: String = "administrator",
    @SerialName("user")
    val user: User,
    @SerialName("can_be_edited")
    val canBeEdited: Boolean,
    @SerialName("is_anonymous")
    val isAnonymous: Boolean,
    @SerialName("can_manage_chat")
    val canManageChat: Boolean,
    @SerialName("can_delete_messages")
    val canDeleteMessages: Boolean,
    @SerialName("can_manage_video_chats")
    val canManageVideoChats: Boolean,
    @SerialName("can_restrict_members")
    val canRestrictMembers: Boolean,
    @SerialName("can_promote_members")
    val canPromoteMembers: Boolean,
    @SerialName("can_change_info")
    val canChangeInfo: Boolean,
    @SerialName("can_invite_users")
    val canInviteUsers: Boolean,
    @SerialName("can_post_stories")
    val canPostStories: Boolean,
    @SerialName("can_edit_stories")
    val canEditStories: Boolean,
    @SerialName("can_delete_stories")
    val canDeleteStories: Boolean,
    @SerialName("can_post_messages")
    val canPostMessages: Boolean? = null,
    @SerialName("can_edit_messages")
    val canEditMessages: Boolean? = null,
    @SerialName("can_pin_messages")
    val canPinMessages: Boolean? = null,
    @SerialName("can_manage_topics")
    val canManageTopics: Boolean? = null,
    @SerialName("can_manage_direct_messages")
    val canManageDirectMessages: Boolean? = null,
    @SerialName("can_manage_tags")
    val canManageTags: Boolean? = null,
    @SerialName("can_send_welcome_messages")
    val canSendWelcomeMessages: Boolean,
    @SerialName("custom_title")
    val customTitle: String? = null
) : ChatMember

@Serializable
data class ChatMemberBanned(
    @SerialName("status")
    val status: String = "kicked",
    @SerialName("user")
    val user: User,
    @SerialName("until_date")
    val untilDate: Long
) : ChatMember

@Serializable
data class ChatMemberLeft(
    @SerialName("status")
    val status: String = "left",
    @SerialName("user")
    val user: User
) : ChatMember

@Serializable
data class ChatMemberMember(
    @SerialName("status")
    val status: String = "member",
    @SerialName("tag")
    val tag: String? = null,
    @SerialName("user")
    val user: User,
    @SerialName("until_date")
    val untilDate: Long? = null
) : ChatMember

@Serializable
data class ChatMemberOwner(
    @SerialName("status")
    val status: String = "creator",
    @SerialName("user")
    val user: User,
    @SerialName("is_anonymous")
    val isAnonymous: Boolean,
    @SerialName("custom_title")
    val customTitle: String? = null
) : ChatMember

@Serializable
data class ChatMemberRestricted(
    @SerialName("status")
    val status: String = "restricted",
    @SerialName("tag")
    val tag: String? = null,
    @SerialName("user")
    val user: User,
    @SerialName("is_member")
    val isMember: Boolean,
    @SerialName("can_send_messages")
    val canSendMessages: Boolean,
    @SerialName("can_send_audios")
    val canSendAudios: Boolean,
    @SerialName("can_send_documents")
    val canSendDocuments: Boolean,
    @SerialName("can_send_photos")
    val canSendPhotos: Boolean,
    @SerialName("can_send_videos")
    val canSendVideos: Boolean,
    @SerialName("can_send_video_notes")
    val canSendVideoNotes: Boolean,
    @SerialName("can_send_voice_notes")
    val canSendVoiceNotes: Boolean,
    @SerialName("can_send_polls")
    val canSendPolls: Boolean,
    @SerialName("can_send_other_messages")
    val canSendOtherMessages: Boolean,
    @SerialName("can_add_web_page_previews")
    val canAddWebPagePreviews: Boolean,
    @SerialName("can_react_to_messages")
    val canReactToMessages: Boolean,
    @SerialName("can_edit_tag")
    val canEditTag: Boolean,
    @SerialName("can_change_info")
    val canChangeInfo: Boolean,
    @SerialName("can_invite_users")
    val canInviteUsers: Boolean,
    @SerialName("can_pin_messages")
    val canPinMessages: Boolean,
    @SerialName("can_manage_topics")
    val canManageTopics: Boolean,
    @SerialName("until_date")
    val untilDate: Long
) : ChatMember

@Serializable
data class ChatMemberUpdated(
    @SerialName("chat")
    val chat: Chat,
    @SerialName("from")
    val from: User,
    @SerialName("date")
    val date: Long,
    @SerialName("old_chat_member")
    val oldChatMember: ChatMember,
    @SerialName("new_chat_member")
    val newChatMember: ChatMember,
    @SerialName("invite_link")
    val inviteLink: ChatInviteLink? = null,
    @SerialName("via_join_request")
    val viaJoinRequest: Boolean? = null,
    @SerialName("via_chat_folder_invite_link")
    val viaChatFolderInviteLink: Boolean? = null
)

@Serializable
data class ChatOwnerChanged(
    @SerialName("new_owner")
    val newOwner: User
)

@Serializable
data class ChatOwnerLeft(
    @SerialName("new_owner")
    val newOwner: User? = null
)

@Serializable
data class ChatPermissions(
    @SerialName("can_send_messages")
    val canSendMessages: Boolean? = null,
    @SerialName("can_send_audios")
    val canSendAudios: Boolean? = null,
    @SerialName("can_send_documents")
    val canSendDocuments: Boolean? = null,
    @SerialName("can_send_photos")
    val canSendPhotos: Boolean? = null,
    @SerialName("can_send_videos")
    val canSendVideos: Boolean? = null,
    @SerialName("can_send_video_notes")
    val canSendVideoNotes: Boolean? = null,
    @SerialName("can_send_voice_notes")
    val canSendVoiceNotes: Boolean? = null,
    @SerialName("can_send_polls")
    val canSendPolls: Boolean? = null,
    @SerialName("can_send_other_messages")
    val canSendOtherMessages: Boolean? = null,
    @SerialName("can_add_web_page_previews")
    val canAddWebPagePreviews: Boolean? = null,
    @SerialName("can_react_to_messages")
    val canReactToMessages: Boolean? = null,
    @SerialName("can_edit_tag")
    val canEditTag: Boolean? = null,
    @SerialName("can_change_info")
    val canChangeInfo: Boolean? = null,
    @SerialName("can_invite_users")
    val canInviteUsers: Boolean? = null,
    @SerialName("can_pin_messages")
    val canPinMessages: Boolean? = null,
    @SerialName("can_manage_topics")
    val canManageTopics: Boolean? = null
)

@Serializable
data class ChatPhoto(
    @SerialName("small_file_id")
    val smallFileId: String,
    @SerialName("small_file_unique_id")
    val smallFileUniqueId: String,
    @SerialName("big_file_id")
    val bigFileId: String,
    @SerialName("big_file_unique_id")
    val bigFileUniqueId: String
)

@Serializable
data class ChatShared(
    @SerialName("request_id")
    val requestId: Long,
    @SerialName("chat_id")
    val chatId: Long,
    @SerialName("title")
    val title: String? = null,
    @SerialName("username")
    val username: String? = null,
    @SerialName("photo")
    val photo: List<PhotoSize>? = null
)

@Serializable
data class Checklist(
    @SerialName("title")
    val title: String,
    @SerialName("title_entities")
    val titleEntities: List<MessageEntity>? = null,
    @SerialName("tasks")
    val tasks: List<ChecklistTask>,
    @SerialName("others_can_add_tasks")
    val othersCanAddTasks: Boolean? = null,
    @SerialName("others_can_mark_tasks_as_done")
    val othersCanMarkTasksAsDone: Boolean? = null
)

@Serializable
data class ChecklistTask(
    @SerialName("id")
    val id: Long,
    @SerialName("text")
    val text: String,
    @SerialName("text_entities")
    val textEntities: List<MessageEntity>? = null,
    @SerialName("completed_by_user")
    val completedByUser: User? = null,
    @SerialName("completed_by_chat")
    val completedByChat: Chat? = null,
    @SerialName("completion_date")
    val completionDate: Long? = null
)

@Serializable
data class ChecklistTasksAdded(
    @SerialName("checklist_message")
    val checklistMessage: Message? = null,
    @SerialName("tasks")
    val tasks: List<ChecklistTask>
)

@Serializable
data class ChecklistTasksDone(
    @SerialName("checklist_message")
    val checklistMessage: Message? = null,
    @SerialName("marked_as_done_task_ids")
    val markedAsDoneTaskIds: List<Long>? = null,
    @SerialName("marked_as_not_done_task_ids")
    val markedAsNotDoneTaskIds: List<Long>? = null
)

@Serializable
data class ChosenInlineResult(
    @SerialName("result_id")
    val resultId: String,
    @SerialName("from")
    val from: User,
    @SerialName("location")
    val location: Location? = null,
    @SerialName("inline_message_id")
    val inlineMessageId: String? = null,
    @SerialName("query")
    val query: String
)

@Serializable
data class Community(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String
)

@Serializable
data class CommunityChatAdded(
    @SerialName("community")
    val community: Community
)

@Serializable
data class CommunityChatJoined(
    @SerialName("community")
    val community: Community
)

@Serializable
data class CommunityChatRemoved(
    @SerialName("_placeholder")
    val placeholder: String? = null
)

@Serializable
data class Contact(
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String? = null,
    @SerialName("user_id")
    val userId: Long? = null,
    @SerialName("vcard")
    val vcard: String? = null
)

@Serializable
data class CopyTextButton(
    @SerialName("text")
    val text: String
)

@Serializable
data class Dice(
    @SerialName("emoji")
    val emoji: String,
    @SerialName("value")
    val value: Long
)

@Serializable
data class DirectMessagePriceChanged(
    @SerialName("are_direct_messages_enabled")
    val areDirectMessagesEnabled: Boolean,
    @SerialName("direct_message_star_count")
    val directMessageStarCount: Long? = null
)

@Serializable
data class DirectMessagesTopic(
    @SerialName("topic_id")
    val topicId: Long,
    @SerialName("user")
    val user: User? = null
)

@Serializable
data class DisabledButton(
    @SerialName("_placeholder")
    val placeholder: String? = null
)

@Serializable
data class Document(
    @SerialName("file_id")
    val fileId: String,
    @SerialName("file_unique_id")
    val fileUniqueId: String,
    @SerialName("thumbnail")
    val thumbnail: PhotoSize? = null,
    @SerialName("file_name")
    val fileName: String? = null,
    @SerialName("mime_type")
    val mimeType: String? = null,
    @SerialName("file_size")
    val fileSize: Long? = null
)

@Serializable
data class EncryptedCredentials(
    @SerialName("data")
    val `data`: String,
    @SerialName("hash")
    val hash: String,
    @SerialName("secret")
    val secret: String
)

@Serializable
data class EncryptedPassportElement(
    @SerialName("type")
    val type: String,
    @SerialName("data")
    val `data`: String? = null,
    @SerialName("phone_number")
    val phoneNumber: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("files")
    val files: List<PassportFile>? = null,
    @SerialName("front_side")
    val frontSide: PassportFile? = null,
    @SerialName("reverse_side")
    val reverseSide: PassportFile? = null,
    @SerialName("selfie")
    val selfie: PassportFile? = null,
    @SerialName("translation")
    val translation: List<PassportFile>? = null,
    @SerialName("hash")
    val hash: String
)

@Serializable
data class EphemeralMessageParameters(
    @SerialName("receiver_user_id")
    val receiverUserId: Long,
    @SerialName("callback_query_id")
    val callbackQueryId: String? = null,
    @SerialName("replace_callback_query_message")
    val replaceCallbackQueryMessage: Boolean? = null
)

@Serializable
data class ExternalReplyInfo(
    @SerialName("origin")
    val origin: MessageOrigin,
    @SerialName("chat")
    val chat: Chat? = null,
    @SerialName("message_id")
    val messageId: Long? = null,
    @SerialName("link_preview_options")
    val linkPreviewOptions: LinkPreviewOptions? = null,
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
    @SerialName("giveaway")
    val giveaway: Giveaway? = null,
    @SerialName("giveaway_winners")
    val giveawayWinners: GiveawayWinners? = null,
    @SerialName("invoice")
    val invoice: Invoice? = null,
    @SerialName("location")
    val location: Location? = null,
    @SerialName("poll")
    val poll: Poll? = null,
    @SerialName("venue")
    val venue: Venue? = null
)

@Serializable
data class File(
    @SerialName("file_id")
    val fileId: String,
    @SerialName("file_unique_id")
    val fileUniqueId: String,
    @SerialName("file_size")
    val fileSize: Long? = null,
    @SerialName("file_path")
    val filePath: String? = null
)

@Serializable
data class ForceReply(
    @SerialName("force_reply")
    val forceReply: Boolean,
    @SerialName("input_field_placeholder")
    val inputFieldPlaceholder: String? = null,
    @SerialName("selective")
    val selective: Boolean? = null
) : ReplyMarkup

@Serializable
data class ForumTopic(
    @SerialName("message_thread_id")
    val messageThreadId: Long,
    @SerialName("name")
    val name: String,
    @SerialName("icon_color")
    val iconColor: Long,
    @SerialName("icon_custom_emoji_id")
    val iconCustomEmojiId: String? = null,
    @SerialName("is_name_implicit")
    val isNameImplicit: Boolean? = null
)

@Serializable
data class ForumTopicClosed(
    @SerialName("_placeholder")
    val placeholder: String? = null
)

@Serializable
data class ForumTopicCreated(
    @SerialName("name")
    val name: String,
    @SerialName("icon_color")
    val iconColor: Long,
    @SerialName("icon_custom_emoji_id")
    val iconCustomEmojiId: String? = null,
    @SerialName("is_name_implicit")
    val isNameImplicit: Boolean? = null
)

@Serializable
data class ForumTopicEdited(
    @SerialName("name")
    val name: String? = null,
    @SerialName("icon_custom_emoji_id")
    val iconCustomEmojiId: String? = null
)

@Serializable
data class ForumTopicReopened(
    @SerialName("_placeholder")
    val placeholder: String? = null
)

@Serializable
data class Game(
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("photo")
    val photo: List<PhotoSize>,
    @SerialName("text")
    val text: String? = null,
    @SerialName("text_entities")
    val textEntities: List<MessageEntity>? = null,
    @SerialName("animation")
    val animation: Animation? = null
)

@Serializable
data class GameHighScore(
    @SerialName("position")
    val position: Long,
    @SerialName("user")
    val user: User,
    @SerialName("score")
    val score: Long
)

@Serializable
data class GeneralForumTopicHidden(
    @SerialName("_placeholder")
    val placeholder: String? = null
)

@Serializable
data class GeneralForumTopicUnhidden(
    @SerialName("_placeholder")
    val placeholder: String? = null
)

@Serializable
data class Gift(
    @SerialName("id")
    val id: String,
    @SerialName("sticker")
    val sticker: Sticker,
    @SerialName("star_count")
    val starCount: Long,
    @SerialName("upgrade_star_count")
    val upgradeStarCount: Long? = null,
    @SerialName("is_premium")
    val isPremium: Boolean? = null,
    @SerialName("has_colors")
    val hasColors: Boolean? = null,
    @SerialName("total_count")
    val totalCount: Long? = null,
    @SerialName("remaining_count")
    val remainingCount: Long? = null,
    @SerialName("personal_total_count")
    val personalTotalCount: Long? = null,
    @SerialName("personal_remaining_count")
    val personalRemainingCount: Long? = null,
    @SerialName("background")
    val background: GiftBackground? = null,
    @SerialName("unique_gift_variant_count")
    val uniqueGiftVariantCount: Long? = null,
    @SerialName("publisher_chat")
    val publisherChat: Chat? = null
)

@Serializable
data class GiftBackground(
    @SerialName("center_color")
    val centerColor: Long,
    @SerialName("edge_color")
    val edgeColor: Long,
    @SerialName("text_color")
    val textColor: Long
)

@Serializable
data class GiftInfo(
    @SerialName("gift")
    val gift: Gift,
    @SerialName("owned_gift_id")
    val ownedGiftId: String? = null,
    @SerialName("convert_star_count")
    val convertStarCount: Long? = null,
    @SerialName("prepaid_upgrade_star_count")
    val prepaidUpgradeStarCount: Long? = null,
    @SerialName("is_upgrade_separate")
    val isUpgradeSeparate: Boolean? = null,
    @SerialName("can_be_upgraded")
    val canBeUpgraded: Boolean? = null,
    @SerialName("text")
    val text: String? = null,
    @SerialName("entities")
    val entities: List<MessageEntity>? = null,
    @SerialName("is_private")
    val isPrivate: Boolean? = null,
    @SerialName("unique_gift_number")
    val uniqueGiftNumber: Long? = null
)

@Serializable
data class Gifts(
    @SerialName("gifts")
    val gifts: List<Gift>
)
