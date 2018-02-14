package com.chipcerio.moovy.features

import com.chipcerio.moovy.api.ApiService
import com.chipcerio.moovy.data.source.TrendingSource
import com.chipcerio.moovy.data.source.remote.TrendingRemoteSource
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun providesTrendingRemoteSource(apiService: ApiService): TrendingSource {
        return TrendingRemoteSource(apiService)
    }
}