package com.chipcerio.moovy

import android.app.Application
import com.chipcerio.moovy.di.AppComponent
import com.chipcerio.moovy.di.AppModule
import com.chipcerio.moovy.di.DaggerAppComponent
import com.chipcerio.moovy.di.NetworkModule
import timber.log.Timber

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}