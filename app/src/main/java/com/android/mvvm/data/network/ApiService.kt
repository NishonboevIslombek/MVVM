package com.android.mvvm.data.network

import androidx.lifecycle.LiveData
import com.android.mvvm.data.network.response.UserResponse
import retrofit2.Response

interface ApiService {

    fun isNetworkConnected(): Boolean

    suspend fun getUsers(): LiveData<Response<List<UserResponse.User>>>

//    suspend fun getUsers(): Response<List<UserResponse.User>>

}