package com.chipcerio.moovy.di

import com.chipcerio.moovy.api.ApiService
import com.chipcerio.moovy.api.ApiService.Companion.API_VERSION
import com.chipcerio.moovy.api.ApiService.Companion.HOST
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().run {
            setLenient()
            create()
        }
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return with(Retrofit.Builder()) {
            baseUrl(HOST + API_VERSION)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create(gson))
            client(okHttpClient)
        }.build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}