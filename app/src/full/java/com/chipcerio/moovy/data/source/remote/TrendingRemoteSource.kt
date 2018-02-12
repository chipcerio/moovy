package com.chipcerio.moovy.data.source.remote

import com.chipcerio.moovy.BuildConfig
import com.chipcerio.moovy.api.ApiService
import com.chipcerio.moovy.data.common.Trending
import com.chipcerio.moovy.data.source.TrendingSource
import io.reactivex.Observable
import timber.log.Timber

class TrendingRemoteSource(private val apiService: ApiService) : TrendingSource {

    override fun getTrendingMovies(): Observable<List<Trending>> {

        return apiService.getTrending()
                .flatMapIterable { it } // emits every item as observable
//                .doOnNext { // do every item
//
//                    Timber.d("movie: ${it.movie.title}, movieId: ${it.movie.ids.tmdb}")
//                    apiService.getTmdbImage("https://api.themoviedb.org/3/movie/${it.movie.ids.tmdb}/images?api_key=${BuildConfig.TMDB_API_KEY}")
//                            .map { it.posters.first().filePath } // /poNggE1pzQpezVcTUSPKpacujCP.jpg
//
//                }
                .toList()
                .toObservable()

//        return apiService.getTrending()
    }

}