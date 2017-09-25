package com.chipcerio.moovy.data.source.local

import com.chipcerio.moovy.api.response.TrendingResponse
import com.chipcerio.moovy.data.common.Trending
import com.chipcerio.moovy.data.source.TrendingSource
import io.reactivex.Observable

class TrendingLocalSourceDemo : TrendingSource {

    override fun getTrendingMovies(): Observable<List<Trending>> {
        return Observable.just(emptyList())
    }
}