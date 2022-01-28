package com.example.sampleapplistdetail.domain.repository;

import com.example.sampleapplistdetail.data.model.MoviesDetailResponse
import com.example.sampleapplistdetail.domain.model.MoviesDetail

interface GetMoviesDetailRepository {

    suspend fun getMovieDetails(movieId: Int) : MoviesDetailResponse

}
