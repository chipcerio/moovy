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

class MovieAdapter(private val items: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var onMovieSelectedListener: OnMovieSelectedListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(movie: Movie) {
            titleView.text = movie.title
            containerView.setOnClickListener {
                onMovieSelectedListener.onMovieSelected(movie)
            }
        }
    }

    fun setOnMovieSelectedListener(listener: OnMovieSelectedListener) {
        onMovieSelectedListener = listener
    }

    interface OnMovieSelectedListener {
        fun onMovieSelected(movie: Movie)
    }

    fun filterByDate(date: LocalDate) {
        val newItems = items.filter {
            LocalDate.parse(it.release_date).isAfter(date)
        }

        DiffUtil.calculateDiff(MoviesDiffCallback(items, newItems)).apply {
            dispatchUpdatesTo(this@MovieAdapter)
        }
    }
}