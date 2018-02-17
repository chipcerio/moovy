package com.chipcerio.moovy.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.chipcerio.moovy.data.model.MovieModel

@Database(entities = [MovieModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
}