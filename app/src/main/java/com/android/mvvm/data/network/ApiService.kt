package com.android.mvvm.data.network

import com.android.mvvm.data.network.response.UserResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ApiService {

    fun isNetworkConnected(): Boolean

    suspend fun getUsers(): Flow<Response<List<UserResponse.User>>>

}