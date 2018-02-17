package com.chipcerio.moovy.features.master_list

import android.support.v7.util.DiffUtil

class MoviesDiffCallback (
    private val oldMovies: List<DisplayableMovie>,
    private val newMovies: List<DisplayableMovie>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldMovies.size

    override fun getNewListSize(): Int = newMovies.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovies[oldItemPosition].id == newMovies[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovies[oldItemPosition] == newMovies[newItemPosition]
    }
}