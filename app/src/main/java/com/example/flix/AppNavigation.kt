package com.example.flix

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.flix.app.home.presentation.view.screen.HomeScreen
import com.example.flix.app.movie.presentation.view.screen.MovieScreen
import kotlinx.serialization.Serializable


@Serializable
object Home

@Serializable
data class Movie(val movieId: Int)

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Home
    ) {

        composable<Home> {
            HomeScreen(

                onMovieClick = { movieId ->
                    navController.navigate(
                        Movie(movieId)
                    )
                    Log.d("AppNavigation", "MovieId: $movieId")

                }
            )
        }

        composable<Movie> {
            val args = it.toRoute<Movie>()
            MovieScreen(
//                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}