package com.hyeonbin.translator_android.model

data class TranslatedLanguage(
    // 번역될 언어의 기본 타입
    val originLanguageType: String,
    // 번역된 언어의 기본 타입
    val targetLanguageType: String,
    // 번역된 텍스트
    val translatedText: String,
)