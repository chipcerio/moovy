package com.chipcerio.moovy.api

import com.chipcerio.moovy.api.response.TrendingResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("movies/trending")
    fun getTrending(): Observable<TrendingResponse>
}