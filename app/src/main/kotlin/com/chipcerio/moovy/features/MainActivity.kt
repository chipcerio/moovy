package com.chipcerio.moovy.features

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.chipcerio.moovy.R
import com.chipcerio.moovy.data.common.Trending
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: TrendingViewModel

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onStart() {
        super.onStart()
        disposables.add(
            viewModel.loadTrendingMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ setTrendingItems(it) }, { Timber.e(it) })
        )
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    private fun setTrendingItems(items: List<Trending>) {
        recyclerView.adapter = MovieAdapter(items)
    }
}