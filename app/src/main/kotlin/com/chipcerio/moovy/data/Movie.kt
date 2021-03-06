package com.chipcerio.moovy.data

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Movie (

    val id: Int,

    @SerializedName("vote_count")
    val voteCount: Int,

    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Double,

    val title: String,

    val popularity: Double,

    @SerializedName("poster_path")
    var posterPath: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("genre_ids")
    val genreIds: List<Int>,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    val adult: Boolean,

    val overview: String,

    @SerializedName("release_date")
    val releaseDate: String

) : Parcelable