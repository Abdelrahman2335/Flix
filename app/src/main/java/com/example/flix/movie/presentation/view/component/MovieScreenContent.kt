package com.example.flix.movie.presentation.view.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.flix.core.util.getImageUrl
import com.example.flix.movie.data.model.movie.MovieModel
import com.example.flix.movie.presentation.event.MovieUiEvent
import com.example.flix.movie.presentation.view_model.MovieViewModel


@Composable
fun MovieScreenContent(
    modifier: Modifier = Modifier, onNavigateBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    val movieViewModel = hiltViewModel<MovieViewModel>()
    val uiState by movieViewModel.uiState.collectAsState()


    val movie: MovieModel? = uiState.movie
    val isLoading = uiState.isLoading
    val error = uiState.error

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
                text = "Error: $error",
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
            Box {

                AsyncImage(
                    model = getImageUrl(movie.posterPath),
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,

                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)


                )
                IconButton(
                    onClick = {
                        if (!uiState.isNavigating) {
                            movieViewModel.onEvent(MovieUiEvent.NavigateBack)
                            onNavigateBack()
                        }
                    },
                    enabled = !uiState.isNavigating,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .statusBarsPadding()
                        .padding(start = 8.dp, top = 8.dp)
                ) {
                    Icon(

                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Navigate back"
                    )
                }
            }


            MovieTags(movie = movie)
            MovieInfoSection(movie.title, movie.overview)
            CastSection(uiState.cast)
        }
    }

}
