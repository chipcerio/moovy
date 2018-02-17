package com.chipcerio.moovy.data.source

import com.chipcerio.moovy.data.Movie
import com.chipcerio.moovy.data.poko.Image
import io.reactivex.Observable

interface PopularMoviesSource {

    fun getPopularMovies(page: Int): Observable<MutableList<Movie>>

    fun loadTmdbConfig(): Observable<Image>

    fun saveImageBaseUrlConfig(secureBaseUrl: String)

    fun saveImageWidthConfig(imageSizeConfig: String)

    fun saveImageUrlConfig(hasConfig: Boolean)
}