package com.chipcerio.moovy.data.common

import com.squareup.moshi.Json

class Id (

        @Json(name = "trakt")
        val trakt: Int = 0,

        @Json(name = "slug")
        val slug: String = "",

        @Json(name = "imdb")
        val imdb: String = "",

        @Json(name = "tmdb")
        val tmdb: Int = 0

)