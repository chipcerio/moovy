package com.chipcerio.moovy.data.source

import com.chipcerio.moovy.data.Movie
import io.reactivex.Observable

interface PopularMoviesSource {

    fun getPopularMovies(page: Int): Observable<MutableList<Movie>>
}