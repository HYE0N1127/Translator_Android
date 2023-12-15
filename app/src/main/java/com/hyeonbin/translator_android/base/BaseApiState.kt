package com.hyeonbin.translator_android.base

sealed class BaseApiState<out T> {
    object Loading : BaseApiState<Nothing>()
    data class Success<out T>(val data: T) : BaseApiState<T>()
    data class Error(val errorMessage: String) : BaseApiState<Nothing>()
}