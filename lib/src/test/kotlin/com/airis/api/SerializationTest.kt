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
