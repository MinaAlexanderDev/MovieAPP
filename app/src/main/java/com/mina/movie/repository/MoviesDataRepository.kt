package com.mina.movie.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.mina.movie.model.remotemoviesmodel.Movie

interface MoviesDataRepository {
    //data return from paging source as livedata for Latest movie
    fun getLatestDataResults(): LiveData<PagingData<Movie>>?

    //data return from paging source as livedata for search movie
    fun getSearchDataResults(query: String): LiveData<PagingData<Movie>>?

}