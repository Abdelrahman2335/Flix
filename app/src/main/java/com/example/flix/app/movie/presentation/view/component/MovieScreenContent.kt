package com.example.flix.app.movie.presentation.view.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.flix.app.movie.data.model.movie.MovieModel
import com.example.flix.app.movie.presentation.view_model.MovieViewModel
import com.example.flix.core.util.getImageUrl


@Composable
fun MovieScreenContent(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    val movieViewModel = hiltViewModel<MovieViewModel>()
    val movie: MovieModel? = movieViewModel.movie
    val isLoading = movieViewModel.isLoading
    val error = movieViewModel.error

    if (isLoading) {
        MovieLoadingScreen()
    }
    if (error != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Error: ${movieViewModel.error}",
                color = Color.Red
            )
        }
    }
    if (movie != null && !isLoading) {

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            AsyncImage(
                model = getImageUrl(movie.posterPath),
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,

                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)


            )

            MovieTags(movie = movie)
            MovieInfoSection(movie.title, movie.overview)
            CastSection(movieViewModel.cast)
        }
    }

}


