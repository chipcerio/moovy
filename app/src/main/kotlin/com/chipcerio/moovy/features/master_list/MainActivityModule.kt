package com.chipcerio.moovy.features.master_list

import android.content.SharedPreferences
import com.chipcerio.moovy.api.ApiService
import com.chipcerio.moovy.data.source.PopularMoviesSource
import com.chipcerio.moovy.data.source.local.AppDatabase
import com.chipcerio.moovy.data.source.local.PopularMoviesLocalSource
import com.chipcerio.moovy.data.source.remote.PopularMoviesRemoteSource
import com.chipcerio.moovy.di.scope.Local
import com.chipcerio.moovy.di.scope.Remote
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @Remote
    fun providesPopularMoviesRemoteSource(apiService: ApiService, pref: SharedPreferences): PopularMoviesSource {
        return PopularMoviesRemoteSource(apiService, pref)
    }

    @Provides
    @Local
    fun providesPopularMoviesLocalSource(db: AppDatabase, pref: SharedPreferences): PopularMoviesSource {
        return PopularMoviesLocalSource(db, pref)
    }
}