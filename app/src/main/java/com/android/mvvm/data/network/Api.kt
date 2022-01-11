package com.android.mvvm.data.network

import com.android.mvvm.data.network.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    companion object {
        var BASE_URL = "https://api.github.com/"
    }

    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse.User>>

}