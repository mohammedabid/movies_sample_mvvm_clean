package com.example.sampleapplistdetail.presentation.movies_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapplistdetail.common.Resource
import com.example.sampleapplistdetail.domain.model.MoviesDetail
import com.example.sampleapplistdetail.domain.use_case.GetMoviesDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MoviesDetailsViewModel constructor(private var moviesDetailsUseCase: GetMoviesDetailUseCase) :
    ViewModel() {


    private val _movieDetails = MutableStateFlow<MoviesDetailsState>(MoviesDetailsState())
    val movieDetails: StateFlow<MoviesDetailsState> = _movieDetails


    fun getMealDetails(id: Int) {
        moviesDetailsUseCase(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _movieDetails.value = MoviesDetailsState(isLoading = true)
                }
                is Resource.Error -> {
                    _movieDetails.value = MoviesDetailsState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _movieDetails.value = MoviesDetailsState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }


}