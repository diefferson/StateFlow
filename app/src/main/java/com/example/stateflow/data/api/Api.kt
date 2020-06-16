package com.example.stateflow.data.api

import com.example.stateflow.domain.model.User
import kotlinx.coroutines.delay

class Api {

    suspend fun getUser(id: Int) : User {
        delay(800)
        return mockUserList.find { it.id == id }?: throw Exception("Usuário não encontrado")
    }

    companion object{

        private val mockUserList = listOf(
            User(
                id = 1,
                name = "João da Silva",
                email = "joao@dasilva.com"
            ),
            User(
                id = 2,
                name = "Fulano de souza",
                email = "fulano@gmail.com"
            ),
            User(
                id = 3,
                name = "Ciclano bezerra",
                email = "bezerra@teste.com"
            )
        )
    }
}