package com.chipcerio.moovy.api

import com.chipcerio.moovy.api.response.ConfigurationResponse
import com.chipcerio.moovy.api.response.PopularMoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val HOST = "https://api.themoviedb.org/"
        const val API_VERSION = "3/"
    }

    @GET("movie/popular")
    fun getPopular(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Observable<PopularMoviesResponse>

    @GET("configuration")
    fun getConfiguration(
        @Query("api_key") apiKey: String
    ): Observable<ConfigurationResponse>
}