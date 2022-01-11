package com.android.mvvm.di

import com.android.mvvm.di.module.appModule
import com.android.mvvm.di.module.networkModule

val  appComponent = listOf(networkModule, appModule)