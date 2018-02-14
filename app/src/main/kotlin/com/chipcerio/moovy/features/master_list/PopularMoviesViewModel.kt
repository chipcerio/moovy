package com.chipcerio.moovy.features.master_list

import com.chipcerio.moovy.data.Movie
import com.chipcerio.moovy.data.source.repository.PopularMoviesRepository
import io.reactivex.Observable
import javax.inject.Inject

class PopularMoviesViewModel @Inject
constructor(private val repository: PopularMoviesRepository) {

    fun loadPopularMovies(page: Int): Observable<List<Movie>> = repository.getPopularMovies(page)
}