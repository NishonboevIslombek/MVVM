package com.android.mvvm.ui.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.android.mvvm.data.network.ApiService
import com.android.mvvm.data.network.response.UserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import retrofit2.Response
import java.lang.Exception

class MainViewModel(private val api: ApiService) : KoinComponent, ViewModel() {


    private val _uiState = MutableStateFlow(LatestUsers.Success(emptyList()))

    val uiState: StateFlow<LatestUsers> = _uiState

    init {
        viewModelScope.launch {
            api.getUsers()
                .collect {
                    if (it.isSuccessful) {
                        _uiState.value = LatestUsers.Success(it.body()!!)
                    }
                }
        }
    }

}

sealed class LatestUsers {
    data class Success(val users: List<UserResponse.User>) : LatestUsers()
    data class Error(val exception: Exception) : LatestUsers()
}