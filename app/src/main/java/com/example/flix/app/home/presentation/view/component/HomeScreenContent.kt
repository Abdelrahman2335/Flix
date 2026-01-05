package com.example.flix.app.home.presentation.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flix.app.home.presentation.view_model.HomeViewModel
import com.example.flix.core.util.shimmerEffect


//@Preview(showBackground = true, backgroundColor = 0xFF1F1F29)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    onMovieClick: (Int) -> Unit,

    ) {

    val homeViewModel = hiltViewModel<HomeViewModel>()
    val uiState by homeViewModel.uiState.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize(),
    ) {

        // Show shimmer placeholders while loading

        if (uiState.isLoading) {
            item(
                span = { GridItemSpan(2) }
            ) {
                LazyRow {

                    items(5) { // Show 5 shimmer placeholders
                        Box(
                            modifier = Modifier
                                .size(350.dp, 200.dp)
                                .padding(12.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .shimmerEffect(),
                        )
                    }
                }
            }
        } else if (uiState.popularMovies.isNotEmpty()) {

            item(
                span = { GridItemSpan(2) },
                content = {
                    LazyRow {
                        // Show actual movies when loaded
                        items(uiState.popularMovies.size) { count ->
                            val movie = uiState.popularMovies[count]
                            if (movie.posterPath != null) {
                                MovieCard(
                                    count = count,
                                    onMovieClick = onMovieClick
                                )

                            } else {
                                // Placeholder for movies without poster
                                Box(
                                    modifier = Modifier
                                        .width(300.dp)
                                        .height(200.dp)
                                        .padding(16.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color.Gray),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = movie.title, color = Color.White)
                                }
                            }

                        }

                    }
                }
            )


            item(
                span = { GridItemSpan(2) },
                content = {
                    Spacer(Modifier.height(24.dp))
                    LazyRow {
                        items(uiState.genres.genres.size) { count ->
                            val genre = uiState.genres.genres[count]
                            Genres(genre, isSelected = uiState.selectedGenreId == genre.id)
                        }
                    }
                },
            )

            items(uiState.searchedGenreMovies.size) {
                val movie = uiState.searchedGenreMovies[it]
                GenreMovieGrid(
                    movie,
                    onMovieClick = onMovieClick
                )
            }
        }

        // Show error message
        if (uiState.error != null) {
            item(
                span = { GridItemSpan(2) },
                content = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Error: ${uiState.error}",
                            color = Color.Red
                        )
                    }
                },
            )
        }
    }
}

