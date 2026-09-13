# Airis Kotlin Bot API — Telegram Bot API 10.3

Собственная Kotlin-библиотека Telegram Bot API **10.3** (последняя версия на **12 сентября 2026**, релиз от **24 августа 2026**).

- ✅ Все **185 методов** Bot API (getMe … sendGame, sendRichMessage, editEphemeralMessage*, sendInvoice, setPassportDataErrors и т.д.)
- ✅ Все **~400 типов** (Message, Update, Chat, User, Poll, RichText/RichBlock 50+ видов, InlineQueryResult 20 видов, TransactionPartner, Passport, Gifts и т.д.)
- ✅ Long polling (`LongPolling`) + Webhook (`WebhookReceiver`, Ktor CIO) + прямые вызовы API
- ✅ Оптимально: **Ktor CIO** (корутины, zero native deps) + **kotlinx.serialization** (codegen, `ignoreUnknownKeys=true`, `explicitNulls=false`)

## Быстрый старт

```kotlin
val bot = AirisBot(token = "123456:ABC-DEF")

// Простой вызов
val me = bot.getMe()
println(me.username)

// Отправка
bot.sendMessage(chatId = 123456L.toChatId(), text = "Привет!")

// Кнопки
bot.sendMessage(
  chatId = "@mychannel".toChatId(),
  text = "Выбери:",
  replyMarkup = InlineKeyboardMarkup(
    inlineKeyboard = listOf(listOf(InlineKeyboardButton(text = "OK", callbackData = "ok")))
  )
)

// Файл
bot.sendPhoto(chatId = 1L.toChatId(), photo = java.io.File("pic.jpg").asInputFile(), caption = "фото")

// Long polling
bot.startPolling { update ->
  println(update)
  update.message?.text?.let { bot.sendMessage(update.message!!.chat.id.toChatId(), "Эхо: $it") }
}

// Webhook (свой сервер)
WebhookReceiver(port = 8080, path = "/webhook", secretToken = "s3cr3t", onUpdate = { /* ... */ }).start(wait = true)

// Локальный Bot API сервер
val local = AirisBot(token, baseUrl = "http://localhost:8081")
```

## Установка (Gradle)

```kotlin
dependencies {
  implementation("com.airis:airis-kotlin-api:10.3.0")
}
```

## Структура

- `com.airis.api.client.AirisBot` — все 185 suspend-методов
- `com.airis.api.client.ApiClient` — Ktor CIO транспорт (JSON / multipart, 429 retry_after)
- `com.airis.api.types.*` — все типы + `ChatId`, `InputFile`, `ReplyMarkup`, `MessageOrTrue`, полиморфные сериализаторы
- `com.airis.api.polling.LongPolling` — long polling с offset + backoff
- `com.airis.api.webhook.WebhookReceiver` — webhook-сервер + `parseWebhookUpdate`
- `com.airis.api.utils.TelegramJson` — общий Json (optimal)

## Версия API

- Bot API **10.3** (24.08.2026): Rich Messages (`RichBlock*`, `InputRichBlock*`, `sendRichMessage`), Ephemeral (`EphemeralMessageParameters`, `editEphemeralMessage*`), `DisabledButton`, `MessageGenerationStopped`, `CommunityChatJoined`, `can_send_welcome_messages`, `can_stop`/`keep_on_stop` в драфтах.
- Предыдущие: 10.2 (14.07.2026), 10.1 (11.06.2026), 10.0 (08.05.2026), 9.6–9.0.

## Лицензия

MIT
