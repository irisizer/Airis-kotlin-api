package com.airis.api.types

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Sealed interfaces for polymorphic Telegram types (Bot API 10.3).
 * Serializers use `type`/`status`/`source` discriminators for optimal performance.
 */

@Serializable(with = MaybeInaccessibleMessageSerializer::class)
sealed interface MaybeInaccessibleMessage

object MaybeInaccessibleMessageSerializer : JsonContentPolymorphicSerializer<MaybeInaccessibleMessage>(MaybeInaccessibleMessage::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<MaybeInaccessibleMessage> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for MaybeInaccessibleMessage")
        // Special: date==0 -> InaccessibleMessage, else Message
        val date = obj["date"]?.jsonPrimitive?.longOrNull ?: 0L
        // InaccessibleMessage has only chat/message_id/date with date==0; Message has date!=0 in practice
        // Heuristic: if date==0 and no 'from'/'text'/etc -> Inaccessible
        val hasMessageFields = obj.containsKey("from") || obj.containsKey("text") || obj.containsKey("photo") || obj.containsKey("entities")
        return if (date == 0L && !hasMessageFields) InaccessibleMessage.serializer() as kotlinx.serialization.DeserializationStrategy<MaybeInaccessibleMessage> else Message.serializer() as kotlinx.serialization.DeserializationStrategy<MaybeInaccessibleMessage>
    }
}

@Serializable(with = MessageOriginSerializer::class)
sealed interface MessageOrigin

object MessageOriginSerializer : JsonContentPolymorphicSerializer<MessageOrigin>(MessageOrigin::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<MessageOrigin> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for MessageOrigin")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "user" -> MessageOriginUser.serializer() as kotlinx.serialization.DeserializationStrategy<MessageOrigin>
            "hidden_user" -> MessageOriginHiddenUser.serializer() as kotlinx.serialization.DeserializationStrategy<MessageOrigin>
            "chat" -> MessageOriginChat.serializer() as kotlinx.serialization.DeserializationStrategy<MessageOrigin>
            "channel" -> MessageOriginChannel.serializer() as kotlinx.serialization.DeserializationStrategy<MessageOrigin>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for MessageOrigin")
        }
    }
}

@Serializable(with = PaidMediaSerializer::class)
sealed interface PaidMedia

object PaidMediaSerializer : JsonContentPolymorphicSerializer<PaidMedia>(PaidMedia::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<PaidMedia> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for PaidMedia")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "live_photo" -> PaidMediaLivePhoto.serializer() as kotlinx.serialization.DeserializationStrategy<PaidMedia>
            "photo" -> PaidMediaPhoto.serializer() as kotlinx.serialization.DeserializationStrategy<PaidMedia>
            "preview" -> PaidMediaPreview.serializer() as kotlinx.serialization.DeserializationStrategy<PaidMedia>
            "video" -> PaidMediaVideo.serializer() as kotlinx.serialization.DeserializationStrategy<PaidMedia>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for PaidMedia")
        }
    }
}

@Serializable(with = InputPollMediaSerializer::class)
sealed interface InputPollMedia

object InputPollMediaSerializer : JsonContentPolymorphicSerializer<InputPollMedia>(InputPollMedia::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<InputPollMedia> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for InputPollMedia")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "animation" -> InputMediaAnimation.serializer() as kotlinx.serialization.DeserializationStrategy<InputPollMedia>
            "audio" -> InputMediaAudio.serializer() as kotlinx.serialization.DeserializationStrategy<InputPollMedia>
            "document" -> InputMediaDocument.serializer() as kotlinx.serialization.DeserializationStrategy<InputPollMedia>
            "live_photo" -> InputMediaLivePhoto.serializer() as kotlinx.serialization.DeserializationStrategy<InputPollMedia>
            "location" -> InputMediaLocation.serializer() as kotlinx.serialization.DeserializationStrategy<InputPollMedia>
            "photo" -> InputMediaPhoto.serializer() as kotlinx.serialization.DeserializationStrategy<InputPollMedia>
            "venue" -> InputMediaVenue.serializer() as kotlinx.serialization.DeserializationStrategy<InputPollMedia>
            "video" -> InputMediaVideo.serializer() as kotlinx.serialization.DeserializationStrategy<InputPollMedia>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for InputPollMedia")
        }
    }
}

@Serializable(with = InputPollOptionMediaSerializer::class)
sealed interface InputPollOptionMedia

object InputPollOptionMediaSerializer : JsonContentPolymorphicSerializer<InputPollOptionMedia>(InputPollOptionMedia::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<InputPollOptionMedia> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for InputPollOptionMedia")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "animation" -> InputMediaAnimation.serializer() as kotlinx.serialization.DeserializationStrategy<InputPollOptionMedia>
            "link" -> InputMediaLink.serializer() as kotlinx.serialization.DeserializationStrategy<InputPollOptionMedia>
            "live_photo" -> InputMediaLivePhoto.serializer() as kotlinx.serialization.DeserializationStrategy<InputPollOptionMedia>
            "location" -> InputMediaLocation.serializer() as kotlinx.serialization.DeserializationStrategy<InputPollOptionMedia>
            "photo" -> InputMediaPhoto.serializer() as kotlinx.serialization.DeserializationStrategy<InputPollOptionMedia>
            "sticker" -> InputMediaSticker.serializer() as kotlinx.serialization.DeserializationStrategy<InputPollOptionMedia>
            "venue" -> InputMediaVenue.serializer() as kotlinx.serialization.DeserializationStrategy<InputPollOptionMedia>
            "video" -> InputMediaVideo.serializer() as kotlinx.serialization.DeserializationStrategy<InputPollOptionMedia>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for InputPollOptionMedia")
        }
    }
}

@Serializable(with = BackgroundFillSerializer::class)
sealed interface BackgroundFill

object BackgroundFillSerializer : JsonContentPolymorphicSerializer<BackgroundFill>(BackgroundFill::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<BackgroundFill> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for BackgroundFill")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "solid" -> BackgroundFillSolid.serializer() as kotlinx.serialization.DeserializationStrategy<BackgroundFill>
            "gradient" -> BackgroundFillGradient.serializer() as kotlinx.serialization.DeserializationStrategy<BackgroundFill>
            "freeform_gradient" -> BackgroundFillFreeformGradient.serializer() as kotlinx.serialization.DeserializationStrategy<BackgroundFill>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for BackgroundFill")
        }
    }
}

@Serializable(with = BackgroundTypeSerializer::class)
sealed interface BackgroundType

object BackgroundTypeSerializer : JsonContentPolymorphicSerializer<BackgroundType>(BackgroundType::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<BackgroundType> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for BackgroundType")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "fill" -> BackgroundTypeFill.serializer() as kotlinx.serialization.DeserializationStrategy<BackgroundType>
            "wallpaper" -> BackgroundTypeWallpaper.serializer() as kotlinx.serialization.DeserializationStrategy<BackgroundType>
            "pattern" -> BackgroundTypePattern.serializer() as kotlinx.serialization.DeserializationStrategy<BackgroundType>
            "chat_theme" -> BackgroundTypeChatTheme.serializer() as kotlinx.serialization.DeserializationStrategy<BackgroundType>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for BackgroundType")
        }
    }
}

@Serializable(with = ChatMemberSerializer::class)
sealed interface ChatMember

object ChatMemberSerializer : JsonContentPolymorphicSerializer<ChatMember>(ChatMember::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<ChatMember> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for ChatMember")
        val disc = obj["status"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "creator" -> ChatMemberOwner.serializer() as kotlinx.serialization.DeserializationStrategy<ChatMember>
            "administrator" -> ChatMemberAdministrator.serializer() as kotlinx.serialization.DeserializationStrategy<ChatMember>
            "member" -> ChatMemberMember.serializer() as kotlinx.serialization.DeserializationStrategy<ChatMember>
            "restricted" -> ChatMemberRestricted.serializer() as kotlinx.serialization.DeserializationStrategy<ChatMember>
            "left" -> ChatMemberLeft.serializer() as kotlinx.serialization.DeserializationStrategy<ChatMember>
            "kicked" -> ChatMemberBanned.serializer() as kotlinx.serialization.DeserializationStrategy<ChatMember>
            else -> throw IllegalArgumentException("Unknown status '" + disc + "' for ChatMember")
        }
    }
}

@Serializable(with = StoryAreaTypeSerializer::class)
sealed interface StoryAreaType

object StoryAreaTypeSerializer : JsonContentPolymorphicSerializer<StoryAreaType>(StoryAreaType::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<StoryAreaType> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for StoryAreaType")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "location" -> StoryAreaTypeLocation.serializer() as kotlinx.serialization.DeserializationStrategy<StoryAreaType>
            "suggested_reaction" -> StoryAreaTypeSuggestedReaction.serializer() as kotlinx.serialization.DeserializationStrategy<StoryAreaType>
            "link" -> StoryAreaTypeLink.serializer() as kotlinx.serialization.DeserializationStrategy<StoryAreaType>
            "weather" -> StoryAreaTypeWeather.serializer() as kotlinx.serialization.DeserializationStrategy<StoryAreaType>
            "unique_gift" -> StoryAreaTypeUniqueGift.serializer() as kotlinx.serialization.DeserializationStrategy<StoryAreaType>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for StoryAreaType")
        }
    }
}

@Serializable(with = ReactionTypeSerializer::class)
sealed interface ReactionType

object ReactionTypeSerializer : JsonContentPolymorphicSerializer<ReactionType>(ReactionType::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<ReactionType> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for ReactionType")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "emoji" -> ReactionTypeEmoji.serializer() as kotlinx.serialization.DeserializationStrategy<ReactionType>
            "custom_emoji" -> ReactionTypeCustomEmoji.serializer() as kotlinx.serialization.DeserializationStrategy<ReactionType>
            "paid" -> ReactionTypePaid.serializer() as kotlinx.serialization.DeserializationStrategy<ReactionType>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for ReactionType")
        }
    }
}

@Serializable(with = OwnedGiftSerializer::class)
sealed interface OwnedGift

object OwnedGiftSerializer : JsonContentPolymorphicSerializer<OwnedGift>(OwnedGift::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<OwnedGift> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for OwnedGift")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "regular" -> OwnedGiftRegular.serializer() as kotlinx.serialization.DeserializationStrategy<OwnedGift>
            "unique" -> OwnedGiftUnique.serializer() as kotlinx.serialization.DeserializationStrategy<OwnedGift>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for OwnedGift")
        }
    }
}

@Serializable(with = BotCommandScopeSerializer::class)
sealed interface BotCommandScope

object BotCommandScopeSerializer : JsonContentPolymorphicSerializer<BotCommandScope>(BotCommandScope::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<BotCommandScope> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for BotCommandScope")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "default" -> BotCommandScopeDefault.serializer() as kotlinx.serialization.DeserializationStrategy<BotCommandScope>
            "all_private_chats" -> BotCommandScopeAllPrivateChats.serializer() as kotlinx.serialization.DeserializationStrategy<BotCommandScope>
            "all_group_chats" -> BotCommandScopeAllGroupChats.serializer() as kotlinx.serialization.DeserializationStrategy<BotCommandScope>
            "all_chat_administrators" -> BotCommandScopeAllChatAdministrators.serializer() as kotlinx.serialization.DeserializationStrategy<BotCommandScope>
            "chat" -> BotCommandScopeChat.serializer() as kotlinx.serialization.DeserializationStrategy<BotCommandScope>
            "chat_administrators" -> BotCommandScopeChatAdministrators.serializer() as kotlinx.serialization.DeserializationStrategy<BotCommandScope>
            "chat_member" -> BotCommandScopeChatMember.serializer() as kotlinx.serialization.DeserializationStrategy<BotCommandScope>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for BotCommandScope")
        }
    }
}

@Serializable(with = MenuButtonSerializer::class)
sealed interface MenuButton

object MenuButtonSerializer : JsonContentPolymorphicSerializer<MenuButton>(MenuButton::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<MenuButton> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for MenuButton")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "commands" -> MenuButtonCommands.serializer() as kotlinx.serialization.DeserializationStrategy<MenuButton>
            "web_app" -> MenuButtonWebApp.serializer() as kotlinx.serialization.DeserializationStrategy<MenuButton>
            "default" -> MenuButtonDefault.serializer() as kotlinx.serialization.DeserializationStrategy<MenuButton>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for MenuButton")
        }
    }
}

@Serializable(with = ChatBoostSourceSerializer::class)
sealed interface ChatBoostSource

object ChatBoostSourceSerializer : JsonContentPolymorphicSerializer<ChatBoostSource>(ChatBoostSource::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<ChatBoostSource> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for ChatBoostSource")
        val disc = obj["source"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "premium" -> ChatBoostSourcePremium.serializer() as kotlinx.serialization.DeserializationStrategy<ChatBoostSource>
            "gift_code" -> ChatBoostSourceGiftCode.serializer() as kotlinx.serialization.DeserializationStrategy<ChatBoostSource>
            "giveaway" -> ChatBoostSourceGiveaway.serializer() as kotlinx.serialization.DeserializationStrategy<ChatBoostSource>
            else -> throw IllegalArgumentException("Unknown source '" + disc + "' for ChatBoostSource")
        }
    }
}

@Serializable(with = InputMediaSerializer::class)
sealed interface InputMedia

object InputMediaSerializer : JsonContentPolymorphicSerializer<InputMedia>(InputMedia::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<InputMedia> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for InputMedia")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "animation" -> InputMediaAnimation.serializer() as kotlinx.serialization.DeserializationStrategy<InputMedia>
            "audio" -> InputMediaAudio.serializer() as kotlinx.serialization.DeserializationStrategy<InputMedia>
            "document" -> InputMediaDocument.serializer() as kotlinx.serialization.DeserializationStrategy<InputMedia>
            "live_photo" -> InputMediaLivePhoto.serializer() as kotlinx.serialization.DeserializationStrategy<InputMedia>
            "photo" -> InputMediaPhoto.serializer() as kotlinx.serialization.DeserializationStrategy<InputMedia>
            "video" -> InputMediaVideo.serializer() as kotlinx.serialization.DeserializationStrategy<InputMedia>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for InputMedia")
        }
    }
}

@Serializable(with = InputPaidMediaSerializer::class)
sealed interface InputPaidMedia

object InputPaidMediaSerializer : JsonContentPolymorphicSerializer<InputPaidMedia>(InputPaidMedia::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<InputPaidMedia> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for InputPaidMedia")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "live_photo" -> InputPaidMediaLivePhoto.serializer() as kotlinx.serialization.DeserializationStrategy<InputPaidMedia>
            "photo" -> InputPaidMediaPhoto.serializer() as kotlinx.serialization.DeserializationStrategy<InputPaidMedia>
            "video" -> InputPaidMediaVideo.serializer() as kotlinx.serialization.DeserializationStrategy<InputPaidMedia>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for InputPaidMedia")
        }
    }
}

@Serializable(with = InputProfilePhotoSerializer::class)
sealed interface InputProfilePhoto

object InputProfilePhotoSerializer : JsonContentPolymorphicSerializer<InputProfilePhoto>(InputProfilePhoto::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<InputProfilePhoto> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for InputProfilePhoto")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "static" -> InputProfilePhotoStatic.serializer() as kotlinx.serialization.DeserializationStrategy<InputProfilePhoto>
            "animated" -> InputProfilePhotoAnimated.serializer() as kotlinx.serialization.DeserializationStrategy<InputProfilePhoto>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for InputProfilePhoto")
        }
    }
}

@Serializable(with = InputStoryContentSerializer::class)
sealed interface InputStoryContent

object InputStoryContentSerializer : JsonContentPolymorphicSerializer<InputStoryContent>(InputStoryContent::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<InputStoryContent> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for InputStoryContent")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "photo" -> InputStoryContentPhoto.serializer() as kotlinx.serialization.DeserializationStrategy<InputStoryContent>
            "video" -> InputStoryContentVideo.serializer() as kotlinx.serialization.DeserializationStrategy<InputStoryContent>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for InputStoryContent")
        }
    }
}

@Serializable(with = RichTextSerializer::class)
sealed interface RichText

object RichTextSerializer : JsonContentPolymorphicSerializer<RichText>(RichText::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<RichText> {
        // Bot API: RichText = String (plain) | Array<RichText> | object
        // (https://core.telegram.org/bots/api#richtext). Plain spans arrive
        // as bare strings — without this the whole getUpdates batch fails.
        if (element is JsonPrimitive) return RichTextPlain.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
        if (element is JsonArray) return RichTextArray.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for RichText")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "bold" -> RichTextBold.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "italic" -> RichTextItalic.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "underline" -> RichTextUnderline.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "strikethrough" -> RichTextStrikethrough.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "spoiler" -> RichTextSpoiler.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "date_time" -> RichTextDateTime.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "text_mention" -> RichTextTextMention.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "subscript" -> RichTextSubscript.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "superscript" -> RichTextSuperscript.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "marked" -> RichTextMarked.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "code" -> RichTextCode.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "custom_emoji" -> RichTextCustomEmoji.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "mathematical_expression" -> RichTextMathematicalExpression.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "url" -> RichTextUrl.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "email_address" -> RichTextEmailAddress.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "phone_number" -> RichTextPhoneNumber.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "bank_card_number" -> RichTextBankCardNumber.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "mention" -> RichTextMention.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "hashtag" -> RichTextHashtag.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "cashtag" -> RichTextCashtag.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "bot_command" -> RichTextBotCommand.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "button" -> RichTextButton.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "anchor" -> RichTextAnchor.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "anchor_link" -> RichTextAnchorLink.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "reference" -> RichTextReference.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            "reference_link" -> RichTextReferenceLink.serializer() as kotlinx.serialization.DeserializationStrategy<RichText>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for RichText")
        }
    }
}

@Serializable(with = RichBlockSerializer::class)
sealed interface RichBlock

object RichBlockSerializer : JsonContentPolymorphicSerializer<RichBlock>(RichBlock::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<RichBlock> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for RichBlock")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "paragraph" -> RichBlockParagraph.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "heading" -> RichBlockSectionHeading.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "pre" -> RichBlockPreformatted.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "footer" -> RichBlockFooter.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "divider" -> RichBlockDivider.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "mathematical_expression" -> RichBlockMathematicalExpression.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "anchor" -> RichBlockAnchor.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "list" -> RichBlockList.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "blockquote" -> RichBlockBlockQuotation.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "expandable_blockquote" -> RichBlockExpandableBlockQuotation.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "pullquote" -> RichBlockPullQuotation.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "collage" -> RichBlockCollage.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "slideshow" -> RichBlockSlideshow.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "table" -> RichBlockTable.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "details" -> RichBlockDetails.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "map" -> RichBlockMap.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "buttons" -> RichBlockButtons.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "animation" -> RichBlockAnimation.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "audio" -> RichBlockAudio.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "document" -> RichBlockDocument.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "photo" -> RichBlockPhoto.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "video" -> RichBlockVideo.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "voice_note" -> RichBlockVoiceNote.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            "thinking" -> RichBlockThinking.serializer() as kotlinx.serialization.DeserializationStrategy<RichBlock>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for RichBlock")
        }
    }
}

@Serializable(with = InputRichBlockSerializer::class)
sealed interface InputRichBlock

object InputRichBlockSerializer : JsonContentPolymorphicSerializer<InputRichBlock>(InputRichBlock::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<InputRichBlock> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for InputRichBlock")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "paragraph" -> InputRichBlockParagraph.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "heading" -> InputRichBlockSectionHeading.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "pre" -> InputRichBlockPreformatted.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "footer" -> InputRichBlockFooter.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "divider" -> InputRichBlockDivider.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "mathematical_expression" -> InputRichBlockMathematicalExpression.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "anchor" -> InputRichBlockAnchor.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "list" -> InputRichBlockList.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "blockquote" -> InputRichBlockBlockQuotation.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "expandable_blockquote" -> InputRichBlockExpandableBlockQuotation.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "pullquote" -> InputRichBlockPullQuotation.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "collage" -> InputRichBlockCollage.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "slideshow" -> InputRichBlockSlideshow.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "table" -> InputRichBlockTable.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "details" -> InputRichBlockDetails.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "map" -> InputRichBlockMap.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "buttons" -> InputRichBlockButtons.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "animation" -> InputRichBlockAnimation.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "audio" -> InputRichBlockAudio.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "document" -> InputRichBlockDocument.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "photo" -> InputRichBlockPhoto.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "video" -> InputRichBlockVideo.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "voice_note" -> InputRichBlockVoiceNote.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            "thinking" -> InputRichBlockThinking.serializer() as kotlinx.serialization.DeserializationStrategy<InputRichBlock>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for InputRichBlock")
        }
    }
}

@Serializable(with = InlineQueryResultSerializer::class)
sealed interface InlineQueryResult

object InlineQueryResultSerializer : JsonContentPolymorphicSerializer<InlineQueryResult>(InlineQueryResult::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<InlineQueryResult> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for InlineQueryResult")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "article" -> InlineQueryResultArticle.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult>
            "contact" -> InlineQueryResultContact.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult>
            "game" -> InlineQueryResultGame.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult>
            "location" -> InlineQueryResultLocation.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult>
            "venue" -> InlineQueryResultVenue.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult>
            "sticker" -> InlineQueryResultCachedSticker.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult>
            "audio" -> if (obj.containsKey("audio_file_id")) InlineQueryResultCachedAudio.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult> else InlineQueryResultAudio.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult>
            "document" -> if (obj.containsKey("document_file_id")) InlineQueryResultCachedDocument.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult> else InlineQueryResultDocument.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult>
            "gif" -> if (obj.containsKey("gif_file_id")) InlineQueryResultCachedGif.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult> else InlineQueryResultGif.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult>
            "mpeg4_gif" -> if (obj.containsKey("mpeg4_file_id")) InlineQueryResultCachedMpeg4Gif.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult> else InlineQueryResultMpeg4Gif.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult>
            "photo" -> if (obj.containsKey("photo_file_id")) InlineQueryResultCachedPhoto.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult> else InlineQueryResultPhoto.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult>
            "video" -> if (obj.containsKey("video_file_id")) InlineQueryResultCachedVideo.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult> else InlineQueryResultVideo.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult>
            "voice" -> if (obj.containsKey("voice_file_id")) InlineQueryResultCachedVoice.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult> else InlineQueryResultVoice.serializer() as kotlinx.serialization.DeserializationStrategy<InlineQueryResult>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for InlineQueryResult")
        }
    }
}

@Serializable(with = InputMessageContentSerializer::class)
sealed interface InputMessageContent

object InputMessageContentSerializer : JsonContentPolymorphicSerializer<InputMessageContent>(InputMessageContent::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<InputMessageContent> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for InputMessageContent")
        // InputMessageContent has no 'type' discriminator; detect by distinctive fields
        return when {
            obj.containsKey("message_text") -> InputTextMessageContent.serializer() as kotlinx.serialization.DeserializationStrategy<InputMessageContent>
            obj.containsKey("rich_message") -> InputRichMessageContent.serializer() as kotlinx.serialization.DeserializationStrategy<InputMessageContent>
            obj.containsKey("phone_number") -> InputContactMessageContent.serializer() as kotlinx.serialization.DeserializationStrategy<InputMessageContent>
            obj.containsKey("payload") -> InputInvoiceMessageContent.serializer() as kotlinx.serialization.DeserializationStrategy<InputMessageContent>
            obj.containsKey("address") -> InputVenueMessageContent.serializer() as kotlinx.serialization.DeserializationStrategy<InputMessageContent>
            obj.containsKey("latitude") -> InputLocationMessageContent.serializer() as kotlinx.serialization.DeserializationStrategy<InputMessageContent>
            else -> throw IllegalArgumentException("Unknown InputMessageContent shape: " + obj.toString().take(200))
        }
    }
}

@Serializable(with = RevenueWithdrawalStateSerializer::class)
sealed interface RevenueWithdrawalState

object RevenueWithdrawalStateSerializer : JsonContentPolymorphicSerializer<RevenueWithdrawalState>(RevenueWithdrawalState::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<RevenueWithdrawalState> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for RevenueWithdrawalState")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "pending" -> RevenueWithdrawalStatePending.serializer() as kotlinx.serialization.DeserializationStrategy<RevenueWithdrawalState>
            "succeeded" -> RevenueWithdrawalStateSucceeded.serializer() as kotlinx.serialization.DeserializationStrategy<RevenueWithdrawalState>
            "failed" -> RevenueWithdrawalStateFailed.serializer() as kotlinx.serialization.DeserializationStrategy<RevenueWithdrawalState>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for RevenueWithdrawalState")
        }
    }
}

@Serializable(with = TransactionPartnerSerializer::class)
sealed interface TransactionPartner

object TransactionPartnerSerializer : JsonContentPolymorphicSerializer<TransactionPartner>(TransactionPartner::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<TransactionPartner> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for TransactionPartner")
        val disc = obj["type"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "user" -> TransactionPartnerUser.serializer() as kotlinx.serialization.DeserializationStrategy<TransactionPartner>
            "chat" -> TransactionPartnerChat.serializer() as kotlinx.serialization.DeserializationStrategy<TransactionPartner>
            "affiliate_program" -> TransactionPartnerAffiliateProgram.serializer() as kotlinx.serialization.DeserializationStrategy<TransactionPartner>
            "fragment" -> TransactionPartnerFragment.serializer() as kotlinx.serialization.DeserializationStrategy<TransactionPartner>
            "telegram_ads" -> TransactionPartnerTelegramAds.serializer() as kotlinx.serialization.DeserializationStrategy<TransactionPartner>
            "telegram_api" -> TransactionPartnerTelegramApi.serializer() as kotlinx.serialization.DeserializationStrategy<TransactionPartner>
            "other" -> TransactionPartnerOther.serializer() as kotlinx.serialization.DeserializationStrategy<TransactionPartner>
            else -> throw IllegalArgumentException("Unknown type '" + disc + "' for TransactionPartner")
        }
    }
}

@Serializable(with = PassportElementErrorSerializer::class)
sealed interface PassportElementError

object PassportElementErrorSerializer : JsonContentPolymorphicSerializer<PassportElementError>(PassportElementError::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<PassportElementError> {
        val obj = element as? JsonObject ?: throw IllegalArgumentException("Expected JsonObject for PassportElementError")
        val disc = obj["source"]?.jsonPrimitive?.contentOrNull
        return when (disc) {
            "data" -> PassportElementErrorDataField.serializer() as kotlinx.serialization.DeserializationStrategy<PassportElementError>
            "front_side" -> PassportElementErrorFrontSide.serializer() as kotlinx.serialization.DeserializationStrategy<PassportElementError>
            "reverse_side" -> PassportElementErrorReverseSide.serializer() as kotlinx.serialization.DeserializationStrategy<PassportElementError>
            "selfie" -> PassportElementErrorSelfie.serializer() as kotlinx.serialization.DeserializationStrategy<PassportElementError>
            "file" -> PassportElementErrorFile.serializer() as kotlinx.serialization.DeserializationStrategy<PassportElementError>
            "files" -> PassportElementErrorFiles.serializer() as kotlinx.serialization.DeserializationStrategy<PassportElementError>
            "translation_file" -> PassportElementErrorTranslationFile.serializer() as kotlinx.serialization.DeserializationStrategy<PassportElementError>
            "translation_files" -> PassportElementErrorTranslationFiles.serializer() as kotlinx.serialization.DeserializationStrategy<PassportElementError>
            "unspecified" -> PassportElementErrorUnspecified.serializer() as kotlinx.serialization.DeserializationStrategy<PassportElementError>
            else -> throw IllegalArgumentException("Unknown source '" + disc + "' for PassportElementError")
        }
    }
}
