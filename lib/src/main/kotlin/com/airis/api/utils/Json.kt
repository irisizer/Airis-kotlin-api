package com.airis.api.utils

import kotlinx.serialization.json.Json

/**
 * Optimized shared Json instance for Telegram Bot API 10.3.
 * - ignoreUnknownKeys=true for forward-compat (new fields in 10.3+ won't break)
 * - explicitNulls=false to omit nulls (smaller payloads, faster)
 * - encodeDefaults=false? No — we need defaults like `type:"photo"`; keep encodeDefaults=true for correctness,
 *   but explicitNulls=false already omits nulls which is the main win.
 * - coerceInputValues=true for robustness, isLenient=false for strictness.
 */
val TelegramJson: Json = Json {
    ignoreUnknownKeys = true
    explicitNulls = false
    encodeDefaults = true
    coerceInputValues = true
    isLenient = false
    prettyPrint = false
    classDiscriminator = "__type__" // unused: all unions use custom serializers, avoid clash with Telegram `type`
}
