package com.mina.movie.ui.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mina.movie.model.remotemoviesmodel.Movie
import com.mina.movie.repository.MoviesDataRepository

class MoviesViewModel @ViewModelInject constructor(
    private val repository: MoviesDataRepository,
    @Assisted state: SavedStateHandle
) : ViewModel() {

    // search query
    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    //livedata of search movie
    val searchMoviesData = currentQuery.switchMap { queryString ->
        if (queryString != null) {
            if (queryString != "") {
                searchList = repository.getSearchDataResults(queryString)!!.cachedIn(viewModelScope)
            } else {
                searchList!!.cachedIn(viewModelScope)
            }
        }
        searchList!!.cachedIn(viewModelScope)

    }

    //live data of search movies to sort the last search data from searchMoviesData
    private var searchList: LiveData<PagingData<Movie>>? = searchMoviesData

    //livedata of Latest movie
    val latestMoviesData = repository.getLatestDataResults()!!.cachedIn(viewModelScope)

    fun searchQuery(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val CURRENT_QUERY = ""
        private const val DEFAULT_QUERY = ""
    }
}