package com.chipcerio.moovy.features.master_list

import com.chipcerio.moovy.api.ApiService
import com.chipcerio.moovy.data.source.PopularMoviesSource
import com.chipcerio.moovy.data.source.remote.PopularMoviesRemoteSource
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun providesTrendingRemoteSource(apiService: ApiService): PopularMoviesSource {
        return PopularMoviesRemoteSource(apiService)
    }
}