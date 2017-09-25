package com.chipcerio.moovy.features

import com.chipcerio.moovy.data.common.Trending
import com.chipcerio.moovy.data.source.TrendingSource
import io.reactivex.Observable

class TrendingViewModel(private val repository: TrendingSource) {

    fun loadTrendingMovies(): Observable<List<Trending>> = repository.getTrendingMovies()

}