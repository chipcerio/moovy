package com.chipcerio.moovy.data.source.repository

import com.chipcerio.moovy.data.Movie
import com.chipcerio.moovy.data.source.PopularMoviesSource
import io.reactivex.Observable
import javax.inject.Inject

class PopularMoviesRepository @Inject
constructor(private val remote: PopularMoviesSource) : PopularMoviesSource {

    override fun getPopularMovies(page: Int): Observable<MutableList<Movie>> = remote.getPopularMovies(page)

}