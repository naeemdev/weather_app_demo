package com.naeemdev.weather.domain

sealed class Resource<out T> {
    data class Success< T>(val data: T) : Resource<T>()
    data class Error<T>(val errorType: ErrorType, val statusCode: Int? = null) : Resource<T>()
}
