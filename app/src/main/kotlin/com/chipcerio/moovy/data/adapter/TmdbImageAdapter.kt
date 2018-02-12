package com.chipcerio.moovy.data.adapter

import com.chipcerio.moovy.data.common.TmdbImage
import com.chipcerio.moovy.data.common.TmdbImageMeta
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.json.JSONArray
import org.json.JSONObject

class TmdbImageAdapter {

    companion object {
        private val ASPECTRIO= "aspect_ratio"
        private val FILEPATH = "file_path"
        private val HEIGHT   = "height"
        private val ISO_6391 = "iso_639_1"
        private val VOTE_AVG = "vote_average"
        private val VOTE_CNT = "vote_count"
        private val WIDTH    = "width"

        private val TMDB_ID  = "id"
        private val BACKDROPS= "backdrops"
        private val POSTERS  = "posters"
    }

    @ToJson fun toJson(tmdb: TmdbImage): String {
        val root = JSONObject()
        root.put(TMDB_ID, tmdb.id)

        val backdrops = JSONArray()
        tmdb.backdrops.forEach {
            val json = JSONObject()
            json.put(ASPECTRIO, it.aspectRatio)
            json.put(FILEPATH, it.filePath)
            json.put(HEIGHT  , it.height)
            json.put(ISO_6391, it.iso639_1)
            json.put(VOTE_AVG, it.voteAverage)
            json.put(VOTE_CNT, it.voteCount)
            json.put(WIDTH   , it.width)
            backdrops.put(json)
        }
        root.put(BACKDROPS, backdrops)

        val posters = JSONArray()
        tmdb.posters.forEach {
            val json = JSONObject()
            json.put(ASPECTRIO, it.aspectRatio)
            json.put(FILEPATH, it.filePath)
            json.put(HEIGHT  , it.height)
            json.put(ISO_6391, it.iso639_1)
            json.put(VOTE_AVG, it.voteAverage)
            json.put(VOTE_CNT, it.voteCount)
            json.put(WIDTH   , it.width)
            posters.put(json)
        }
        root.put(POSTERS, posters)

        return root.toString()
    }

    @FromJson fun fromJson(json: String): TmdbImage {
        val tmdb = TmdbImage()
        val root = JSONObject(json)

        tmdb.id = root.getInt(TMDB_ID)

        val backdrops = arrayListOf<TmdbImageMeta>()
        val backdropsArr = root.getJSONArray(BACKDROPS)
        for (i in 0..(backdropsArr.length() - 1)) {
            val itemJson = backdropsArr.getJSONObject(i)

            val tmdbImageMeta = TmdbImageMeta()
            tmdbImageMeta.aspectRatio = itemJson.getDouble(ASPECTRIO)
            tmdbImageMeta.filePath    = itemJson.getString(FILEPATH)
            tmdbImageMeta.height      = itemJson.getInt(HEIGHT)
            tmdbImageMeta.iso639_1    = itemJson.getString(ISO_6391)
            tmdbImageMeta.voteAverage = itemJson.getDouble(VOTE_AVG)
            tmdbImageMeta.voteCount   = itemJson.getInt(VOTE_CNT)
            tmdbImageMeta.width       = itemJson.getInt(WIDTH)

            backdrops.add(tmdbImageMeta)
        }
        tmdb.backdrops = backdrops

        val posters = arrayListOf<TmdbImageMeta>()
        val postersArr = root.getJSONArray(POSTERS)
        for (i in 0..(postersArr.length() - 1)) {
            val itemJson = postersArr.getJSONObject(i)

            val tmdbImageMeta = TmdbImageMeta()
            tmdbImageMeta.aspectRatio = itemJson.getDouble(ASPECTRIO)
            tmdbImageMeta.filePath    = itemJson.getString(FILEPATH)
            tmdbImageMeta.height      = itemJson.getInt(HEIGHT)
            tmdbImageMeta.iso639_1    = itemJson.getString(ISO_6391)
            tmdbImageMeta.voteAverage = itemJson.getDouble(VOTE_AVG)
            tmdbImageMeta.voteCount   = itemJson.getInt(VOTE_CNT)
            tmdbImageMeta.width       = itemJson.getInt(WIDTH)

            posters.add(tmdbImageMeta)
        }
        tmdb.posters = posters

        return tmdb
    }
}