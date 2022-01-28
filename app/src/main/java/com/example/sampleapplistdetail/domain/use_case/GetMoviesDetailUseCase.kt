package com.example.sampleapplistdetail.domain.use_case

import com.example.sampleapplistdetail.common.Resource
import com.example.sampleapplistdetail.data.model.toDomainMovieDetail
import com.example.sampleapplistdetail.data.model.toDomainMoviesListItem
import com.example.sampleapplistdetail.domain.model.MoviesDetail
import com.example.sampleapplistdetail.domain.model.MoviesListItem
import com.example.sampleapplistdetail.domain.repository.GetMoviesDetailRepository
import com.example.sampleapplistdetail.domain.repository.GetMoviesListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetMoviesDetailUseCase constructor(private val repository: GetMoviesDetailRepository) {

    operator fun invoke(movieId : Int): Flow<Resource<MoviesDetail>> = flow {
        try {
            emit(Resource.Loading<MoviesDetail>())
            val data = repository.getMovieDetails(movieId)
            val domainData =
                data.toDomainMovieDetail()
            emit(Resource.Success(data = domainData))
        } catch (e: HttpException) {
            emit(Resource.Error<MoviesDetail>(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<MoviesDetail>(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }
}
