package com.chipcerio.moovy.data.source.remote

import com.chipcerio.moovy.BuildConfig
import com.chipcerio.moovy.api.ApiService
import com.chipcerio.moovy.data.Movie
import com.chipcerio.moovy.data.source.PopularMoviesSource
import io.reactivex.Observable
import javax.inject.Inject

class PopularMoviesRemoteSource @Inject
constructor(private val apiService: ApiService) : PopularMoviesSource {

    override fun getPopularMovies(page: Int): Observable<MutableList<Movie>> {
        return apiService.getPopular(BuildConfig.TMDB_API_KEY, 1)
            .map { it.movies }
    }
}