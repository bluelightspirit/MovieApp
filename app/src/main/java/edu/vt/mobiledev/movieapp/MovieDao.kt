package edu.vt.mobiledev.movieapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Query("SELECT COUNT(*) FROM movies")
    suspend fun getCount(): Int

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovie(id: Int): Flow<Movie>

    @Query("SELECT * FROM movies")
    suspend fun getMoviesOnce(): List<Movie>
}