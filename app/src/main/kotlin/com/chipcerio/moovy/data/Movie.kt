package com.chipcerio.moovy.data

import com.chipcerio.moovy.data.common.Id
import com.squareup.moshi.Json

data class Movie (

        @Json(name = "title")
        val title: String = "",

        @Json(name = "year")
        val year: Int = 1900,

        @Json(name = "ids")
        val ids: Id = Id()

)