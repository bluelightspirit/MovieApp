package edu.vt.mobiledev.movieapp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieViewModel(
    private val repo: MovieRepository,
    private val dataStore: SettingsDataStore
) : ViewModel() {

    var movies by mutableStateOf<List<Movie>>(emptyList())
    var liked by mutableStateOf<Set<String>>(emptySet())

    var sort by mutableStateOf("TITLE")
    var isGrid by mutableStateOf(false)
    var showLikedOnly by mutableStateOf(false)

    init {
        println("MOVIES SIZE = ${movies.size}")
        println("LIKED SIZE = ${liked.size}")
//        loadMovies()

        println("MOVIES SIZE = ${movies.size}")
        println("LIKED SIZE = ${liked.size}")

        viewModelScope.launch {
            repo.initializeMovies()
//            dataStore.settingsFlow.collect { prefs ->
//
//                liked = prefs[SettingsDataStore.LIKED] ?: emptySet()
//
//                showLikedOnly = prefs[SettingsDataStore.FILTER] ?: false
//                isGrid = prefs[SettingsDataStore.VIEW] ?: false
//                sort = prefs[SettingsDataStore.SORT] ?: "TITLE"
//            }
        }
        loadMovies()

        observeSettings()

        println("MOVIES SIZE = ${movies.size}")
        println("LIKED SIZE = ${liked.size}")
    }

    private fun observeSettings() {
        viewModelScope.launch {
            dataStore.settingsFlow.collect { prefs ->
                liked = prefs[SettingsDataStore.LIKED] ?: emptySet()
                sort = prefs[SettingsDataStore.SORT] ?: "TITLE"
                isGrid = prefs[SettingsDataStore.VIEW] ?: false
                showLikedOnly = prefs[SettingsDataStore.FILTER] ?: false
            }
        }
    }

    fun loadMovies() {
//        viewModelScope.launch {
//            movies = repo.getMovies()
//        }
        viewModelScope.launch {

            repo.getMovies().collect {
                movies = it
            }
        }
    }

//    fun getMovie(id: Int): Flow<Movie> {
//        return repo.getMovies().map { list ->
//            list.first { it.id == id }
//        }
//    }
    fun getMovie(id: Int): Flow<Movie> {
        return repo.getMovie(id)
    }

    fun toggleLike(id: Int) {
        viewModelScope.launch {
            dataStore.toggleLike(id)
        }
    }

    fun getDisplayMovies(): List<Movie> {
        var list = movies   // ✅ THIS WORKS because movies is List<Movie>

        if (showLikedOnly) {
            list = list.filter { liked.contains(it.id.toString()) }
        }

        return when (sort) {
            "DATE" -> list.sortedBy { it.release_date }
            "RATING" -> list.sortedByDescending { it.vote_average }
            else -> list.sortedBy { it.title }
        }
    }
    fun refreshMovies() {

        viewModelScope.launch {

            repo.refreshMovies()
        }
    }
    fun savePhoto(movie: Movie, path: String) {

        viewModelScope.launch {

            repo.updateMovie(
                movie.copy(
                    photoPath = path
                )
            )

            loadMovies()
        }
    }


}