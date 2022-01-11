package com.android.mvvm

import android.app.Application
import com.android.mvvm.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MVVMApp : Application() {

    override fun onCreate() {
        super.onCreate()

        //starting koin dependency injection
        startKoin {
            androidContext(this@MVVMApp)
            modules(appComponent)
        }

    }
}