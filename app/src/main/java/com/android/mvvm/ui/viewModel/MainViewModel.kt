package com.android.mvvm.ui.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.android.mvvm.data.network.ApiService
import com.android.mvvm.data.network.response.UserResponse
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import retrofit2.Response

class MainViewModel(private val api: ApiService) : KoinComponent, ViewModel() {


    //MutableLiveData is Data holder which can be updated later
    //lazy keyword Simply, lazy creates an instance that performs initialization at the first access
    // to the property value, stores the result, and returns the stored value.
    private val users: MutableLiveData<List<UserResponse.User>> by lazy {
        MutableLiveData<List<UserResponse.User>>().also {
            loadUsers()
        }
    }

    //returns with LiveData which MutableDate is child of LiveData
    fun getUsers(): LiveData<List<UserResponse.User>> {
        return users
    }

    //* this is normal using Coroutines in ViewModel class

//    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
//
//    /**
//     * Cancel all coroutines when the ViewModel is cleared
//     */
//    override fun onCleared() {
//        super.onCleared()
//        viewModelJob.cancel()
//    }

//    fun launchDataLoad() {
//        uiScope.launch {
//            sortList() // happens on the background
//            // Modify UI
//        }
//    }

    //this is using coroutines with viewModelScope in ViewModel class. it simplifies boilerplate code
    private fun loadUsers() {
        viewModelScope.launch { // here
            val response = api.getUsers();
            if (response.isSuccessful) {
                users.value = response.body()
            }
        }
    }

}