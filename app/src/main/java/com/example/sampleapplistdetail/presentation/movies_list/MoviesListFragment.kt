package com.example.sampleapplistdetail.presentation.movies_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collect
import com.example.sampleapplistdetail.databinding.FragmentMoviesListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListFragment : Fragment() {

    private val moviesListAdapter = MoviesListAdapter()


    private val viewModel: MoviesListViewModel by viewModel()


    private var _binding: FragmentMoviesListBinding? = null
    val binding: FragmentMoviesListBinding
        get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getMoviesList()

        binding.rvMoviesList.apply {
            adapter = moviesListAdapter
        }


        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.moviesList.collect {
                if (it.isLoading) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let {itemsList ->

                    if (itemsList.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressMealSearch.visibility = View.GONE
                    moviesListAdapter.setContentList(itemsList.toMutableList())
                }


            }
        }


        moviesListAdapter.itemClickListener {
            findNavController().navigate(
                MoviesListFragmentDirections.actionMoviesListFragmentToMoviesDetailsFragment(
                    it.id!!
                )
            )
        }


    }
}