package edu.vt.mobiledev.movieapp
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun MovieRow(movie: Movie, onClick: () -> Unit) {
    Row(modifier = Modifier.clickable { onClick() }) {

        Image(
            painter = rememberAsyncImagePainter(
                "https://image.tmdb.org/t/p/w200${movie.poster_path}"
            ),
            contentDescription = null
        )

        Column {
            Text(movie.title)
            Text("⭐ ${movie.vote_average}")
        }
    }
}