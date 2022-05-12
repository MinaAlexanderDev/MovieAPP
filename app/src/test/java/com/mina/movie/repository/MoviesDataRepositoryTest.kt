package com.mina.movie.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.mina.movie.model.remotemoviesmodel.Movie

class MoviesDataRepositoryTest : MoviesDataRepository {

    private val observableShoppingItems = MutableLiveData<PagingData<Movie>>()

    override fun getLatestDataResults(): LiveData<PagingData<Movie>> {
        return observableShoppingItems
    }

    override fun getSearchDataResults(query: String): LiveData<PagingData<Movie>> {
        return observableShoppingItems
    }

}