package com.example.stateflow.presentation.base

import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
fun <T> StateFlow<T>.observe(scope: CoroutineScope, observer: Observer<T>){
    scope.launch {
        collect { observer.onChanged(it) }
    }
}