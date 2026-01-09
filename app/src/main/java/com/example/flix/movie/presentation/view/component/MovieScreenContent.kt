package com.example.flix.movie.presentation.view.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.flix.core.util.HelperMethod
import com.example.flix.movie.data.model.movie.MovieModel
import com.example.flix.movie.presentation.event.MovieUiEvent
import com.example.flix.movie.presentation.view_model.MovieViewModel


@SuppressLint("UseKtx")
@Composable
fun MovieScreenContent(
    modifier: Modifier = Modifier, onNavigateBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val movieViewModel = hiltViewModel<MovieViewModel>()
    val uiMovieState by movieViewModel.uiState.collectAsState()


    val movie: MovieModel? = uiMovieState.movie
    val isLoading = uiMovieState.isLoading
    val error = uiMovieState.error
    val helperMethod = HelperMethod()


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
                // Movie Poster
                AsyncImage(
                    model = helperMethod.getImageUrl(movie.posterPath),
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                )

                IconButton(
                    onClick = {
                        if (!uiMovieState.isNavigating) {
                            movieViewModel.onEvent(MovieUiEvent.NavigateBack)
                            onNavigateBack()
                        }
                    },
                    enabled = !uiMovieState.isNavigating,
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
                if (uiMovieState.trailerKey.isNotEmpty()) {
                    IconButton(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center),
                        colors = IconButtonColors(
                            containerColor = Color.Red,
                            contentColor = Color.Red,
                            disabledContainerColor = Color.White,
                            disabledContentColor = Color.White,
                        ),

                        shape = CircleShape,
                        onClick = {
                            // Launch YouTube app or browser with the trailer
                            helperMethod.launchTrailer(
                                trailerKey = uiMovieState.trailerKey,
                                context = context
                            )
                        }

                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            tint = Color.White,
                            contentDescription = "Play"

                        )
                    }
                }


            }


            MovieTags(movie = movie)
            MovieInfoSection(movie.title, movie.overview)
            CastSection(uiMovieState.cast)
        }
    }

}
