package com.chipcerio.moovy.api

import com.chipcerio.moovy.data.common.Trending
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("movies/trending")
    fun getTrending(): Observable<List<Trending>>

}