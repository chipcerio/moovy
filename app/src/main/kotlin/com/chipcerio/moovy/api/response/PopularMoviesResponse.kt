package com.chipcerio.moovy.api.response

import com.chipcerio.moovy.data.Movie
import com.google.gson.annotations.SerializedName

class PopularMoviesResponse (

    val page: Int,

    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("results")
    val movies: MutableList<Movie>

)