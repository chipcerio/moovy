package com.chipcerio.moovy.di

import com.chipcerio.moovy.api.ApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
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
        return with(Retrofit.Builder()) {
            baseUrl("https://api.trakt.tv/")
            addConverterFactory(MoshiConverterFactory.create(moshi))
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        }.build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}