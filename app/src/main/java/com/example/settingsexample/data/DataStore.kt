package com.example.settingsexample.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object DataStoreManager {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    suspend fun saveLanguageSelection(context: Context, language: String) {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.LANGUAGE_SELECTION] = language
        }
    }

    val Context.languageSelection: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[PreferencesKeys.LANGUAGE_SELECTION]
        }

    private object PreferencesKeys {
        val LANGUAGE_SELECTION = stringPreferencesKey("language_selection")
    }
}