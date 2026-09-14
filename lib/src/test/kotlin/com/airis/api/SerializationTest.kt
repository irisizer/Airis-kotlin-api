package com.airis.api

import com.airis.api.types.*
import com.airis.api.utils.TelegramJson
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SerializationTest {
    @Test
    fun testUserRoundTrip() {
        val u = User(id = 123L, isBot = false, firstName = "Test", username = "testbot")
        val json = TelegramJson.encodeToString(User.serializer(), u)
        val back = TelegramJson.decodeFromString(User.serializer(), json)
        assertEquals(u, back)
    }

    @Test
    fun testChatId() {
        val id: ChatId = 123L.toChatId()
        val el = TelegramJson.encodeToJsonElement(ChatId.serializer(), id)
        assertEquals("123", el.toString())
        val user: ChatId = "@durov".toChatId()
        val el2 = TelegramJson.encodeToJsonElement(ChatId.serializer(), user)
        assertTrue(el2.toString().contains("durov"))
    }

    @Test
    fun testInputFile() {
        val f: InputFile = "file_id_123".asFileId()
        val el = TelegramJson.encodeToJsonElement(InputFile.serializer(), f)
        assertEquals("\"file_id_123\"", el.toString())
        val up = InputFile.fromBytes("photo.jpg", byteArrayOf(1, 2, 3))
        val el2 = TelegramJson.encodeToJsonElement(InputFile.serializer(), up)
        assertEquals("\"attach://photo.jpg\"", el2.toString())
    }

    @Test
    fun testUpdateDeserialization() {
        val json = """{"update_id":1,"message":{"message_id":1,"date":1720000000,"chat":{"id":1,"type":"private"},"text":"hi"}}"""
        val u = TelegramJson.decodeFromString(Update.serializer(), json)
        assertEquals(1L, u.updateId)
        assertEquals("hi", u.message?.text)
    }

    @Test
    fun testReplyMarkup() {
        val m = InlineKeyboardMarkup(
            inlineKeyboard = listOf(listOf(InlineKeyboardButton(text = "btn", callbackData = "x")))
        )
        val el = TelegramJson.encodeToJsonElement(InlineKeyboardMarkup.serializer(), m)
        assertTrue(el.toString().contains("inline_keyboard"))
        val rm: ReplyMarkup = m
        val el2 = TelegramJson.encodeToJsonElement(ReplyMarkup.serializer(), rm)
        assertTrue(el2.toString().contains("inline_keyboard"))
    }

    @Test
    fun testChatMemberPolymorphic() {
        val json = """{"status":"creator","user":{"id":1,"is_bot":false,"first_name":"A"},"is_anonymous":false}"""
        val cm = TelegramJson.decodeFromString(ChatMember.serializer(), json)
        assertTrue(cm is ChatMemberOwner)
    }

    @Test
    fun testMessageOrTrue() {
        val b = TelegramJson.decodeFromString(MessageOrTrue.serializer(), "true")
        assertTrue(b is MessageOrTrue.OfBoolean)
    }
}

class RichTextShapesTest {
    // Bot API: RichText = String (plain) | Array<RichText> | object.
    // Regression: bare strings used to kill the whole getUpdates batch.
    @Test
    fun testRichTextPlainString() {
        val el = TelegramJson.decodeFromString(
            RichText.serializer(), """"plain span""""
        )
        assertTrue(el is RichTextPlain)
        assertEquals("plain span", (el as RichTextPlain).text)
    }

    @Test
    fun testRichTextArray() {
        val el = TelegramJson.decodeFromString(
            RichText.serializer(), """["a", {"type": "bold", "text": "b"}]"""
        )
        assertTrue(el is RichTextArray)
        val items = (el as RichTextArray).items
        assertEquals(2, items.size)
        assertTrue(items[0] is RichTextPlain)
        assertTrue(items[1] is RichTextBold)
    }

    @Test
    fun testCallbackWithRichPanel() {
        //Like the real 03:13 incident: callback_query.message carries rich_message
        // with plain-string spans — must parse, not wedge polling.
        val json = """{"update_id":9,"callback_query":{"id":"cb1","from":{"id":1,"is_bot":false,"first_name":"T"},"chat_instance":"x","data":"richmode_classic","message":{"message_id":5,"date":1726000000,"chat":{"id":-1001,"type":"supergroup"},"rich_message":{"blocks":[{"type":"paragraph","text":["line1",{"type":"bold","text":"line2"}]}]}}}}"""
        val u = TelegramJson.decodeFromString(Update.serializer(), json)
        assertEquals("richmode_classic", u.callbackQuery?.data)
        val msg = u.callbackQuery?.message as? Message
        assertTrue(msg?.richMessage != null)
    }
}

class ButtonStyleTest {
    // style/icon_custom_emoji_id обязаны уходить на провод как есть.
    @Test
    fun testInlineButtonStyleAndIcon() {
        val b = InlineKeyboardButton(
            text = "Hide",
            callbackData = "hide",
            style = "danger",
            iconCustomEmojiId = "5310132169978326164"
        )
        val json = TelegramJson.encodeToString(InlineKeyboardButton.serializer(), b)
        assertTrue(json.contains("\"style\":\"danger\""), json)
        assertTrue(json.contains("\"icon_custom_emoji_id\":\"5310132169978326164\""), json)
        assertTrue(json.contains("\"callback_data\":\"hide\""), json)
        // round-trip
        val back = TelegramJson.decodeFromString(InlineKeyboardButton.serializer(), json)
        assertEquals("danger", back.style)
        assertEquals("5310132169978326164", back.iconCustomEmojiId)
    }

    @Test
    fun testAllButtonStyles() {
        for (s in listOf("danger", "success", "primary")) {
            val b = InlineKeyboardButton(text = "T", callbackData = "d", style = s)
            val json = TelegramJson.encodeToString(InlineKeyboardButton.serializer(), b)
            assertTrue(json.contains("\"style\":\"$s\""), json)
        }
        // KeyboardButton (reply) — те же поля
        val kb = KeyboardButton(text = "T", style = "success")
        val kbJson = TelegramJson.encodeToString(KeyboardButton.serializer(), kb)
        assertTrue(kbJson.contains("\"style\":\"success\""), kbJson)
    }
}

class GuestModeTest {
    // Guest Mode (Bot API 10.0): guest_message + answerGuestQuery.
    @Test
    fun testGuestMessageUpdate() {
        val json = """{"update_id":7,"guest_message":{"message_id":3,"date":1726000000,"chat":{"id":-1001,"type":"supergroup"},"from":{"id":42,"is_bot":false,"first_name":"G"},"text":"hi","guest_query_id":"gq1"}}"""
        val u = TelegramJson.decodeFromString(Update.serializer(), json)
        assertEquals("gq1", u.guestMessage?.guestQueryId)
        assertEquals("hi", u.guestMessage?.text)
    }
}
