package com.example.stateflow.data.repository

import com.example.stateflow.data.api.Api
import com.example.stateflow.domain.model.User

class UserRepository(private  val api: Api) {

    suspend fun getUser(id: Int) : User {
        return api.getUser(id)
    }

}