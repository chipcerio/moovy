package com.chipcerio.moovy.features

import com.chipcerio.moovy.data.common.Trending
import com.chipcerio.moovy.data.source.repository.TrendingRepository
import io.reactivex.Observable
import javax.inject.Inject

class TrendingViewModel @Inject
constructor(private val repository: TrendingRepository) {

    fun loadTrendingMovies(): Observable<List<Trending>> = repository.getTrendingMovies()
}