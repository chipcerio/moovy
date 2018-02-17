package com.chipcerio.moovy.features.master_list

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chipcerio.moovy.R
import com.chipcerio.moovy.common.loadFromUrl
import com.chipcerio.moovy.data.Movie
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_trending.*
import org.threeten.bp.LocalDate

class MovieAdapter(private val displayableMovies: MutableList<DisplayableMovie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var onMovieSelectedListener: OnMovieSelectedListener? = null

    private var onLoadMoreMoviesListener: OnLoadMoreMoviesListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(displayableMovies[position])
        if (position == displayableMovies.lastIndex) onLoadMoreMoviesListener?.loadMoreMovies()
    }

    override fun getItemCount(): Int = displayableMovies.size

    fun add(position: Int, displayableMovie: DisplayableMovie) {
        displayableMovies.add(displayableMovie)
        notifyItemChanged(position)
    }

    fun setOnMovieSelectedListener(listener: OnMovieSelectedListener) {
        onMovieSelectedListener = listener
    }

    fun setOnLoadMoreMoviesListener(listener: OnLoadMoreMoviesListener) {
        onLoadMoreMoviesListener = listener
    }

    fun filterByDate(date: LocalDate) {
        val newMovies = displayableMovies.filter {
            LocalDate.parse(it.releaseDate).isAfter(date)
        }

        DiffUtil.calculateDiff(MoviesDiffCallback(displayableMovies, newMovies)).apply {
            dispatchUpdatesTo(this@MovieAdapter)
        }
    }

    interface OnMovieSelectedListener {
        fun onMovieSelected(movie: DisplayableMovie)
    }

    interface OnLoadMoreMoviesListener {
        fun loadMoreMovies()
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(displayableMovie: DisplayableMovie) {
            titleView.text = displayableMovie.title
            thumbnailView.loadFromUrl(displayableMovie.imageUrl)
            containerView.setOnClickListener {
                onMovieSelectedListener?.onMovieSelected(displayableMovie)
            }
        }
    }
}