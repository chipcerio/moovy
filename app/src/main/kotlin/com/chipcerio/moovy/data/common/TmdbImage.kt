package com.chipcerio.moovy.data.common

import com.squareup.moshi.Json

class TmdbImage (

        var id: Int = 0,

        @Json(name = "backdrops")
        var backdrops: ArrayList<TmdbImageMeta> = arrayListOf(),

        @Json(name = "posters")
        var posters: ArrayList<TmdbImageMeta> = arrayListOf()

)