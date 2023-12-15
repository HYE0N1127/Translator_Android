package com.hyeonbin.translator_android.network.request

data class RequestTranslatedLanguageData(
    val source: String,
    val target: String,
    val text: String
)