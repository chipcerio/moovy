package com.chipcerio.moovy.di

import com.chipcerio.moovy.features.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {

    fun inject(activity: MainActivity)
}