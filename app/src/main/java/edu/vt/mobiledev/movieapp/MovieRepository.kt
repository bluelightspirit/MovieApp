package edu.vt.mobiledev.movieapp
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val api: MovieApi, private val dao: MovieDao) {

//    suspend fun getMovies(): List<Movie> {
//        val pages = listOf(1, 2, 3)
//
//        val movies = pages.flatMap {
//            api.getNowPlaying(it).results
//        }
//
//        return movies.filter { it.original_language == "en" }
//    }

    fun getMovies(): Flow<List<Movie>> {
        return dao.getMovies()
    }
    fun getMovie(id: Int): Flow<Movie> {
        return dao.getMovie(id)
    }
    suspend fun initializeMovies() {

        if (dao.getCount() == 0) {

            val pages = listOf(1, 2, 3)

            val movies = pages.flatMap {
                api.getNowPlaying(it).results
            }.filter {
                it.original_language == "en"
            }

            dao.insertMovies(movies)
        }
    }
    suspend fun refreshMovies() {

        val oldMovies = dao.getMoviesOnce()

        val oldMap = oldMovies.associateBy { it.id }

        val pages = listOf(1, 2, 3)

        val apiMovies = pages.flatMap {
            api.getNowPlaying(it).results
        }
            .filter { it.original_language == "en" }
            .map { movie ->

                val existing = oldMap[movie.id]

                movie.copy(
                    isFavorite = existing?.isFavorite ?: false,
                    photoPath = existing?.photoPath
                )
            }

        dao.insertMovies(apiMovies)
    }
    suspend fun updateMovie(movie: Movie) {
        dao.updateMovie(movie)
    }
}