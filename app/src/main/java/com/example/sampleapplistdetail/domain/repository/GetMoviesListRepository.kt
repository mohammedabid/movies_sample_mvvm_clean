package com.example.sampleapplistdetail.domain.repository

import com.example.sampleapplistdetail.data.model.MoviesDetailResponse
import com.example.sampleapplistdetail.data.model.MoviesListResponse
import com.example.sampleapplistdetail.domain.model.MoviesDetail

interface GetMoviesListRepository {

   suspend fun getMoviesList(): MoviesListResponse

}