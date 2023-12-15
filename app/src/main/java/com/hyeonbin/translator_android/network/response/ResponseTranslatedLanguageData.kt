package com.hyeonbin.translator_android.network.response

data class ResponseTranslatedLanguageData(
    val srcLangType: String,
    val tarLangType: String,
    val translatedText: String
)