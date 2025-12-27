package com.example.flix.app.movie.presentation.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieInfoSection(title: String, overView: String) {

    Column(
        modifier = Modifier.padding(12.dp),

        ) {
        Text(title, fontWeight = FontWeight.W500, fontSize = 25.sp)
        Spacer(Modifier.height(16.dp))
        Text(overView, fontSize = 18.sp)

    }
}
