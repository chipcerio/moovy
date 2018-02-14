package com.chipcerio.moovy.data.common

import com.google.gson.annotations.SerializedName

class TmdbImageMeta(

    @SerializedName("aspect_ratio")
    val aspectRatio: Double,

    @SerializedName("file_path")
    val filePath: String,

    val height: Int,

    @SerializedName("iso_639_1")
    val iso639_1: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int,

    val width: Int
)

