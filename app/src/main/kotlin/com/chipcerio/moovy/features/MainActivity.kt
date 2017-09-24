package com.chipcerio.moovy.features

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearLayoutManager.HORIZONTAL
import com.chipcerio.moovy.R
import com.chipcerio.moovy.api.ApiService
import com.chipcerio.moovy.di.MovieInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var apiService: ApiService

    private lateinit var viewModel: MovieViewModel

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent().inject(this)

        viewModel = MovieViewModel(MovieInjection.provides(apiService))

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, HORIZONTAL))
    }

    override fun onStart() {
        super.onStart()

    }
}
