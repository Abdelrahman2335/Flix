package com.example.flix.movie.presentation.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.flix.core.util.HelperMethod
import com.example.flix.movie.data.model.cast.Cast


@Composable
fun CastSection(cast: List<Cast>) {
    val helperMethod = HelperMethod()
    LazyRow {
        items(cast.size) { count ->

            val actor = cast[count]
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(9.dp)
                    .clip(RoundedCornerShape(9.dp))

            ) {
                AsyncImage(
                    modifier = Modifier.size(120.dp),
                    model = helperMethod.getImageUrl(actor.profilePath),
                    contentDescription = actor.name,
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = actor.name,
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}