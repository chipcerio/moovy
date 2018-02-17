package com.chipcerio.moovy.data.source.local

import android.content.SharedPreferences
import com.chipcerio.moovy.common.Constants.HAS_TMDB_CONFIG
import com.chipcerio.moovy.common.Constants.IMG_BASE_URL
import com.chipcerio.moovy.common.Constants.IMG_WIDTH
import com.chipcerio.moovy.common.edit
import com.chipcerio.moovy.data.Movie
import com.chipcerio.moovy.data.model.MovieModel
import com.chipcerio.moovy.data.poko.Image
import com.chipcerio.moovy.data.source.PopularMoviesSource
import io.reactivex.Observable
import javax.inject.Inject

class PopularMoviesLocalSource @Inject
constructor(private val db: AppDatabase, private val pref: SharedPreferences) : PopularMoviesSource {

    override fun getPopularMovies(page: Int): Observable<MutableList<Movie>> {
        return Observable.just(mutableListOf())
    }

    override fun loadTmdbConfig(): Observable<Image> {
        return Observable.empty()
    }

    override fun saveImageBaseUrlConfig(secureBaseUrl: String) {
        pref.edit {
            putString(IMG_BASE_URL, secureBaseUrl)
        }
    }

    override fun saveImageWidthConfig(imageSizeConfig: String) {
        pref.edit {
            putString(IMG_WIDTH, imageSizeConfig)
        }
    }

    override fun saveImageUrlConfig(hasConfig: Boolean) {
        pref.edit {
            putBoolean(HAS_TMDB_CONFIG, hasConfig)
        }
    }

    override fun saveMovie(movie: Movie) {
        val movieModel = MovieModel(
            id = movie.id,
            voteCount = movie.voteCount,
            video = movie.video,
            voteAverage = movie.voteAverage,
            title = movie.title,
            popularity = movie.popularity,
            posterPath = movie.posterPath,
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            backdropPath = movie.backdropPath,
            adult = movie.adult,
            overview = movie.overview,
            releaseDate = movie.releaseDate
        )
        db.moviesDao().save(movieModel)
    }
}