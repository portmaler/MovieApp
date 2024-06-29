package com.indigo.moviesaapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.indigo.moviesaapp.data.model.Movie
import com.indigo.moviesaapp.data.model.MovieDetail
import com.indigo.moviesaapp.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val repository = MovieRepository()

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _selectedMovie = MutableLiveData<MovieDetail>()
    val selectedMovie: LiveData<MovieDetail> get() = _selectedMovie

    fun fetchTrendingMovies(apiKey: String, page: Int = 1) {
        viewModelScope.launch {
            try {
                val response = repository.getTrendingMovies(apiKey, page)
                _movies.value = response.results
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }

    fun fetchMovieDetails(movieId: Int, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getMovieDetails(movieId, apiKey)
                _selectedMovie.value = response
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }
}
