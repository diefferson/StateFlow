package com.example.stateflow.presentation

import com.example.stateflow.domain.iteractor.base.AsyncStatus
import com.example.stateflow.domain.iteractor.base.listen
import com.example.stateflow.domain.iteractor.user.GetUser
import com.example.stateflow.domain.model.User
import com.example.stateflow.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

@ExperimentalCoroutinesApi
class MainViewModel(private val getUserCase: GetUser) : BaseViewModel() {

    val userStateFlow = MutableStateFlow<AsyncStatus<User>>(AsyncStatus.Idle)

    fun getUser(id:Int) {
        getUserCase(this, GetUser.Params(id) ).listen {
            userStateFlow.value = it
        }
    }
}