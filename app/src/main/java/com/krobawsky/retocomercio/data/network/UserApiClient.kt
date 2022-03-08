package com.krobawsky.retocomercio.data.network

import com.krobawsky.retocomercio.data.model.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface UserApiClient {
    @GET("/users")
    suspend fun getUsers(): Response<List<UserModel>>
}