package com.android.mvvm.di.module

import android.content.Context
import com.android.mvvm.data.network.Api
import com.android.mvvm.data.network.ApiService
import com.android.mvvm.data.network.ApiServiceImpl
import com.readystatesoftware.chuck.BuildConfig
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { provideNetworkService(get(), get()) }
    factory { provideApi(get(), get()) }
    factory { provideOkHttpClient(get()) }
    factory { provideMoshi() }

}

fun provideNetworkService(context: Context, api: Api): ApiService {
    return ApiServiceImpl(context, api)
}

fun provideApi(moshi: Moshi, okHttpClient: OkHttpClient): Api {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addConverterFactory(ScalarsConverterFactory.create())
        .client(okHttpClient)
        .build()
    return retrofit.create(Api::class.java)
}

fun provideOkHttpClient(context: Context): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val okHttpClientBuilder = OkHttpClient.Builder()

    if (BuildConfig.DEBUG) {
        okHttpClientBuilder.addInterceptor(ChuckInterceptor(context))
        okHttpClientBuilder.addInterceptor(interceptor)
    }
    return okHttpClientBuilder
        .connectTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .cache(null)
        .build()
}

fun provideMoshi(): Moshi {
    return Moshi.Builder().build()
}