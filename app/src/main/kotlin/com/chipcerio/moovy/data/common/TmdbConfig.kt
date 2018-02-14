package com.chipcerio.moovy.data.common

import com.google.gson.annotations.SerializedName

class TmdbConfig(
    val images: TmdbImageBase,

    @SerializedName("change_keys")
    val changeKeys: List<String>
)