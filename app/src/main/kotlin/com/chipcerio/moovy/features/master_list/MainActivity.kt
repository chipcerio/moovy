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
import com.chipcerio.moovy.features.master_list.MovieAdapter.OnLoadMoreMoviesListener
import com.chipcerio.moovy.features.master_list.MovieAdapter.OnMovieSelectedListener
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import org.threeten.bp.LocalDate
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), OnMovieSelectedListener, OnLoadMoreMoviesListener, OnDatePickedListener {

    @Inject
    lateinit var viewModel: PopularMoviesViewModel

    private lateinit var adapter: MovieAdapter

    private val disposables = CompositeDisposable()

    private val filteredDateStream = BehaviorSubject.create<String>()

    private val pageNumberStream = PublishSubject.create<Int>()

    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupMovieList()

        disposables.add(
            pageNumberStream.observeOn(Schedulers.io())
                .concatMap {
                    viewModel.loadPopularMovies(page)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    it.map {
                        Timber.d("${it.title}, ${it.release_date}")
                    }
                }
                .subscribe({
                    setPopularMovieItems(it)
                }, { Timber.e(it) })
        )

        pageNumberStream.onNext(page)
    }

    override fun onStart() {
        super.onStart()
        bindFilteredDate()
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    private fun setPopularMovieItems(items: MutableList<Movie>) {
        items.forEach {
            adapter.add(items.indexOf(it), it)
        }
    }

    override fun onMovieSelected(movie: Movie) {
        startActivity(Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.EXTRAS_MOVIE, movie)
        })
    }

    override fun loadMoreMovies() {
        page += 1
        pageNumberStream.onNext(page)
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

    override fun onDatePicked(date: String) {
        filteredDateStream.onNext(date)
    }

    private fun bindFilteredDate() {
        disposables.add(
            filteredDateStream.observeOn(AndroidSchedulers.mainThread())
                .map {
                    LocalDate.parse(it)
                }
                .doOnError {

                }
                .subscribe({
                    adapter.filterByDate(it)
                })
        )
    }

    private fun setupMovieList() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = MovieAdapter(mutableListOf())
        adapter.setOnMovieSelectedListener(this)
        adapter.setOnLoadMoreMoviesListener(this)
        recyclerView.adapter = adapter
    }
}