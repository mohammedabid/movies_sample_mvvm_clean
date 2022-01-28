package com.example.sampleapplistdetail.presentation.movies_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapplistdetail.common.Resource
import com.example.sampleapplistdetail.domain.use_case.GetMoviesListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MoviesListViewModel constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase
) : ViewModel() {

    private val _moviesList = MutableStateFlow<MoviesListState>(MoviesListState())
    val moviesList: StateFlow<MoviesListState> = _moviesList


    fun getMoviesList() {
        getMoviesListUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _moviesList.value = MoviesListState(isLoading = true)
                }
                is Resource.Success -> {
                    _moviesList.value = MoviesListState(data = it.data)
                }
                is Resource.Error -> {
                    _moviesList.value = MoviesListState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}