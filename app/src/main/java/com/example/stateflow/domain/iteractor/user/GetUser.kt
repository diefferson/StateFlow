package com.example.stateflow.domain.iteractor.user

import com.example.stateflow.domain.iteractor.base.UseCase
import com.example.stateflow.domain.model.User
import com.example.stateflow.data.repository.UserRepository

class GetUser(private val repository: UserRepository) : UseCase<User, GetUser.Params>(){

    override suspend fun run(params: Params): User {
        return repository.getUser(params.id)
    }

    class Params(val id: Int)

}