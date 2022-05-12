package com.mina.movie.ui.adapter

import com.mina.movie.model.remotemoviesmodel.Movie

interface OnListItemClick {
    //on click on movie in recyclerview
    fun onItemSelect(article: Movie)
}