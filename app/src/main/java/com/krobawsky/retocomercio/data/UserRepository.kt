package com.krobawsky.retocomercio.data

import com.krobawsky.retocomercio.data.model.UserModel
import com.krobawsky.retocomercio.data.model.UserProvider
import com.krobawsky.retocomercio.data.network.UserService

class UserRepository {

    private val api = UserService()

    suspend fun getUsers(): List<UserModel> {
        val response = api.getUsers()
        UserProvider.users = response
        return response
    }
}