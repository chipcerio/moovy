package com.chipcerio.moovy.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.chipcerio.moovy.data.model.MovieModel

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movieModel: MovieModel)
}