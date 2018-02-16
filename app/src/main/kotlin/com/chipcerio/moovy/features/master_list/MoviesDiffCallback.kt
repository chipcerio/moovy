package com.chipcerio.moovy.features.master_list

import android.support.v7.util.DiffUtil
import com.chipcerio.moovy.data.Movie

class MoviesDiffCallback(private val oldMovies: List<Movie>, private val newMovies: List<Movie>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldMovies.size

    override fun getNewListSize(): Int = newMovies.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovies.get(oldItemPosition).id == newMovies.get(newItemPosition).id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovies[oldItemPosition] == newMovies[newItemPosition]
    }
}