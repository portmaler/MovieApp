package com.indigo.moviesaapp.ui.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.indigo.moviesaapp.data.model.Movie
import com.indigo.moviesaapp.utils.AppConstant.IMAGE_URL
import com.indigo.moviesaapp.utils.AppString

@Composable
fun MovieItem(movie: Movie, onMovieSelected: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(150.dp) // Set a fixed height for the card
            .clickable { onMovieSelected(movie.id) },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row {
            Image(
                painter = rememberImagePainter("${IMAGE_URL}/w500${movie.posterPath}"),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
            )
            Column(modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movie.releaseDate,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${AppString.RATING}: ${movie.voteAverage}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
