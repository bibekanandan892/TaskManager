package com.bibek.taskmanager.domain.repository
import kotlinx.coroutines.flow.Flow


interface AppDataStore {
    suspend fun saveToken(accessToken: String)
    suspend fun saveUserName(userName : String)
    suspend fun saveEmail(email : String)

    fun getEmail() : Flow<String?>
    suspend fun savePassword(password : String)
    fun getPassword() : Flow<String?>

    fun getAccessToken() : Flow<String?>
    fun getUserName() : Flow<String?>

}