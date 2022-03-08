package com.krobawsky.retocomercio.data.network

import com.krobawsky.retocomercio.core.RetrofitHelper
import com.krobawsky.retocomercio.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getUsers(): List<UserModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(UserApiClient::class.java).getUsers()
            response.body() ?: emptyList()
        }
    }
}