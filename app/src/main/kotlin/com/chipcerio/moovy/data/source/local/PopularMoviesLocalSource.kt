package com.chipcerio.moovy.data.source.local

import com.chipcerio.moovy.data.Movie
import com.chipcerio.moovy.data.source.PopularMoviesSource
import io.reactivex.Observable

class PopularMoviesLocalSource : PopularMoviesSource {

    override fun getPopularMovies(page: Int): Observable<List<Movie>> {
        return Observable.just(emptyList())
    }
}