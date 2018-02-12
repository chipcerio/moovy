package com.chipcerio.moovy.data.source.remote

import com.chipcerio.moovy.api.ApiService
import com.chipcerio.moovy.data.adapter.TmdbImageAdapter
import com.chipcerio.moovy.data.common.Trending
import com.chipcerio.moovy.data.source.json.Trending.movieId_281338
import com.chipcerio.moovy.data.source.json.Trending.trendingMovies
import com.chipcerio.moovy.data.source.TrendingSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.Observable
import timber.log.Timber

class TrendingRemoteSourceDemo(apiService: ApiService) : TrendingSource {

    override fun getTrendingMovies(): Observable<List<Trending>> {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Trending::class.java)
        val adapter = moshi.adapter<List<Trending>>(type)
        val movieList = adapter.fromJson(trendingMovies)


        Observable.fromIterable(movieList)
                .doOnNext { Timber.d("title: ${it.movie.ids.tmdb}") } // tmdbId
                .flatMap {
                    getTmdbImage(it.movie.ids.tmdb)
                    Observable.just(it)
                }
                .toList()
                .toObservable()
                .subscribe()

        return Observable.just(movieList)
    }

    // https://api.themoviedb.org/3/movie/$tmdbId/images?api_key=$TMDB_API_KEY}")
    private fun getTmdbImage(movieId: Int) {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(TmdbImageAdapter::class.java)
        val tmdbImage = jsonAdapter.fromJson(movieId_281338)
//        return Observable.just(tmdbImage)
    }

}