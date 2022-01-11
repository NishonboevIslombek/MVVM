package com.android.mvvm.di.module

import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.mvvm.ui.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    //need to call ViewModel class in appModule when using koin dependency injection.
    viewModel { MainViewModel(get()) }
}