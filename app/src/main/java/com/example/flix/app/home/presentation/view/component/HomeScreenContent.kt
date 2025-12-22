package com.example.flix.app.home.presentation.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flix.app.home.presentation.viewmodel.HomeViewModel


//@Preview(showBackground = true, backgroundColor = 0xFF1F1F29)
@Composable
fun HomeScreenContent(modifier: Modifier = Modifier) { // Remove the Modifier
    val homeViewModel: HomeViewModel = viewModel()

    var text by remember { mutableStateOf("") } // Basic State management


    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 17.dp)
    ) {

        item { SearchField(text, { text = it }) }


        // Show shimmer or movies
        item {
            LazyRow {
                // Show shimmer placeholders while loading
                if (homeViewModel.isLoading) {
                    items(5) { // Show 5 shimmer placeholders
                        ShimmerListEffect(
                            modifier = Modifier
                                .width(350.dp)
                                .height(200.dp)
                                .padding(12.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }

                } else if (homeViewModel.movieResponse.isNotEmpty()) {
                    // Show actual movies when loaded
                    items(homeViewModel.movieResponse.size) { count ->
                        val movie = homeViewModel.movieResponse[count]
                        if (movie.posterPath != null) {
                            MovieCard(movie = movie, homeViewModel = homeViewModel)

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
        }

        // Show error message
        if (homeViewModel.error != null) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Error: ${homeViewModel.error}",
                        color = Color.Red
                    )
                }
            }
        }

        item {
            Spacer(Modifier.height(24.dp))
            LazyRow {
                items(homeViewModel.genresResponses.genres.size) { count ->
                    val genre = homeViewModel.genresResponses.genres[count]
                    Genres(
                        genre,
                        isSelected = homeViewModel.selectedGenreId == genre.id,
                        homeViewModel = homeViewModel
                    )
                }
            }
        }

        items(homeViewModel.SearchedGenreResponse.chunked(2).size) { rowIndex ->
            GenreMovieGrid(rowIndex, homeViewModel)
        }

    }


}