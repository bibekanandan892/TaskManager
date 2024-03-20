package com.bibek.taskmanager.domain.usecase

import com.bibek.taskmanager.domain.repository.AppDataStore

class SaveTokenUseCase(private val appDataStore: AppDataStore) {
    // This function invokes the use case to save the provided userName using the AppDataStore.
    suspend operator fun invoke( token : String) {
        appDataStore.saveToken(accessToken = token)
    }
}