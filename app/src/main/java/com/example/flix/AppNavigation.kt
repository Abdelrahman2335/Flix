package com.example.flix

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.flix.app.home.presentation.view.screen.HomeScreen
import com.example.flix.app.movie.presentation.view.screen.MovieScreen
import com.example.flix.app.search.presentation.view.screen.SearchScreen
import kotlinx.serialization.Serializable


@Serializable
object Home

@Serializable
object Search

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

                },
                onSearchClick = { navController.navigate(Search) }

            )
        }
        composable<Search> {
            val args = it.toRoute<Search>()

            SearchScreen(
                onMovieClick = { movieId ->
                    navController.navigate(
                        Movie(movieId)
                    )
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