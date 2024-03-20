package com.bibek.taskmanager.data.app_datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bibek.taskmanager.domain.repository.AppDataStore
import com.bibek.taskmanager.utils.Constants.ACCESS_TOKEN
import com.bibek.taskmanager.utils.Constants.EMAIL
import com.bibek.taskmanager.utils.Constants.PASSWORD_KEY
import com.bibek.taskmanager.utils.Constants.PREFERENCES_NAME
import com.bibek.taskmanager.utils.Constants.USER_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


/**
Author: Bibekananda Nayak
Date: 21-02-2024
Description: This class implements the AppDataStore interface and provides methods to save and retrieve various user-related data using Android's DataStore. DataStore is a modern data storage solution that stores key-value pairs asynchronously and consistently, making it suitable for storing user preferences, settings, and other persistent data. The class utilizes Kotlin flows to observe changes in data and provides suspend functions for saving data asynchronously. It also handles exceptions like IOException gracefully during data retrieval.
*/

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class AppDateStoreImpl(context: Context) : AppDataStore {

    private val dataStore = context.dataStore

    private object PreferencesKey {
        // Define keys for various preferences
        val accessTokenKey = stringPreferencesKey(name = ACCESS_TOKEN)
        val userNameKey = stringPreferencesKey(name = USER_NAME)
        val emailKey = stringPreferencesKey(name = EMAIL)
        val passwordKey = stringPreferencesKey(name = PASSWORD_KEY)

    }

    // Method to save login response data
    override suspend fun saveToken(
        accessToken: String
    ) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.accessTokenKey] = accessToken

        }
    }

    override suspend fun saveUserName(userName: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.userNameKey] = userName
        }
    }



    override suspend fun saveEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.emailKey] = email
        }
    }

    override suspend fun savePassword(password: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.passwordKey] = password
        }
    }

    override fun getPassword(): Flow<String?> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val password = preferences[PreferencesKey.passwordKey]
            password
        }
    }

    override fun getEmail(): Flow<String?> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
                val email = preferences[PreferencesKey.emailKey]
                email
            }
    }





    override fun getAccessToken(): Flow<String?> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
                val accessToken = preferences[PreferencesKey.accessTokenKey]
                accessToken
            }
    }

    override fun getUserName(): Flow<String?> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
                val userName = preferences[PreferencesKey.userNameKey]
                userName
            }
    }






}

