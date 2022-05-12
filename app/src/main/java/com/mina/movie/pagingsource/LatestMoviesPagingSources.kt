package com.mina.movie.pagingsource

import androidx.paging.PagingSource
import com.mina.movie.model.remotemoviesmodel.Movie
import com.mina.movie.repository.Repository
import retrofit2.HttpException
import java.io.IOException

private const val PAGE_INDEX = 1

class PagingSourcesLatestMovies(private val api: Repository) :
    PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        // the start page number=1
        val position = params.key ?: PAGE_INDEX

        return try {
            val response = api.getLatestMoviesData(position)

            val responseData = mutableListOf<Movie>()
            val movieData = response.body()?.results
            movieData?.let { responseData.addAll((movieData)) }

            LoadResult.Page(
                data = responseData,
                prevKey = if (position == PAGE_INDEX) null else position - 1,
                nextKey = if (movieData?.isEmpty() == true) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}