package com.example.sampleapplistdetail.data.remote

import com.example.sampleapplistdetail.common.Constants
import com.example.sampleapplistdetail.data.model.MoviesDetailResponse
import com.example.sampleapplistdetail.data.model.MoviesListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {

    @GET("discover/movie")
    suspend fun getMovieList(@Query("api_key") apiKey: String = Constants.ServiceConstants.API_KEY) : MoviesListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId : Int, @Query("api_key") apiKey: String = Constants.ServiceConstants.API_KEY) : MoviesDetailResponse

}