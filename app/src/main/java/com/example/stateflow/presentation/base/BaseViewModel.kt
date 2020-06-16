package com.example.stateflow.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    private val executionJob: Job by lazy { Job() }

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.IO + executionJob
    }

    override fun onCleared() {
        super.onCleared()
        executionJob.cancel()
    }
}
