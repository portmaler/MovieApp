package com.indigo.moviesaapp.ui.movie

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.indigo.moviesaapp.viewmodel.MovieViewModel

@Composable
fun MovieListScreen(viewModel: MovieViewModel, onMovieSelected: (Int) -> Unit) {
    val movies by viewModel.movies.observeAsState(emptyList())
    LazyColumn {
        items(movies) { movie ->
            MovieItem(movie, onMovieSelected)
        }
    }
}
