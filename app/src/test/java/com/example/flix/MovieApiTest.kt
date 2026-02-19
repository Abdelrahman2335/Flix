package com.example.flix

import com.example.flix.home.data.api.MovieApi
import com.example.flix.home.data.model.PopularMoviesResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class MovieApiTest {

    private lateinit var movieApi: MovieApi

    @Before
    fun setup() {
        movieApi = mockk()
    }

    @Test
    fun testGetPopularMovies() = runBlocking {
        val mockResponse = PopularMoviesResponse(
            page = 1,
            results = emptyList(),
            totalPages = 100,
            totalResults = 5000
        )

        coEvery { movieApi.getPopularMovies(page = 1) } returns mockResponse

        val response = movieApi.getPopularMovies(page = 1)

        println("Page: ${response.page}")
        println("Total Pages: ${response.totalPages}")
        println("Total Results: ${response.totalResults}")
        println("Number of movies: ${response.results.size}")
    }
}