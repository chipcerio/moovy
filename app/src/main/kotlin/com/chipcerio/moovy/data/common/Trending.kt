package com.chipcerio.moovy.data.common

import com.chipcerio.moovy.data.Movie
import com.squareup.moshi.Json

class Trending(

        @Json(name = "watchers")
        val watchers: Int = 0,

        @Json(name = "movie")
        val movie: Movie = Movie()

)