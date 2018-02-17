package com.chipcerio.moovy.common

import android.content.SharedPreferences
import android.widget.ImageView
import com.chipcerio.moovy.config.MoovyGlide

fun ImageView.loadFromUrl(url: String) {
    MoovyGlide.with(this)
        .load(url)
        .into(this)
}

inline fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    action(editor)
    editor.apply()
}