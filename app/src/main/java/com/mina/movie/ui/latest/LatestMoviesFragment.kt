package com.mina.movie.ui.latest

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.mina.movie.R
import com.mina.movie.databinding.FragmentLatestBinding
import com.mina.movie.model.remotemoviesmodel.Movie
import com.mina.movie.ui.adapter.LoaderStateAdapter
import com.mina.movie.ui.adapter.MoviesRecyclerViewAdapter
import com.mina.movie.ui.adapter.OnListItemClick
import com.mina.movie.ui.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LatestMoviesFragment : Fragment(R.layout.fragment_latest), OnListItemClick {

    private val viewModel by viewModels<MoviesViewModel>()
    private var _binding: FragmentLatestBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentLatestBinding.bind(view)
        val adapter = MoviesRecyclerViewAdapter(this)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = LoaderStateAdapter { adapter.retry() },
                footer = LoaderStateAdapter { adapter.retry() })

            buttonRetry.setOnClickListener {
                adapter.retry()
            }
        }
        viewModel.latestMoviesData.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        adapter.addLoadStateListener { loadState ->
            binding.apply {

                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }
        }
        setHasOptionsMenu(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelect(movie: Movie) {
        //action to move to details fragment with selected movie
        val action =
            LatestMoviesFragmentDirections.actionTrendingMoviesFragmentToDetailsFragment(movie)

        findNavController().navigate(action)
    }
}