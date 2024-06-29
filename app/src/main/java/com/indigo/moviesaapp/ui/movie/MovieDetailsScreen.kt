package com.indigo.moviesaapp.ui.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.indigo.moviesaapp.data.model.MovieDetail
import com.indigo.moviesaapp.utils.AppConstant.API_KEY
import com.indigo.moviesaapp.utils.AppConstant.IMAGE_URL
import com.indigo.moviesaapp.utils.AppString
import com.indigo.moviesaapp.viewmodel.MovieViewModel

@Composable
fun MovieDetailsScreen(
    viewModel: MovieViewModel,
    movieId: Int
) {
    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId, API_KEY)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val movieDetail by viewModel.selectedMovie.observeAsState()

        movieDetail?.let { movie ->
            Column(modifier = Modifier.padding(16.dp)) {
                MoviePoster(posterPath = movie.posterPath)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = movie.title,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W700,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                MovieDetails(movie)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = AppString.DESCRIPTION,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = movie.overview)
            }
        } ?: run {
            Text("Loading...", modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
private fun MoviePoster(posterPath: String?) {
    posterPath?.let {
        Image(
            painter = rememberImagePainter("$IMAGE_URL/w500$it"),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(5.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun MovieDetails(movieDetail: MovieDetail) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
    ) {
        MovieDetailItem(
            text = movieDetail.originalLanguage ?: "",
            label = AppString.LANGUAGE
        )
        Divider()
        MovieDetailItem(
            text = movieDetail.voteAverage.toString(),
            label = AppString.RATING
        )
        Divider()
        MovieDetailItem(
            text = "${movieDetail.runtime} min",
            label = AppString.DURATION
        )
        Divider()
        MovieDetailItem(
            text = movieDetail.releaseDate ?: "",
            label = AppString.RELEASE_DATE
        )
    }
}

@Composable
private fun MovieDetailItem(text: String, label: String) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
    }
}

@Composable
private fun Divider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .fillMaxHeight()
            .background(Color.Gray)
    )
}
