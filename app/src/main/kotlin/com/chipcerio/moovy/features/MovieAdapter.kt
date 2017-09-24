package com.chipcerio.moovy.features

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.chipcerio.moovy.R

class MovieAdapter(private val anys: List<Any>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(anys.get(position))
    }

    override fun getItemCount(): Int = anys.size

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private var titleView: TextView = view.findViewById(R.id.titleView)

        private var imageView: ImageView = view.findViewById(R.id.imageView)

        fun bind(any: Any) {

        }

    }
}