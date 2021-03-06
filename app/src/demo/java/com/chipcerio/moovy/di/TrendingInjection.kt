package com.chipcerio.moovy.di

import com.chipcerio.moovy.api.ApiService
import com.chipcerio.moovy.data.source.TrendingSource
import com.chipcerio.moovy.data.source.remote.TrendingRemoteSourceDemo
import com.chipcerio.moovy.data.source.repository.TrendingRepository

object TrendingInjection {

    fun provides(apiService: ApiService): TrendingSource =
            TrendingRepository(TrendingRemoteSourceDemo(apiService))
}