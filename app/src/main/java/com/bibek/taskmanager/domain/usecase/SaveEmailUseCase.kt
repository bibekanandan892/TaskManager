package com.bibek.taskmanager.domain.usecase

import com.bibek.taskmanager.domain.repository.AppDataStore

class SaveEmailUseCase(private val appDataStore: AppDataStore) {
    // This function invokes the use case to save the provided userName using the AppDataStore.
    suspend operator fun invoke(email : String) {
        appDataStore.saveEmail(email = email)
    }
}