package com.example.stateflow.domain.iteractor.base

sealed class AsyncStatus<out T> {

    object Idle : AsyncStatus<Nothing>()

    object Loading : AsyncStatus<Nothing>()

    data class Success<T>(val data: T) : AsyncStatus<T>()

    data class Error(val error: Throwable, var consumed: Boolean = false) : AsyncStatus<Nothing>()
}

