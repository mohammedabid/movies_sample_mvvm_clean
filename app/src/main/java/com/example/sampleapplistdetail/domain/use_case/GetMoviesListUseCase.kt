package com.example.sampleapplistdetail.domain.use_case

import com.example.sampleapplistdetail.common.Resource
import com.example.sampleapplistdetail.data.model.toDomainMoviesListItem
import com.example.sampleapplistdetail.domain.model.MoviesListItem
import com.example.sampleapplistdetail.domain.repository.GetMoviesListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetMoviesListUseCase constructor(private val repository: GetMoviesListRepository) {

    operator fun invoke(): Flow<Resource<List<MoviesListItem>>> = flow {
        try {
            emit(Resource.Loading<List<MoviesListItem>>())
            val data = repository.getMoviesList()
            val domainData =
                if (data.results != null) data.results.map { it -> it!!.toDomainMoviesListItem() } else emptyList()
            emit(Resource.Success(data = domainData))
        } catch (e: HttpException) {
            emit(Resource.Error<List<MoviesListItem>>(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<MoviesListItem>>(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }
}

//Turbine
//RoboElectric
//Mockito