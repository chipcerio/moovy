package com.chipcerio.moovy.features

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.chipcerio.moovy.R
import com.chipcerio.moovy.data.common.Trending
import timber.log.Timber

class MovieAdapter(private val items: List<Trending>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var titleView: TextView = view.findViewById(R.id.titleView)
        private var categoryView: TextView = view.findViewById(R.id.categoryView)

        private var imageView: ImageView = view.findViewById(R.id.thumbnailView)

        fun bind(item: Trending) {
            titleView.text = item.movie.title
//            Timber.d("movie: ${item.movie.title}, movieId: ${item.movie.ids.tmdb}")
        }

    }
}