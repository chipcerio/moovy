package com.chipcerio.moovy.features.master_list

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chipcerio.moovy.R
import com.chipcerio.moovy.data.Movie
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_trending.*
import org.threeten.bp.LocalDate

class MovieAdapter(private val movies: MutableList<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var onMovieSelectedListener: OnMovieSelectedListener

    private lateinit var onLoadMoreMoviesListener: OnLoadMoreMoviesListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
        if (position == movies.lastIndex) onLoadMoreMoviesListener.loadMoreMovies()
    }

    override fun getItemCount(): Int = movies.size

    fun add(position: Int, movie: Movie) {
        movies.add(movie)
        notifyItemChanged(position)
    }

    fun setOnMovieSelectedListener(listener: OnMovieSelectedListener) {
        onMovieSelectedListener = listener
    }

    fun setOnLoadMoreMoviesListener(listener: OnLoadMoreMoviesListener) {
        onLoadMoreMoviesListener = listener
    }

    fun filterByDate(date: LocalDate) {
        val newMovies = movies.filter {
            LocalDate.parse(it.release_date).isAfter(date)
        }

        DiffUtil.calculateDiff(MoviesDiffCallback(movies, newMovies)).apply {
            dispatchUpdatesTo(this@MovieAdapter)
        }
    }

    interface OnMovieSelectedListener {
        fun onMovieSelected(movie: Movie)
    }

    interface OnLoadMoreMoviesListener {
        fun loadMoreMovies()
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(movie: Movie) {
            titleView.text = movie.title
            containerView.setOnClickListener {
                onMovieSelectedListener.onMovieSelected(movie)
            }
        }
    }
}