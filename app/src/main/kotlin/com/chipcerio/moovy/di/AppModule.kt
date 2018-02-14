package com.chipcerio.moovy.di

import android.content.Context
import com.chipcerio.moovy.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesApplicationContext(app: App): Context = app.applicationContext
}