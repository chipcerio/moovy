package com.chipcerio.moovy.di

import com.chipcerio.moovy.BuildConfig
import com.chipcerio.moovy.api.ApiService
import com.chipcerio.moovy.api.adapter.TrendingAdapter
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
    fun providesOkHttpClient(): OkHttpClient {
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
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
                .add(TrendingAdapter())
                .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return with(Retrofit.Builder()) {
            baseUrl("https://api.trakt.tv/")
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(MoshiConverterFactory.create())
            client(okHttpClient)
        }.build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}