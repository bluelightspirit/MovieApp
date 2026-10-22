package edu.vt.mobiledev.movieapp
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.navigation.compose.*
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.grid.items

@Composable
fun MovieListScreen(navController: NavController, viewModel: MovieViewModel) {

    val movies = viewModel.movies

    Column {

        TopBar(viewModel)

        if (movies.isEmpty()) {

            Text(
                text = "No movies to display",
                modifier = Modifier.padding(16.dp)
            )

        } else {

            if (viewModel.isGrid) {

                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(movies) { movie ->
                        MovieGridItem(movie) {
                            navController.navigate("detail/${movie.id}")
                        }
                    }
                }
//                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
//                    items(5) {
//                        Text("TEST ITEM $it")
//                    }
//                }

            } else {
                Text("Liked size = ${viewModel.liked.size}")
                Text("Filter ON = ${viewModel.showLikedOnly}")

                LazyColumn {
                    items(movies) { movie ->
                        MovieRow(movie) {
                            navController.navigate("detail/${movie.id}")
                        }
                    }
                }
            }
        }
    }
}