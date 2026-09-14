# Airis Kotlin Bot API — Telegram Bot API 10.3

Kotlin-библиотека Telegram Bot API **10.3** (релиз Bot API от **24 августа 2026**).

Решил написать свою библиотеку для работы с Bot API, т.к на Kotlin с этим проблемы))

Возможно, буду развивать её по мере апдейтов Bot API

## Быстрый старт

```kotlin
val bot = AirisBot(token = "123456:ABC-DEF")

// вызов
val me = bot.getMe()
println(me.username)

// отправка
bot.sendMessage(chatId = 123456L.toChatId(), text = "Привет!")

// кнопки
bot.sendMessage(
  chatId = "@mychannel".toChatId(),
  text = "Выбери:",
  replyMarkup = InlineKeyboardMarkup(
    inlineKeyboard = listOf(listOf(InlineKeyboardButton(text = "OK", callbackData = "ok")))
  )
)

// файлы
bot.sendPhoto(chatId = 1L.toChatId(), photo = java.io.File("pic.jpg").asInputFile(), caption = "фото")

// Long polling
bot.startPolling { update ->
  println(update)
  update.message?.text?.let { bot.sendMessage(update.message!!.chat.id.toChatId(), "Эхо: $it") }
}

// Webhook
WebhookReceiver(port = 8080, path = "/webhook", secretToken = "s3cr3t", onUpdate = { /* ... */ }).start(wait = true)

// Bot API сервер
val local = AirisBot(token, baseUrl = "http://localhost:8081")
```

## Установка (Gradle)

```kotlin
dependencies {
  implementation("com.airis:airis-kotlin-api:10.3.0")
}
```

## Структура

- `com.airis.api.client.AirisBot`
- `com.airis.api.client.ApiClient` — Ktor CIO (JSON / multipart, 429 retry_after)
- `com.airis.api.types.*`
- `MessageOrTrue`
- `com.airis.api.polling.LongPolling` — long polling & offset + backoff
- `com.airis.api.webhook.WebhookReceiver` — webhook-сервер + `parseWebhookUpdate`
- `com.airis.api.utils.TelegramJson` — общий Json (optimal)

## Версия API

- Bot API **10.3** (24.08.2026)

## Лицензия

[LICENSE](MIT)
