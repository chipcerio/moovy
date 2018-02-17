package com.chipcerio.moovy.features.master_list

import com.chipcerio.moovy.data.source.repository.PopularMoviesRepository
import io.reactivex.Observable
import javax.inject.Inject

class PopularMoviesViewModel @Inject
constructor(private val repository: PopularMoviesRepository) {

    fun loadPopularMovies(page: Int): Observable<MutableList<DisplayableMovie>> {
        return repository.getPopularMovies(page)
            .flatMapIterable { it }
            .map {
                DisplayableMovie(
                    id = it.id,
                    title = it.title,
                    imageUrl = it.posterPath,
                    releaseDate = it.releaseDate
                )
            }.toList().toObservable()

    }

    fun loadTmdbConfig(): Observable<Boolean> {
        return repository.loadTmdbConfig()
            .map {
                it.secureBaseUrl.isNotEmpty() && it.posterSizes.isNotEmpty()
            }
    }
}