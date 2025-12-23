package com.example.flix.app.home.presentation.view.component

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.flix.app.home.data.model.Movie
import com.example.flix.app.home.presentation.viewmodel.HomeViewModel

@Composable
fun GenreMovieGrid(
    movie: Movie,
    homeViewModel: HomeViewModel
) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        AsyncImage(
            model = homeViewModel.getImageUrl(movie.posterPath),
            contentDescription = movie.title,
            modifier = Modifier
                .height(217.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            onError = {
                Log.e(
                    "HomeScreenContent",
                    "Error loading image for ${movie.title}: ${it.result.throwable}"
                )
            },
            onSuccess = {
                Log.d(
                    "HomeScreenContent",
                    "Successfully loaded image for ${movie.title}"
                )
            }
        )

        Text(
            textAlign = TextAlign.Center,
            text = movie.title,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
        )
    }
}