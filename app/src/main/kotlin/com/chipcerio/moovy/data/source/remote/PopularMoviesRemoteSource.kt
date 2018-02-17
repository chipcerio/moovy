package com.chipcerio.moovy.data.source.remote

import android.content.SharedPreferences
import com.chipcerio.moovy.BuildConfig
import com.chipcerio.moovy.api.ApiService
import com.chipcerio.moovy.common.Constants.IMG_BASE_URL
import com.chipcerio.moovy.common.Constants.IMG_WIDTH
import com.chipcerio.moovy.data.Movie
import com.chipcerio.moovy.data.poko.Image
import com.chipcerio.moovy.data.source.PopularMoviesSource
import io.reactivex.Observable
import javax.inject.Inject

class PopularMoviesRemoteSource @Inject
constructor(private val apiService: ApiService, private val pref: SharedPreferences) : PopularMoviesSource {

    override fun getPopularMovies(page: Int): Observable<MutableList<Movie>> {
        return apiService.getPopular(BuildConfig.TMDB_API_KEY, page)
            .map {
                it.movies.forEach {
                    it.posterPath = pref.getString(IMG_BASE_URL, "") + pref.getString(IMG_WIDTH, "") + it.posterPath
                }
                it.movies
            }
    }

    override fun loadTmdbConfig(): Observable<Image> {
        return apiService.getConfiguration(BuildConfig.TMDB_API_KEY)
            .map {
                it.image
            }
    }

    override fun saveImageBaseUrlConfig(secureBaseUrl: String) {
    }

    override fun saveImageWidthConfig(imageSizeConfig: String) {
    }

    override fun saveImageUrlConfig(hasConfig: Boolean) {
    }

    override fun saveMovie(movie: Movie) {
    }
}