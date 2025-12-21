package com.example.flix

import com.example.flix.app.home.core.api_service.RetrofitInstance
import kotlinx.coroutines.runBlocking
import org.junit.Test


class MovieApiTest {

    @Test
    fun testGetPopularMovies() = runBlocking {
        val response = RetrofitInstance.movieApi.getPopularMovies(page = 1)

        println("Page: ${response.page}")
        println("Total Pages: ${response.totalPages}")
        println("Total Results: ${response.totalResults}")
        println("Number of movies: ${response.results.size}")
    }
}