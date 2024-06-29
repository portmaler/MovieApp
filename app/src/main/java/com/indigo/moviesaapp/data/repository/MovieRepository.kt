package com.indigo.moviesaapp.data.repository

import com.indigo.moviesaapp.data.model.Movie
import com.indigo.moviesaapp.data.model.MovieDetail
import com.indigo.moviesaapp.data.model.MovieResponse
import com.indigo.moviesaapp.data.remote.RetrofitInstance


class MovieRepository {
    suspend fun getTrendingMovies(apiKey: String, page: Int): MovieResponse {
        return RetrofitInstance.api.getTrendingMovies(apiKey, page)
    }

    suspend fun getMovieDetails(movieId: Int, apiKey: String): MovieDetail {
        return RetrofitInstance.api.getMovieDetails(movieId, apiKey)
    }
}
