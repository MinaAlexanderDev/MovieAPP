package com.mina.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mina.movie.R
import com.mina.movie.databinding.ListItemMoviesBinding
import com.mina.movie.model.remotemoviesmodel.Movie
import com.mina.movie.utilies.Constants

class MoviesRecyclerViewAdapter(private val listener: OnListItemClick) :
    PagingDataAdapter<Movie,
            MoviesRecyclerViewAdapter.ImageViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            ListItemMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ImageViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentMovie = getItem(position)
        if (currentMovie != null) {
            holder.bind(currentMovie)
        }
    }

    inner class ImageViewHolder(private val binding: ListItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemSelect(item)
                    }
                }
            }
        }

        fun bind(movie: Movie) {
            binding.apply {
                tvMovieTitle.text = movie.title
                tvMovieOverview.text = movie.overview
                movie.voteAverage?.let { ratingbarMovie.rating = it / 2 }
                Glide.with(imgMoviePoster)
                    .load(Constants.MOVIE_IMAGE_URL + movie.posterPath)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imgMoviePoster)
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            //check and compare between the id of old Item(movie) and the next item
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == oldItem.id
            }

            //check and compare between the content of old Item(movie) and the next item
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == oldItem.id
            }
        }
    }

}