package com.chipcerio.moovy.data.model

import android.annotation.SuppressLint
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize @SuppressLint("ParcelCreator")
@Entity(tableName = "movies")
data class MovieModel (

    @PrimaryKey
    val id: Int,

    val voteCount: Int,

    val video: Boolean,

    val voteAverage: Double,

    val title: String,

    val popularity: Double,

    var posterPath: String,

    val originalLanguage: String,

    val originalTitle: String,

    val backdropPath: String,

    val adult: Boolean,

    val overview: String,

    val releaseDate: String

) : Parcelable