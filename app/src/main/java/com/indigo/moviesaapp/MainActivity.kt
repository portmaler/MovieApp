package com.indigo.moviesaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.indigo.moviesaapp.ui.navigation.NavGraph
import com.indigo.moviesaapp.ui.theme.MoviesAappTheme
import com.indigo.moviesaapp.utils.AppConstant.API_KEY
import com.indigo.moviesaapp.viewmodel.MovieViewModel

class MainActivity : ComponentActivity() {
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Spacer(modifier = Modifier.height(16.dp))
                        AppContent(viewModel = movieViewModel)
                    }
                }
            }
        }
        movieViewModel.fetchTrendingMovies(API_KEY)
    }
}

@Composable
fun AppContent(viewModel: MovieViewModel, modifier: Modifier = Modifier) {
    NavGraph(viewModel = viewModel)
}
