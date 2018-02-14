package com.chipcerio.moovy.features.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.chipcerio.moovy.R
import com.chipcerio.moovy.data.Movie

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRAS_MOVIE = "extras:movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val movie = intent.getParcelableExtra<Movie>(EXTRAS_MOVIE)

        Toast.makeText(this, "movie: ${movie.title}", Toast.LENGTH_SHORT).show()
    }
}