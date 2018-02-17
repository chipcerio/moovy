package com.chipcerio.moovy.data.source.repository

import com.chipcerio.moovy.data.Movie
import com.chipcerio.moovy.data.poko.Image
import com.chipcerio.moovy.data.source.PopularMoviesSource
import com.chipcerio.moovy.di.scope.Local
import com.chipcerio.moovy.di.scope.Remote
import io.reactivex.Observable
import javax.inject.Inject

class PopularMoviesRepository @Inject
constructor(
    @Remote private val remote: PopularMoviesSource,
    @Local private val local: PopularMoviesSource) : PopularMoviesSource {

    override fun getPopularMovies(page: Int): Observable<MutableList<Movie>> {
        return remote.getPopularMovies(page)
            .flatMapIterable { it }
            .doOnNext {
                saveMovie(it)
            }
            .toList().toObservable()
    }

    override fun loadTmdbConfig(): Observable<Image> {
        return remote.loadTmdbConfig()
            .doOnNext {
                saveImageBaseUrlConfig(it.secureBaseUrl)
            }
            .doOnNext {
                val imageSizeConfig = when {
                    it.posterSizes.contains("w500") -> "w500"
                    it.posterSizes.contains("w780") -> "w780"
                    it.posterSizes.contains("original") -> "original"
                    else -> it.posterSizes.last()
                }
                saveImageWidthConfig(imageSizeConfig)
            }
            .doOnNext {
                saveImageUrlConfig(true)
            }
    }

    override fun saveImageBaseUrlConfig(secureBaseUrl: String) {
        local.saveImageBaseUrlConfig(secureBaseUrl)
    }

    override fun saveImageWidthConfig(imageSizeConfig: String) {
        local.saveImageWidthConfig(imageSizeConfig)
    }

    override fun saveImageUrlConfig(hasConfig: Boolean) {
        local.saveImageUrlConfig(hasConfig)
    }

    override fun saveMovie(movie: Movie) {
        local.saveMovie(movie)
    }
}