package edu.vt.mobiledev.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.vt.mobiledev.movieapp.ui.theme.MovieAppTheme
import androidx.compose.material3.MaterialTheme
import androidx.room.Room
import androidx.compose.runtime.remember


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val repo = MovieRepository(RetrofitInstance.api)
        val db = Room.databaseBuilder(
            applicationContext,
            MovieDatabase::class.java,
            "movie_db"
        ).build()

        val repo = MovieRepository(
            RetrofitInstance.api,
            db.movieDao()
        )
        val dataStore = SettingsDataStore(this)
        setContent {

            val viewModel = remember {
                MovieViewModel(repo, dataStore)
            }

            MaterialTheme {
                NavGraph(viewModel)
            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieAppTheme {
        Greeting("Android")
    }
}