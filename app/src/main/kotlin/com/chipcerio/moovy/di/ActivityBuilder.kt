package com.chipcerio.moovy.di

import com.chipcerio.moovy.features.master_list.MainActivity
import com.chipcerio.moovy.features.master_list.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}