package com.bibek.taskmanager.domain.usecase
import com.bibek.taskmanager.domain.repository.AppDataStore
import kotlinx.coroutines.flow.Flow

/**
Author: Bibekananda Nayak
Date: 21-02-2024
Description: This class represents a use case to get the password from an AppDataStore. It encapsulates the logic to retrieve the password and exposes it as a Flow<String?>, which allows for asynchronous handling of the password data.
*/

class GetPasswordUseCase(private val appDataStore: AppDataStore) {
    // Operator function to invoke the use case and retrieve the password as a Flow
    operator fun invoke(): Flow<String?> {
        return appDataStore.getPassword()
    }
}
