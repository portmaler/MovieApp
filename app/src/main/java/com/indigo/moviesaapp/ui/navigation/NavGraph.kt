package com.indigo.moviesaapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.indigo.moviesaapp.ui.movie.MovieDetailsScreen
import com.indigo.moviesaapp.ui.movie.MovieListScreen
import com.indigo.moviesaapp.viewmodel.MovieViewModel

@Composable
fun NavGraph(viewModel: MovieViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "movies") {
        composable("movies") {
            MovieListScreen(viewModel = viewModel, onMovieSelected = { movieId ->
                navController.navigate("movies/$movieId")
            })
        }
        composable("movies/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toInt() ?: return@composable
            MovieDetailsScreen(viewModel = viewModel, movieId = movieId)
        }
    }
}
