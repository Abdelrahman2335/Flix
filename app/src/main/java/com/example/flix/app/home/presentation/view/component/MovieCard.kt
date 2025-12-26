package com.example.flix.app.home.presentation.view.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.flix.app.home.presentation.viewmodel.HomeViewModel
import com.example.flix.core.util.LanguageMapper
import com.example.flix.core.util.getImageUrl

@Composable
fun MovieCard(count: Int) {
    val homeViewModel = hiltViewModel<HomeViewModel>()

    val movie = homeViewModel.movieResponse[count]


    Box(
        Modifier
            .width(350.dp)
            .height(200.dp)
            .padding(16.dp)
    ) {

        AsyncImage(
            model = getImageUrl(movie.posterPath),
            contentDescription = movie.title,
            modifier = Modifier
                .width(350.dp)
                .height(200.dp)
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
        Row(

            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()
        ) {
            Column {
                Text(
                    text = movie.title,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width(180.dp)
                )
                Text(
                    text = homeViewModel.getGenreNames(movie),
                    color = Color.White,
                    fontSize = 12.sp,
                )
                Text(
                    movie.releaseDate,
                    color = Color.White,
                    fontSize = 12.sp,
                )
            }
            RatingCompose(movie.voteAverage, FontWeight.W400)

        }
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()
        ) {
            Text(
                text = LanguageMapper.getLanguageName(movie.originalLanguage),
                color = Color.White,
                fontSize = 12.sp,
            )
            IconButton(
                colors = IconButtonColors(
                    containerColor = Color.Red,
                    contentColor = Color.Red,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.White,
                ),
                shape = CircleShape,
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    tint = Color.White,
                    contentDescription = "Play"

                )
            }

        }
    }
}