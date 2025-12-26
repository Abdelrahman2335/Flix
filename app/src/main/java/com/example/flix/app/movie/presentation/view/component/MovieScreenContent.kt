package com.example.flix.app.movie.presentation.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.flix.app.home.data.model.Movie
import com.example.flix.app.home.presentation.viewmodel.HomeViewModel


@Composable
fun MovieScreenContent(
    modifier: Modifier = Modifier, movie: Movie, genre: String, homeViewModel: HomeViewModel,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        AsyncImage(
            model = homeViewModel.getImageUrl(movie.posterPath),
            contentDescription = movie.title,
            contentScale = ContentScale.FillWidth,

            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)


        )

        MovieTags(movie = movie, genre = genre)

    }
}