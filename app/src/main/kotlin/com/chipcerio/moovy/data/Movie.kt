package com.chipcerio.moovy.data

import com.chipcerio.moovy.data.common.Id

data class Movie(

    val title: String,

    val year: Int,

    val ids: Id,

    val image: String

)