package com.example.sampleapplistdetail.data.repository

import com.example.sampleapplistdetail.data.model.MoviesDetailResponse
import com.example.sampleapplistdetail.data.remote.NetworkApi
import com.example.sampleapplistdetail.domain.repository.GetMoviesDetailRepository

class GetMoviesDetailsImpl(private val networkApi: NetworkApi) : GetMoviesDetailRepository {
    override suspend fun getMovieDetails(movieId: Int): MoviesDetailResponse {
        return networkApi.getMovieDetails(movieId = movieId)
    }
}