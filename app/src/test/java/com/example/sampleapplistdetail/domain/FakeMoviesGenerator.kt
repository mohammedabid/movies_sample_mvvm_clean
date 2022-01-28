package com.example.sampleapplistdetail.domain

import com.example.sampleapplistdetail.domain.model.MoviesListItem

object FakeMoviesGenerator {

    fun generateDummyMovieList(size: Int): List<MoviesListItem> {
        val movieList = mutableListOf<MoviesListItem>()
        repeat(size) {
            movieList.add(generateMovie())
        }
        return movieList
    }

    fun generateMovie(): MoviesListItem {
        return MoviesListItem(
            id = DataFactory.getRandomInt(),
            title = DataFactory.getRandomString(),
            posterPath = DataFactory.getRandomString(),
            releaseDate = DataFactory.getRandomString()
        )
    }
}