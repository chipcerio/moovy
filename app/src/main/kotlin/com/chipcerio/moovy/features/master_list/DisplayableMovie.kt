package com.chipcerio.moovy.features.master_list

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
class DisplayableMovie (

    val id: Int,

    val title: String,

    val imageUrl: String,

    val releaseDate: String,

    val overview: String

) : Parcelable