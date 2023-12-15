package com.hyeonbin.translator_android.network.service

import com.hyeonbin.translator_android.network.request.RequestTranslatedLanguageData
import com.hyeonbin.translator_android.network.response.ResponseTranslatedLanguageData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TranslateService {
    @POST("papago/n2mt")
    suspend fun translateToTargetLanguage(
        @Header("X-Naver-Client-Id") clientID : String,
        @Header("X-Naver-Client-Secret") clientSecretID : String,
        @Body requestTranslatedLanguageData: RequestTranslatedLanguageData
    ): Response<ResponseTranslatedLanguageData>
}