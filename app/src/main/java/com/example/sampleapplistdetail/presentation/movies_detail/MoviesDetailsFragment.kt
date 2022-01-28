package com.example.sampleapplistdetail.presentation.movies_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.flow.collect
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sampleapplistdetail.databinding.FragmentMoviesDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesDetailsFragment : Fragment() {

    private var _binding: FragmentMoviesDetailsBinding? = null
    val binding: FragmentMoviesDetailsBinding
        get() = _binding!!

    private val viewModel: MoviesDetailsViewModel by viewModel()

    private val args: MoviesDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        args.movieId?.let {
            viewModel.getMealDetails(it)
        }


        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.movieDetails.collect {
                if (it.isLoading) {
                }
                if (it.error.isNotBlank()) {
                    Toast.makeText(requireContext(),it.error,Toast.LENGTH_SHORT).show()
                }
                it.data?.let {moviesDetails ->
                    binding.movieDetails = moviesDetails
                }
            }
        }


        binding.detailsBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }


    }

}