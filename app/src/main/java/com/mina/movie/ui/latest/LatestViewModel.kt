package com.mina.movie.ui.latest

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.*
import com.mina.movie.api.ServiceAPI
import com.mina.movie.pagingsource.PagingSourcesLatestMovies
import com.mina.movie.repository.RepositoryImp

class LatestViewModel @ViewModelInject constructor(
    private val api: ServiceAPI,
    @Assisted state: SavedStateHandle
) : ViewModel() {

    //livedata of Latest movie
    val latestMoviesData = Pager(
    config = PagingConfig(
    pageSize = 10,
    maxSize = 30,
    enablePlaceholders = false
    ),
    pagingSourceFactory = {
         PagingSourcesLatestMovies(api) }
    ).liveData.cachedIn(viewModelScope)

}