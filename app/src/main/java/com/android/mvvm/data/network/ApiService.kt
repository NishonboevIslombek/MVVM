package com.android.mvvm.data.network

import com.android.mvvm.data.network.response.UserResponse
import retrofit2.Response

interface ApiService {

    fun isNetworkConnected(): Boolean

   suspend fun getUsers(): Response<List<UserResponse.User>>

}