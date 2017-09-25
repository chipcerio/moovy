package com.chipcerio.moovy.data.source.remote

import com.chipcerio.moovy.api.ApiService
import com.chipcerio.moovy.data.common.Trending
import com.chipcerio.moovy.data.source.TrendingSource
import io.reactivex.Observable

class TrendingRemoteSource(private val apiService: ApiService) : TrendingSource {

    override fun getTrendingMovies(): Observable<List<Trending>> = apiService.getTrending()

}