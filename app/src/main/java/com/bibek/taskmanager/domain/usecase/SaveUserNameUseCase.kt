package com.bibek.taskmanager.domain.usecase

import com.bibek.taskmanager.domain.repository.AppDataStore

/**
Author: Bibekananda Nayak
Date: 21-02-2024
Description: This class represents a use case for saving a user's name.
It takes an instance of AppDataStore and allows the saving of a user's name by invoking the use case.

AppDataStore is assumed to be a dependency injected into this class.
It is used to save the user's name asynchronously using the suspend function saveUserName().
*/

class SaveUserNameUseCase(private val appDataStore: AppDataStore) {
    // This function invokes the use case to save the provided userName using the AppDataStore.
    suspend operator fun invoke(userName : String) {
        appDataStore.saveUserName(userName = userName)
    }
}
