package com.hyeonbin.translator_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyeonbin.translator_android.base.BaseApiState
import com.hyeonbin.translator_android.model.TranslatedLanguage
import com.hyeonbin.translator_android.repository.TranslateRepository
import com.hyeonbin.translator_android.repository.impl.TranslateRepositoryImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    // Repository 주입
    private val repository: TranslateRepository by lazy {
        TranslateRepositoryImpl()
    }

    // API 통신 결과값
    private val _translateResult: MutableSharedFlow<BaseApiState<TranslatedLanguage>> = MutableSharedFlow()
    val translateResult: SharedFlow<BaseApiState<TranslatedLanguage>> = _translateResult.asSharedFlow()

    fun translateLanguage(originLngType: String, targetLngType: String, text: String) {
        viewModelScope.launch {
            _translateResult.emit(BaseApiState.Loading)
            try {
                repository.translateTargetLanguage(originLngType, targetLngType, text).collect { apiResult ->
                    _translateResult.emit(apiResult)
                }
            } catch (e: Exception) {
                _translateResult.emit(BaseApiState.Error("데이터 로딩 중 오류가 발생하였습니다."))
            }
        }
    }
}