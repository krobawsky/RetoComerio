package com.krobawsky.retocomercio.domain

import com.krobawsky.retocomercio.data.UserRepository

class GetUsersUseCase {

    private val repository = UserRepository()

    suspend operator fun invoke() = repository.getUsers()
}