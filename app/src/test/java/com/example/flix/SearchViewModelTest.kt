package com.example.flix

import app.cash.turbine.test
import com.example.flix.search.data.model.Result
import com.example.flix.search.data.model.SearchModel
import com.example.flix.search.data.repository.SearchRepository
import com.example.flix.search.presentation.event.SearchUiEvent
import com.example.flix.search.presentation.view_model.SearchViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

@OptIn(ExperimentalCoroutinesApi::class)

class SearchViewModelTest : BaseViewModelTest() {
    private lateinit var repository: SearchRepository
    private lateinit var viewModel: SearchViewModel

    @Before
    override fun setup() {
        super.setup()
        repository = mockk()
    }

    @Test
    fun `when ViewModel initializes, should load movies successfully`() = runTest {

        val fakeSearch = SearchModel(
            page = 1,
            results = listOf(
                Result(
                    adult = false,
                    backdrop_path = "/backdrop.jpg",
                    genre_ids = listOf(28, 12, 878),
                    id = 12345,
                    original_language = "en",
                    original_title = "Test Movie",
                    overview = "This is a test movie overview",
                    popularity = 123.45,
                    poster_path = "/poster.jpg",
                    release_date = "2024-01-01",
                    title = "Test Movie",
                    video = false,
                    vote_average = 7.5,
                    vote_count = 1000
                ),
                Result(
                    adult = false,
                    backdrop_path = "/backdrop.jpg",
                    genre_ids = listOf(28, 12, 878),
                    id = 12346,
                    original_language = "en",
                    original_title = "Test Movie 2",
                    overview = "This is a test movie overview",
                    popularity = 124.45,
                    poster_path = "/poster.jpg",
                    release_date = "2024-01-01",
                    title = "Test Movie",
                    video = false,
                    vote_average = 7.5,
                    vote_count = 1000
                )
            ),
            total_pages = 10,
            total_results = 200
        )

        coEvery { repository.searchMovie("Test") } returns fakeSearch

        viewModel = SearchViewModel(repository)

        viewModel.onEvent(SearchUiEvent.SearchQueryChanged("Test"))

        testDispatcher.scheduler.advanceTimeBy(600)
        testDispatcher.scheduler.runCurrent()
        
        viewModel.uiState.test {
            val state = awaitItem()

            assertFalse(state.isLoading)
            assertEquals(2, state.searchResults.size)
            assertEquals("Test Movie", state.searchResults[0].title)
        }
    }

    @Test
    fun `when repository fails, should show error state`() = runTest {

        coEvery { repository.searchMovie("Test") } throws Exception("Network error")

        viewModel = SearchViewModel(repository)

        viewModel.onEvent(SearchUiEvent.SearchQueryChanged("Test"))

        testDispatcher.scheduler.advanceTimeBy(600)
        testDispatcher.scheduler.runCurrent()


        viewModel.uiState.test {
            val state = awaitItem()

            assertFalse(state.isLoading)
            assertNotNull(state.error)
            assert(state.error.contains("Failed to search movies") || state.error.contains("Network error"))


        }
    }
}