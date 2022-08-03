package com.otakenne.eventlist.data.datasource

sealed class Result<out T> {
    data class Success<T>(val content: T): Result<T>()
    data class Error<T>(val throwable: Throwable): Result<T>()
}
