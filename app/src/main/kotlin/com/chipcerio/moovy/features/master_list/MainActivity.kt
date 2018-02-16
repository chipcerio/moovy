package com.chipcerio.moovy.features.master_list

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.chipcerio.moovy.R
import com.chipcerio.moovy.data.Movie
import com.chipcerio.moovy.features.details.DetailsActivity
import com.chipcerio.moovy.features.master_list.DatePickerFragment.OnDatePickedListener
import com.chipcerio.moovy.features.master_list.MovieAdapter.OnMovieSelectedListener
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_main.*
import org.threeten.bp.LocalDate
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), OnMovieSelectedListener, OnDatePickedListener {

    @Inject
    lateinit var viewModel: PopularMoviesViewModel

    private lateinit var adapter: MovieAdapter

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
        bindFilteredDate()
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    private fun setPopularMovieItems(items: MutableList<Movie>) {
        adapter = MovieAdapter(items)
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
            val datePicker = DatePickerFragment()
            datePicker.show(supportFragmentManager, "date_picker")
            datePicker.setOnDatePickedListener(this)
        }
        return super.onOptionsItemSelected(item)
    }

    private val filteredDateStream = BehaviorSubject.create<String>()

    override fun onDatePicked(date: String) {
        filteredDateStream.onNext(date)
    }

    private fun bindFilteredDate() {
        filteredDateStream.observeOn(AndroidSchedulers.mainThread())
            .map {
                LocalDate.parse(it)
            }
            .doOnError {

            }
            .subscribe({
                adapter.filterByDate(it)
            })
    }
}