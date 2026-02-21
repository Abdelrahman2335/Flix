package com.example.flix

import app.cash.turbine.test
import com.example.flix.core.data.model.Genre
import com.example.flix.home.data.model.GenreResponse
import com.example.flix.home.data.model.PopularMovie
import com.example.flix.home.data.model.PopularMoviesResponse
import com.example.flix.home.data.repository.HomeRepository
import com.example.flix.home.presentation.event.HomeUiEvent
import com.example.flix.home.presentation.view_model.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest : BaseViewModelTest() {

    private lateinit var repository: HomeRepository
    private lateinit var viewModel: HomeViewModel

    @Before
    override fun setup() {
        super.setup()
        repository = mockk()
    }


    @Test
    fun `when ViewModel initializes, should load genres and movies successfully`() = runTest {
        // ARRANGE: Set up fake data for SUCCESS case
        val fakeGenres = GenreResponse(
            genres = listOf(
                Genre(id = 28, name = "Action"),
                Genre(id = 12, name = "Adventure")
            )
        )

        val fakeMovies = PopularMoviesResponse(
            page = 1,
            results = listOf(
                PopularMovie(
                    id = 1,
                    title = "Test Movie",
                    overview = "Test overview",
                    posterPath = "/test.jpg",
                    backdropPath = "/backdrop.jpg",
                    voteAverage = 8.5,
                    releaseDate = "2025-01-01",
                    genreIds = listOf(28)
                )
            ),
            totalPages = 1,
            totalResults = 1
        )

        // Tell the mock what to return
        coEvery { repository.getMovieGenres() } returns fakeGenres
        coEvery { repository.getPopularMovies(1) } returns fakeMovies
        coEvery { repository.discoverMovies(28) } returns fakeMovies

        // ACT: Create the ViewModel (this triggers init{})
        viewModel = HomeViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle() // Wait for coroutines

        // ASSERT: Check the state for SUCCESS
        viewModel.uiState.test {
            val state = awaitItem()

            // Should not be loading anymore
            assertFalse(state.isLoading)

            // Should have NO error (this is a success test)
            assertEquals(null, state.error)

            // Should have loaded genres
            assertEquals(2, state.genreMap.size)
            assertEquals("Action", state.genreMap[28])
            assertEquals("Adventure", state.genreMap[12])

            // Should have loaded movies
            assertEquals(1, state.popularMovies.size)
            assertEquals("Test Movie", state.popularMovies[0].title)
        }
    }

    @Test
    fun `when repository fails, should show error state`() = runTest {
        // ARRANGE: Make the repository throw an error
        coEvery { repository.getMovieGenres() } throws Exception("Network error")
        coEvery { repository.getPopularMovies(1) } returns PopularMoviesResponse(
            page = 1,
            results = emptyList(),
            totalPages = 0,
            totalResults = 0
        )
        coEvery { repository.discoverMovies(28) } returns PopularMoviesResponse(
            page = 1,
            results = emptyList(),
            totalPages = 0,
            totalResults = 0
        )

        // ACT
        viewModel = HomeViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()

        // ASSERT: Check the state for ERROR
        viewModel.uiState.test {
            val state = awaitItem()

            // Should not be loading
            assertFalse(state.isLoading)

            // Should HAVE an error (this is an error test)
            assertNotNull(state.error)
            assertTrue(state.error.contains("Network error"))

            // Should have NO movies (because loading failed)
            assertTrue(state.popularMovies.isEmpty())
        }
    }

    @Test
    fun `when user searches by genre, should update movies list`() = runTest {
        // ARRANGE
        val initialMovies = PopularMoviesResponse(
            page = 1,
            results = listOf(
                PopularMovie(
                    id = 1,
                    title = "Action Movie",
                    genreIds = listOf(28),
                    overview = "",
                    posterPath = "",
                    backdropPath = "",
                    voteAverage = 0.0,
                    releaseDate = ""
                )
            ),
            totalPages = 1,
            totalResults = 1
        )

        val comedyMovies = PopularMoviesResponse(
            page = 1,
            results = listOf(
                PopularMovie(
                    id = 2,
                    title = "Comedy Movie",
                    genreIds = listOf(35),
                    overview = "",
                    posterPath = "",
                    backdropPath = "",
                    voteAverage = 0.0,
                    releaseDate = ""
                )
            ),
            totalPages = 1,
            totalResults = 1
        )

        coEvery { repository.getMovieGenres() } returns GenreResponse(emptyList())
        coEvery { repository.getPopularMovies(1) } returns initialMovies
        coEvery { repository.discoverMovies(28) } returns initialMovies
        coEvery { repository.discoverMovies(35) } returns comedyMovies

        // ACT: Create ViewModel and wait for init
        viewModel = HomeViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()

        // ACT: Search by comedy genre
        viewModel.onEvent(HomeUiEvent.SearchByGenre(35))
        testDispatcher.scheduler.advanceUntilIdle() // Wait for the search to complete

        // ASSERT
        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(1, state.popularMovies.size)
            assertEquals("Action Movie", state.popularMovies[0].title)
        }
    }
}