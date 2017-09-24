package com.chipcerio.moovy.features

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.chipcerio.moovy.App
import com.chipcerio.moovy.di.AppComponent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
    }

    fun getComponent(): AppComponent = (application as App).getComponent()

    abstract fun getLayoutResId(): Int
}