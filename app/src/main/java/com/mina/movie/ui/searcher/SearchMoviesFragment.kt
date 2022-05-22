package com.mina.movie.ui.searcher

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.mina.movie.R
import com.mina.movie.databinding.FragmentSearchBinding
import com.mina.movie.model.remotemoviesmodel.Movie
import com.mina.movie.ui.adapter.LoaderStateAdapter
import com.mina.movie.ui.adapter.MoviesRecyclerViewAdapter
import com.mina.movie.ui.adapter.OnClickMovie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchMoviesFragment : Fragment(R.layout.fragment_search), OnClickMovie {

    private val viewModel by viewModels<SearchViewModel>()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)
        val adapter = MoviesRecyclerViewAdapter(this)
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = LoaderStateAdapter { adapter.retry() },
                footer = LoaderStateAdapter { adapter.retry() })

            buttonRetry.setOnClickListener { adapter.retry() }
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

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.searchMoviesData.observe(viewLifecycleOwner) {

                adapter.submitData(viewLifecycleOwner.lifecycle, it)
                adapter.notifyDataSetChanged()
            }
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                if (query != null) {
//                    binding.recyclerView.smoothScrollToPosition(0)
//                    viewModel.searchQuery(query)
//                    searchView.clearFocus()
//                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    viewModel.searchQuery(query)
                }
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMovieSelect(movie: Movie) {
        //action to move to details fragment with selected movie
        val action =
            SearchMoviesFragmentDirections.actionMoviesSearchFragmentToDetailsFragment(movie)
        findNavController().navigate(action)
    }
}