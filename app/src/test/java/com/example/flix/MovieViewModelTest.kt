package com.example.flix

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import app.cash.turbine.test
import com.example.flix.core.Movie
import com.example.flix.movie.data.model.cast.CastResponse
import com.example.flix.movie.data.model.movie.BelongsToCollection
import com.example.flix.movie.data.model.movie.MovieModel
import com.example.flix.movie.data.model.movie_media.MovieMediaResponse
import com.example.flix.movie.data.model.movie_media.Result
import com.example.flix.movie.data.repository.MovieRepository
import com.example.flix.movie.presentation.view_model.MovieViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

class MovieViewModelTest : BaseViewModelTest() {

    private lateinit var repository: MovieRepository
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var viewModel: MovieViewModel

    private val movieId = 1

    @Before
    override fun setup() {
        super.setup()
        repository = mockk()
        savedStateHandle = mockk(relaxed = true)

        // Mock the Navigation extension function statically
        mockkStatic("androidx.navigation.SavedStateHandleKt")
    }

    @After
    override fun tearDown() {
        super.tearDown()
        // Clean up static mocks
        unmockkStatic("androidx.navigation.SavedStateHandleKt")
    }

    @Test
    fun `when ViewModel initializes, should load movie, cast and videos details successfully`() =
        runTest {
            // ARRANGE

            // Mock toRoute<Movie>() to return our test Movie
            every { savedStateHandle.toRoute<Movie>() } returns Movie(movieId = movieId)

            val fakeMovie = MovieModel(
                adult = false,
                backdropPath = "/fakeBackdrop.jpg",
                belongsToCollection = BelongsToCollection(
                    id = 1,
                    name = "Fake Movie Collection",
                    posterPath = "/fakeCollectionPoster.jpg",
                    backdropPath = "/fakeCollectionBackdrop.jpg"
                ),
                budget = 150000000,
                genres = emptyList(),
                homepage = "https://example.com",
                id = 12345,
                imdbId = "tt1234567",
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalTitle = "Fake Movie Title",
                overview = "This is a fake movie overview for testing purposes.",
                popularity = 123.45,
                posterPath = "/fakePoster.jpg",
                productionCompanies = emptyList(),
                productionCountries = emptyList(),
                releaseDate = "2024-01-15",
                revenue = 500000000,
                runtime = 120,
                spokenLanguages = emptyList(),
                status = "Released",
                tagline = "A fake tagline",
                title = "Fake Movie",
                video = false,
                voteAverage = 7.5,
                voteCount = 1000
            )

            val fakeCastResponse = CastResponse(
                cast = emptyList(),
                crew = emptyList(),
                id = 12345
            )

            val fakeMovieMediaResponse = MovieMediaResponse(
                id = 12345,
                results = listOf(
                    Result(
                        id = "abc123",
                        iso31661 = "US",
                        iso6391 = "en",
                        key = "dQw4w9WgXcQ",
                        name = "Official Trailer",
                        official = true,
                        publishedAt = "2024-01-15T10:00:00.000Z",
                        site = "YouTube",
                        size = 1080,
                        type = "Trailer"
                    )
                )
            )

            coEvery { repository.getMovieDetails(movieId) } returns fakeMovie
            coEvery { repository.getCast(movieId) } returns fakeCastResponse
            coEvery { repository.getMovieVideos(movieId) } returns fakeMovieMediaResponse

            // ACT
            viewModel = MovieViewModel(repository, savedStateHandle)
            testDispatcher.scheduler.advanceUntilIdle()

            // ASSERT
            viewModel.uiState.test {
                val state = awaitItem()

                assertFalse(state.isLoading)
                assertEquals(null, state.error)
                assertNotNull(state.movie)
                assertEquals("Fake Movie", state.movie?.title)
                assertEquals(7.5, state.movie?.voteAverage)
                assertEquals(emptyList(), state.cast)
                assertEquals("dQw4w9WgXcQ", state.trailerKey)
            }
        }

    @Test
    fun `when repository fails, should show error state`() = runTest {
        // ARRANGE
        every { savedStateHandle.toRoute<Movie>() } returns Movie(movieId = movieId)

        coEvery { repository.getMovieDetails(movieId) } throws Exception("Network error")
        coEvery { repository.getCast(movieId) } throws Exception("Network error")
        coEvery { repository.getMovieVideos(movieId) } throws Exception("Network error")

        // ACT
        viewModel = MovieViewModel(repository, savedStateHandle)
        testDispatcher.scheduler.advanceUntilIdle()

        // ASSERT
        viewModel.uiState.test {
            val state = awaitItem()

            assertFalse(state.isLoading)
            assertNotNull(state.error)
            assert(state.error!!.contains("Failed to load data") || state.error!!.contains("Network error"))
        }
    }
}