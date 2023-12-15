package com.hyeonbin.translator_android.repository

import com.hyeonbin.translator_android.base.BaseApiState
import com.hyeonbin.translator_android.model.TranslatedLanguage
import com.hyeonbin.translator_android.network.response.ResponseTranslatedLanguageData
import kotlinx.coroutines.flow.Flow

interface TranslateRepository {
    suspend fun translateTargetLanguage(source: String, target: String, text: String): Flow<BaseApiState<TranslatedLanguage>>
}