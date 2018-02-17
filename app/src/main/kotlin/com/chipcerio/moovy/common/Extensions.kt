package com.chipcerio.moovy.common

import android.widget.ImageView
import com.chipcerio.moovy.config.MoovyGlide

fun ImageView.loadFromUrl(url: String) {
    MoovyGlide.with(this)
        .load(url)
        .into(this)
}