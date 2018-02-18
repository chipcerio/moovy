package com.chipcerio.moovy.features.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.chipcerio.moovy.R
import com.chipcerio.moovy.common.loadFromUrl
import com.chipcerio.moovy.features.master_list.DisplayableMovie
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRAS_MOVIE = "extras:movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val displayableMovie = intent.getParcelableExtra<DisplayableMovie>(EXTRAS_MOVIE)

        titleView.text = displayableMovie.title
        releaseDateView.text = displayableMovie.releaseDate
        overview.text = displayableMovie.overview
        posterView.loadFromUrl(displayableMovie.imageUrl)
    }
}