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
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize


//package edu.vt.mobiledev.movieapp

//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.core.content.FileProvider
//import coil.compose.rememberAsyncImagePainter
//import java.io.File

@Composable
fun MovieDetailScreen(
    movieId: Int,
    viewModel: MovieViewModel
) {
    val movie by viewModel.getMovie(movieId)
        .collectAsState(initial = null)

    val context = LocalContext.current
    val m = movie ?: return

    var showPhoto by remember { mutableStateOf(true) }

    val photoFile = remember(movieId) {
        File(context.filesDir, "movie_${movieId}.jpg")
    }

    val photoUri = remember(photoFile) {
        FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            photoFile
        )
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.TakePicture()
        ) { success ->
            if (success) {
                viewModel.savePhoto(m, photoUri.toString())
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Title
        Text(m.title, style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(12.dp))

        // ✅ ONLY IMAGE SECTION IS SPLIT
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Image(
                painter = rememberAsyncImagePainter(
                    "https://image.tmdb.org/t/p/w500${m.poster_path}"
                ),
                contentDescription = null,
                modifier = Modifier.weight(1f)
            )

            if (m.photoPath != null && showPhoto) {
                Image(
                    painter = rememberAsyncImagePainter(
                        Uri.parse(m.photoPath)
                    ),
                    contentDescription = null,
                    modifier = Modifier.weight(1f)
                )
            } else {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No photo")
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // Info stays normal (NOT split)
        Text("Release: ${m.release_date}")
        Text("Rating: ${m.vote_average}")
        Text("Votes: ${m.vote_count}")

        Spacer(Modifier.height(12.dp))

        Text(m.overview ?: "No overview")

        Spacer(Modifier.height(16.dp))

        // Buttons
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            Button(onClick = {
                viewModel.toggleLike(m.id)
            }) {
                Text("Like")
            }

            Button(onClick = {
                cameraLauncher.launch(photoUri)
            }) {
                Text("Take Picture")
            }

            if (m.photoPath != null) {
                Button(onClick = {
                    showPhoto = !showPhoto
                }) {
                    Text("Toggle Photo")
                }
            }
        }
    }
}


//    Column(modifier = Modifier.padding(16.dp)) {
//
//        Text(movie.title, style = MaterialTheme.typography.titleLarge)
//
//        Image(
//            painter = rememberAsyncImagePainter(
//                "https://image.tmdb.org/t/p/w500${movie.poster_path}"
//            ),
//            contentDescription = null
//        )
//
//        Text("Release: ${movie.release_date}")
//        Text("Rating: ${movie.vote_average}")
//        Text("Votes: ${movie.vote_count}")
//
//        Text(movie.overview ?: "No overview")
//
//        Button(onClick = {
//            viewModel.toggleLike(movie.id)
//        }) {
//            Text("Like / Unlike")
//        }
//    }

