package com.kronos.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object UserDataStore  {
    private val Context.dataStore by preferencesDataStore(name = "user_prefs")
    private val USERNAME_KEY = stringPreferencesKey("username")

    suspend fun saveUsername(context: Context, username: String) {
        context.dataStore.edit { it[USERNAME_KEY] = username }
    }

    fun getUsername(context: Context): Flow<String?> {
        return context.dataStore.data.map { it[USERNAME_KEY] }
    }
}