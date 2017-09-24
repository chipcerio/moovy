package com.chipcerio.moovy.di

import com.chipcerio.moovy.api.ApiService
import com.chipcerio.moovy.data.source.TrendingSource
import com.chipcerio.moovy.data.source.remote.TrendingRemoteSourceDemo

object MovieInjection {

    fun provides(apiService: ApiService): TrendingSource = TrendingRemoteSourceDemo(apiService)
}