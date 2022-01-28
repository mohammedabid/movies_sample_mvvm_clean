package com.example.sampleapplistdetail.data.repository

import com.example.sampleapplistdetail.data.model.MoviesListResponse
import com.example.sampleapplistdetail.data.remote.NetworkApi
import com.example.sampleapplistdetail.domain.repository.GetMoviesListRepository

class GetMoviesListImpl(private val networkApi: NetworkApi) : GetMoviesListRepository {
    override suspend fun getMoviesList(): MoviesListResponse {
        return networkApi.getMovieList()
    }
}