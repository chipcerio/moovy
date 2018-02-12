package com.chipcerio.moovy.data.common

import com.squareup.moshi.Json

class TmdbImageBase(

        @Json(name = "base_url")
        val baseUrl: String = "",

        @Json(name = "secure_base_url")
        val secureBaseUrl: String = "",

        @Json(name = "backdrop_sizes")
        val backdropSizes: MutableList<String> = mutableListOf(),

        @Json(name = "logo_sizes")
        val logoSizes: MutableList<String> = mutableListOf(),

        @Json(name = "poster_sizes")
        val posterSizes: MutableList<String> = mutableListOf(),

        @Json(name = "profile_sizes")
        val profileSizes: MutableList<String> = mutableListOf(),

        @Json(name = "still_sizes")
        val stillSizes: MutableList<String> = mutableListOf()

)