package com.chipcerio.moovy.data.common

import com.squareup.moshi.Json

class TmdbImageMeta(

        @Json(name = "aspect_ratio")
        var aspectRatio: Double = 0.0,

        @Json(name = "file_path")
        var filePath: String = "",

        var height: Int = 0,

        @Json(name = "iso_639_1")
        var iso639_1: String = "",

        @Json(name = "vote_average")
        var voteAverage: Double = 0.0,

        @Json(name = "vote_count")
        var voteCount: Int = 0,

        var width: Int = 0
)

