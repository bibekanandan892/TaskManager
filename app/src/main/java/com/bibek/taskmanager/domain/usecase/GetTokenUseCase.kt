package com.bibek.taskmanager.domain.usecase

import com.bibek.taskmanager.domain.repository.AppDataStore
import kotlinx.coroutines.flow.Flow

class GetTokenUseCase(private val appDataStore: AppDataStore) {
    // Operator function to invoke the use case and retrieve the password as a Flow
    operator fun invoke(): Flow<String?> {
        return appDataStore.getAccessToken()
    }
}