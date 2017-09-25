package com.chipcerio.moovy.data.source

import com.chipcerio.moovy.data.common.Trending
import io.reactivex.Observable

interface TrendingSource {

    fun getTrendingMovies(): Observable<List<Trending>>

}