package com.chipcerio.moovy.api

import com.chipcerio.moovy.data.common.TmdbImage
import com.chipcerio.moovy.data.common.Trending
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("movies/trending")
    fun getTrending(): Observable<List<Trending>>

    // tmdbApiKey=62e5027e1267e395ca2b0e7ac38c6f1c
    // movieId=20526
    // https://api.themoviedb.org/3/movie/{movieId}/images?api_key={tmdbApiKey}
    @GET
    fun getTmdbImage(@Url url: String): Observable<TmdbImage>

    /*
     *
     * "secure_base_url": "https://image.tmdb.org/t/p/"
     * h632
     * backdrops.file_path: /poNggE1pzQpezVcTUSPKpacujCP.jpg
     * https://image.tmdb.org/t/p/h632/poNggE1pzQpezVcTUSPKpacujCP.jpg
     */

}