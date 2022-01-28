package com.example.sampleapplistdetail.presentation.movies_detail

import com.example.sampleapplistdetail.domain.model.MoviesDetail
import com.example.sampleapplistdetail.domain.model.MoviesListItem

data class MoviesDetailsState(
    val isLoading: Boolean = false,
    val data: MoviesDetail? = null,
    val error: String = ""
)