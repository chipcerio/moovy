package com.chipcerio.moovy.api.adapter

import com.chipcerio.moovy.data.Movie
import com.chipcerio.moovy.data.common.Id
import com.chipcerio.moovy.data.common.Trending
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.json.JSONObject

class TrendingAdapter {

    companion object {
        val WATCHERS = "watchers"
        val MOVIE = "movie"
        val TITLE = "title"
        val YEAR = "year"
        val IDs = "ids"
        val TRAKT = "trakt"
        val SLUG = "slug"
        val IMDB = "imdb"
        val TMDB = "tmdb"
    }

    @ToJson
    fun toJson(trending: Trending): String {
        val ids = JSONObject()
        ids.put(TRAKT, trending.movie.ids.trakt)
        ids.put(SLUG, trending.movie.ids.slug)
        ids.put(IMDB, trending.movie.ids.imdb)
        ids.put(TMDB, trending.movie.ids.tmdb)

        val movie = JSONObject()
        movie.put(TITLE, trending.movie.title)
        movie.put(YEAR, trending.movie.year)
        movie.put(IDs, ids)

        val root = JSONObject()
        root.put(WATCHERS, trending.watchers)
        root.put(MOVIE, movie)

        return root.toString()
    }

    @FromJson
    fun fromJson(trending: String): Trending {
        val root = JSONObject(trending)
        val watchers = root.getInt(WATCHERS)

        val movieJson = root.getJSONObject(MOVIE)
        val title = movieJson.getString(TITLE)
        val year = movieJson.getInt(YEAR)
        val idsJson = movieJson.getJSONObject(IDs)
        val trakt = idsJson.getInt(TRAKT)
        val slug = idsJson.getString(SLUG)
        val imdb = idsJson.getString(IMDB)
        val tmdb = idsJson.getInt(TMDB)

        val ids = Id(trakt, slug, imdb, tmdb)
        val movie = Movie(title, year, ids)

        return Trending(watchers, movie)
    }
}