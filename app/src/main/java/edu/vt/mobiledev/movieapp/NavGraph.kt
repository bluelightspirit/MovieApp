package edu.vt.mobiledev.movieapp
import androidx.navigation.compose.*
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
fun NavGraph(viewModel: MovieViewModel) {

    val navController = rememberNavController()

    NavHost(navController, startDestination = "list") {

        composable("list") {
            MovieListScreen(navController, viewModel)
        }

        composable("detail/{id}") { backStackEntry ->

            val id = backStackEntry.arguments
                ?.getString("id")
                ?.toIntOrNull()

            if (id != null) {
                MovieDetailScreen(
                    movieId = id,
                    viewModel = viewModel
                )
            }
        }
    }
}