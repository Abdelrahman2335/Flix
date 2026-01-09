package com.example.flix.core.util

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri


private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

class HelperMethod() {

    fun getImageUrl(posterPath: String?): String {
        return if (posterPath != null) {
            "$BASE_IMAGE_URL$posterPath"
        } else {
            "" // Return empty string for null paths
        }
    }

    fun launchTrailer(
        trailerKey: String, context: Context
    ) {
        val youtubeUrl =
            "https://www.youtube.com/watch?v=${trailerKey}"
        val intent = Intent(Intent.ACTION_VIEW, youtubeUrl.toUri())
        context.startActivity(intent)
    }

}