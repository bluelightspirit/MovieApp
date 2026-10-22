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
fun TopBar(viewModel: MovieViewModel) {

    Row {

        Button(onClick = {
            viewModel.isGrid = !viewModel.isGrid
        }) {
            Text("Toggle View")
        }

        Button(onClick = {
            viewModel.showLikedOnly = !viewModel.showLikedOnly
        }) {
            Text("Filter")
        }
    }
}