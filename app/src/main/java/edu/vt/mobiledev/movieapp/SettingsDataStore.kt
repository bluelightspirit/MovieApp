package edu.vt.mobiledev.movieapp
import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsDataStore(private val context: Context) {

    companion object {
        val SORT = stringPreferencesKey("sort")
        val VIEW = booleanPreferencesKey("is_grid")
        val FILTER = booleanPreferencesKey("filter_liked")
        val LIKED = stringSetPreferencesKey("liked_movies")
    }

    val settingsFlow = context.dataStore.data

    suspend fun toggleLike(id: Int) {
        context.dataStore.edit { prefs ->

            val current = prefs[LIKED] ?: emptySet()

            prefs[LIKED] =
                if (current.contains(id.toString())) {
                    current - id.toString()
                } else {
                    current + id.toString()
                }
        }
    }
}