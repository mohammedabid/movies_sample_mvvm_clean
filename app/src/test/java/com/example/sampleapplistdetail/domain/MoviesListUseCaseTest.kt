package com.example.sampleapplistdetail.domain

import app.cash.turbine.test
import com.example.sampleapplistdetail.common.Resource
import com.example.sampleapplistdetail.domain.repository.GetMoviesListRepository
import com.example.sampleapplistdetail.domain.use_case.GetMoviesListUseCase
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import java.io.IOException
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class MoviesListUseCaseTest : CoroutineScope by TestCoroutineScope() {

    @get:Rule
    val testCoroutineRule = MainCoroutineScopeRule()

    val repo = mock<GetMoviesListRepository>()
    val useCase = GetMoviesListUseCase(repo)


    @Test
    fun `should throw Resource Error`() =
        runBlockingTest {
            whenever(repo.getMoviesList()).thenAnswer{ throw IOException() }

            val movies = useCase()

            movies.test {
                Assert.assertTrue(awaitItem() is Resource.Loading)
                Assert.assertTrue(awaitItem() is Resource.Error)
                awaitComplete()
            }
            verify(repo, times(1)).getMoviesList()
        }

}