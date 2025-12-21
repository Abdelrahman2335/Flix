package com.example.flix.app.home.presentation.view.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.flix.app.home.presentation.viewmodel.HomeViewModel


@Composable
fun HomeScreenContent(modifier: Modifier) {
    val homeViewModel: HomeViewModel = viewModel()

    var text by remember { mutableStateOf("") } // Basic State management

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 17.dp)
    ) {

        item { SearchField(text, { text = it }) }

        // Show loading indicator
        if (homeViewModel.isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
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

        // Show movies only if we have data
        if (homeViewModel.movies.isNotEmpty()) {
            item {
                LazyRow {
                    items(homeViewModel.movies.size) { count ->
                        val movie = homeViewModel.movies[count]
                        Box() {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                                contentDescription = "Movie Picture",
                                modifier = Modifier
                                    .width(300.dp)
                                    .height(200.dp)
                                    .padding(16.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop,

                                )
                        }
                    }
                }
            }
        }


    }
}