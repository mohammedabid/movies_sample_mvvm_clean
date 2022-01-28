package com.example.sampleapplistdetail.repository

import com.example.sampleapplistdetail.data.model.MoviesListResponse
import com.example.sampleapplistdetail.domain.repository.GetMoviesListRepository

class FakeRepository : GetMoviesListRepository {
    override suspend fun getMoviesList(): MoviesListResponse {
        TODO("Not yet implemented")
    }
}