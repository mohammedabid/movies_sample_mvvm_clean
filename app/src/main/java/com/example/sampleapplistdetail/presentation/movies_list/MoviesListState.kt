package com.example.sampleapplistdetail.presentation.movies_list

import com.example.sampleapplistdetail.domain.model.MoviesListItem


data class MoviesListState(
    val isLoading: Boolean = false,
    val data: List<MoviesListItem>? = null,
    val error: String = ""
)