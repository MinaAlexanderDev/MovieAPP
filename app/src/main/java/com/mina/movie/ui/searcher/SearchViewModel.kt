package com.mina.movie.ui.searcher

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.*
import com.mina.movie.api.ServiceAPI
import com.mina.movie.model.remotemoviesmodel.Movie
import com.mina.movie.pagingsource.PagingSourcesSearchMovies

class SearchViewModel @ViewModelInject constructor(
    private val api: ServiceAPI,
    @Assisted state: SavedStateHandle
) : ViewModel() {

    // search query
    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    //livedata of search movie
    val searchMoviesData = currentQuery.switchMap { queryString ->
        if (queryString != null&&queryString != "" ){

                searchList =    Pager(
                    config = PagingConfig(
                        pageSize = 10,
                        maxSize = 30,
                        enablePlaceholders = false
                    ),
                    pagingSourceFactory = { PagingSourcesSearchMovies(api,queryString) }
                ).liveData.cachedIn(viewModelScope)


        } else {
            searchList!!.cachedIn(viewModelScope)
        }
        searchList!!.cachedIn(viewModelScope)

    }

    //live data of search movies to sort the last search data from searchMoviesData
    private var searchList: LiveData<PagingData<Movie>>? = searchMoviesData


    fun searchQuery(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val CURRENT_QUERY = ""
        private const val DEFAULT_QUERY = ""
    }
}