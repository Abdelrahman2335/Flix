package com.example.flix.core.util

object LanguageMapper {
    private val languageMap = mapOf(
        "en" to "English",
        "ko" to "Korean",
        "es" to "Spanish",
        "fr" to "French",
        "de" to "German",
        "ja" to "Japanese",
        "zh" to "Chinese",
        "hi" to "Hindi",
        "it" to "Italian",
        "pt" to "Portuguese",
        "ru" to "Russian",
        "ar" to "Arabic",
        "tr" to "Turkish",
        "th" to "Thai",
        "nl" to "Dutch",
        "sv" to "Swedish",
        "pl" to "Polish",
        "da" to "Danish",
        "fi" to "Finnish",
        "no" to "Norwegian"
    )

    fun getLanguageName(code: String): String {
        return languageMap[code] ?: code.uppercase()
    }
}

