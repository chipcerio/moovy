package com.chipcerio.moovy.di

import com.chipcerio.moovy.BuildConfig
import com.chipcerio.moovy.api.ApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val moshi = Moshi.Builder().build()

        val builder = OkHttpClient.Builder()
        builder.addInterceptor {
            val original = it.request()
            val request = with(original.newBuilder()) {
                header("Content-Type", "application/json")
                header("trakt-api-version", "2")
                header("trakt-api-key", BuildConfig.TRAKT_CLIENT_ID)
                method(original.method(), original.body())
            }.build()
            it.proceed(request)
        }

        val client = builder.build()

        return with(Retrofit.Builder()) {
            baseUrl("https://api.trakt.tv/")
            addConverterFactory(MoshiConverterFactory.create(moshi))
            client(client)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        }.build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}