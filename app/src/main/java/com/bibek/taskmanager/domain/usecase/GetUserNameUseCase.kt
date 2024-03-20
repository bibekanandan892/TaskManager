package com.bibek.taskmanager.domain.usecase

import com.bibek.taskmanager.domain.repository.AppDataStore
import kotlinx.coroutines.flow.Flow

/**
Author: Bibekananda Nayak
Date: 21-02-2024
Description: This class represents a use case for retrieving a user's name from the app data store asynchronously. It encapsulates the logic for retrieving the user name and exposes it as a Flow<String?>, allowing observers to receive updates when the user name changes.
*/

class GetUserNameUseCase(private val appDataStore: AppDataStore) {

    // This operator function is invoked to retrieve the user's name asynchronously as a Flow<String?>.
    operator fun invoke(): Flow<String?> {
        return appDataStore.getUserName()
    }
}
