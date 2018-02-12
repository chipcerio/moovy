package com.chipcerio.moovy.data.common

import com.squareup.moshi.Json

class TmdbConfig (

        @Json(name = "images")
        val image: TmdbImageBase = TmdbImageBase(),

        @Json(name = "change_keys")
        val changeKeys: MutableList<String> = mutableListOf()

)