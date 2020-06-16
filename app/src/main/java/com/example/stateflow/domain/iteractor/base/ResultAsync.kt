package com.example.stateflow.domain.iteractor.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultAsync<T> private constructor(
    scope: CoroutineScope, action: suspend () -> T) {

    var onSuccess : (T) -> Unit = {}
    var onError : (e: Throwable) -> Unit = {}
    var onStatusChange : (status: AsyncStatus<T>) -> Unit = {}

    companion object {
        fun <T> with( scope: CoroutineScope, action: suspend () -> T) : ResultAsync<T> {
            return  ResultAsync(scope, action)
        }
    }

    init {
        scope.launch {
            withContext(Dispatchers.Main) { onStatusChange(AsyncStatus.Loading) }
            try {
                val result = action()
                withContext(Dispatchers.Main){
                    onStatusChange(AsyncStatus.Success(result))
                    onSuccess(result)
                }
            }catch (e:Throwable){
                withContext(Dispatchers.Main){
                    onStatusChange(AsyncStatus.Error(e))
                    onError(e)
                }
            }
        }
    }
}

fun <T> ResultAsync<T>.onFailure(action: (exception: Throwable) -> Unit): ResultAsync<T> {
    this.onError =  action
    return this
}

fun <T> ResultAsync<T>.onSuccess(action: (value: T) -> Unit): ResultAsync<T> {
    this.onSuccess = action
    return this
}

fun <T> ResultAsync<T>.listen(action: (AsyncStatus<T>) -> Unit): ResultAsync<T> {
    this.onStatusChange = action
    return this
}

fun <T> CoroutineScope.asyncCatching( action: suspend () -> T): ResultAsync<T> {
    return ResultAsync.with( this,action)
}





