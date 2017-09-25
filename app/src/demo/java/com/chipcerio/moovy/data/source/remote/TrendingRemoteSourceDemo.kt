package com.chipcerio.moovy.data.source.remote

import com.chipcerio.moovy.api.ApiService
import com.chipcerio.moovy.data.common.Trending
import com.chipcerio.moovy.data.source.TrendingSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.Observable

class TrendingRemoteSourceDemo(apiService: ApiService) : TrendingSource {

    private val trendingMoviesJson = """
        [
          {
            "watchers": 21,
            "movie": {
              "title": "TRON: Legacy",
              "year": 2010,
              "ids": {
                "trakt": 1,
                "slug": "tron-legacy-2010",
                "imdb": "tt1104001",
                "tmdb": 20526
              }
            }
          },
          {
            "watchers": 17,
            "movie": {
              "title": "The Dark Knight",
              "year": 2008,
              "ids": {
                "trakt": 4,
                "slug": "the-dark-knight-2008",
                "imdb": "tt0468569",
                "tmdb": 155
              }
            }
          }
        ]
        """

    override fun getTrendingMovies(): Observable<List<Trending>> {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Trending::class.java)
        val adapter = moshi.adapter<List<Trending>>(type)
        val movieList = adapter.fromJson(trendingMoviesJson)
        return Observable.just(movieList)
    }
}