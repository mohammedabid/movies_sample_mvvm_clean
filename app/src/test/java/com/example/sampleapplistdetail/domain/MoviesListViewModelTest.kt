package com.example.sampleapplistdetail.domain

import app.cash.turbine.test
import com.example.sampleapplistdetail.common.Resource
import com.example.sampleapplistdetail.domain.use_case.GetMoviesListUseCase
import com.example.sampleapplistdetail.presentation.movies_list.MoviesListViewModel
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class MoviesListViewModelTest : CoroutineScope by TestCoroutineScope() {

    @get:Rule
    val testCoroutineRule = MainCoroutineScopeRule()

    val useCase = mock<GetMoviesListUseCase>()

    val viewModel = MoviesListViewModel(useCase)

    @Test
    fun `should get movies`() =
        runBlockingTest {
            whenever(useCase()).thenReturn(flow {
                emit(Resource.Success(data = FakeMoviesGenerator.generateDummyMovieList(5)))
            }
            )

            val movies = viewModel.getMoviesList()

            viewModel.moviesList.test {
                System.out.println(awaitItem().toString())
            }
            verify(useCase, times(1)).invoke()
        }

}