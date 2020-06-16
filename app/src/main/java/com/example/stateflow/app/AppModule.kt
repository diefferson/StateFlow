package com.example.stateflow.app

import com.example.stateflow.data.api.Api
import com.example.stateflow.data.repository.UserRepository
import com.example.stateflow.domain.iteractor.user.GetUser
import com.example.stateflow.presentation.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val appModule = module {

    single { Api() }

    single { UserRepository(api = get()) }

    factory { GetUser(repository = get()) }

    viewModel { MainViewModel(getUserCase = get()) }
}