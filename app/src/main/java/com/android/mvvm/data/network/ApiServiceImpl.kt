package com.android.mvvm.data.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.android.mvvm.data.network.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ApiServiceImpl(private val context: Context, private val api: Api) : ApiService {

    @SuppressLint("ObsoleteSdkInt")
    override fun isNetworkConnected(): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return result
    }

//    override suspend fun getUsers(): Response<List<UserResponse.User>> {
//        return withContext(Dispatchers.IO) {
//            return@withContext api.getUsers()
//        }
//    }

    override suspend fun getUsers(): LiveData<Response<List<UserResponse.User>>> {
        return withContext(Dispatchers.IO) {
            val user : LiveData<Response<List<UserResponse.User>>> = liveData {
                val data = api.getUsers()
                emit(data)
            }
            return@withContext user
        }
    }


}