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
fun MovieGridItem(movie: Movie, onClick: () -> Unit) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                "https://image.tmdb.org/t/p/w500${movie.poster_path}"
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        Text(movie.title)
    }
}