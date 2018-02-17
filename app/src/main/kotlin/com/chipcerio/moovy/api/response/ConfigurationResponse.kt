package com.chipcerio.moovy.api.response

import com.chipcerio.moovy.data.poko.Image
import com.google.gson.annotations.SerializedName

class ConfigurationResponse (

    @SerializedName("images")
    val image: Image,

    val changeKeys: List<String>
)