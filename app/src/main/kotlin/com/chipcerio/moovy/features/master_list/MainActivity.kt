package com.chipcerio.moovy.features.master_list

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.chipcerio.moovy.R
import com.chipcerio.moovy.data.Movie
import com.chipcerio.moovy.features.details.DetailsActivity
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), MovieAdapter.OnMovieSelectedListener {

    @Inject
    lateinit var viewModel: PopularMoviesViewModel

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onStart() {
        super.onStart()
        disposables.add(
            viewModel.loadPopularMovies(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    it.map {
                        Timber.d("release_date: ${it.release_date}")
                    }
                }
                .subscribe({
                    setPopularMovieItems(it)
                }, { Timber.e(it) })
        )
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    private fun setPopularMovieItems(items: List<Movie>) {
        val adapter = MovieAdapter(items)
        adapter.setOnMovieSelectedListener(this)
        recyclerView.adapter = adapter
    }

    override fun onMovieSelected(movie: Movie) {
        startActivity(Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.EXTRAS_MOVIE, movie)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_filter) {
            DatePickerFragment().show(supportFragmentManager, "date_picker")
        }
        return super.onOptionsItemSelected(item)
    }
}