package com.chipcerio.moovy.features

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearLayoutManager.HORIZONTAL
import com.chipcerio.moovy.R
import com.chipcerio.moovy.api.ApiService
import com.chipcerio.moovy.data.common.Trending
import com.chipcerio.moovy.di.TrendingInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.recyclerView
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var apiService: ApiService

    private lateinit var viewModel: TrendingViewModel

    private val disposables = CompositeDisposable()

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent().inject(this)

        viewModel = TrendingViewModel(TrendingInjection.provides(apiService))

        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onStart() {
        super.onStart()
        val d = viewModel.loadTrendingMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ setTrendingItems(it) }, { Timber.e(it) })
        disposables.add(d)
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    private fun setTrendingItems(items: List<Trending>) {
        recyclerView.adapter = MovieAdapter(items)
    }

}