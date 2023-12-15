package com.hyeonbin.translator_android.repository.impl

import com.hyeonbin.translator_android.base.BaseApiState
import com.hyeonbin.translator_android.model.TranslatedLanguage
import com.hyeonbin.translator_android.network.request.RequestTranslatedLanguageData
import com.hyeonbin.translator_android.network.response.ResponseTranslatedLanguageData
import com.hyeonbin.translator_android.network.retrofit.TranslateRetrofitClient
import com.hyeonbin.translator_android.repository.TranslateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import java.lang.Exception
import java.util.Properties

class TranslateRepositoryImpl : TranslateRepository {
    private val properties = Properties()
    private val clientId = properties.getProperty("clientId")
    private val clientSecret = properties.getProperty("clientSecret")

    override suspend fun translateTargetLanguage(
        source: String,
        target: String,
        text: String
    ): Flow<BaseApiState<TranslatedLanguage>> = flow {
        try {
            val response = TranslateRetrofitClient.getTranslateService().translateToTargetLanguage(
                clientId,
                clientSecret,
                RequestTranslatedLanguageData(source, target, text)
            )

            response.run {
                if (isSuccessful) {
                    if (body() != null) {
                        emit(
                            BaseApiState.Success(
                                TranslatedLanguage(
                                    body()?.srcLangType ?: "",
                                    body()?.tarLangType ?: "",
                                    body()?.translatedText ?: ""
                                )
                            )
                        )
                    } else {
                        emit(BaseApiState.Error("서버 응답이 실패 했습니다."))
                    }
                } else {
                    emit(BaseApiState.Error("서버 통신 중 오류가 발생 했습니다."))
                }
            }
        } catch (e: Exception) {
            emit(BaseApiState.Error(e.message ?: ""))
        }
    }.flowOn(Dispatchers.IO)
}