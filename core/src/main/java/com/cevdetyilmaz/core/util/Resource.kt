package com.cevdetyilmaz.core.util

sealed class Resource<out R> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val error: String) : Resource<T>()
    object Loading : Resource<Nothing>()
}