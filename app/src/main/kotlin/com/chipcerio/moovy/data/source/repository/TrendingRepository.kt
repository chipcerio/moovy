package com.chipcerio.moovy.data.source.repository

import com.chipcerio.moovy.data.common.Trending
import com.chipcerio.moovy.data.source.TrendingSource
import io.reactivex.Observable
import javax.inject.Inject

class TrendingRepository @Inject
constructor(private val remote: TrendingSource) : TrendingSource {

    override fun getTrendingMovies(): Observable<List<Trending>> = remote.getTrendingMovies()

}