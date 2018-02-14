package com.chipcerio.moovy.data.source.remote

import com.chipcerio.moovy.api.ApiService
import com.chipcerio.moovy.data.common.Trending
import com.chipcerio.moovy.data.source.TrendingSource
import io.reactivex.Observable
import javax.inject.Inject

class TrendingRemoteSource @Inject
constructor(private val apiService: ApiService) : TrendingSource {

    override fun getTrendingMovies(): Observable<List<Trending>> {
        return apiService.getTrending()
            .flatMapIterable { it }
            .toList()
            .toObservable()
    }
}