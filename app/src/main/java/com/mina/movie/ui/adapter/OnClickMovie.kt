package com.mina.movie.ui.adapter

import com.mina.movie.model.remotemoviesmodel.Movie

interface OnClickMovie {
    //on click on movie in recyclerview
    fun onMovieSelect(movie: Movie)
}