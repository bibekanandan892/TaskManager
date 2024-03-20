package com.bibek.taskmanager.domain.usecase

import com.bibek.taskmanager.domain.repository.AppDataStore
/**
Author: Bibekananda Nayak
Date: 21-02-2024
Description:
This class represents a use case for saving a password using an AppDataStore.
*/

class SavePasswordUseCase(private val appDataStore: AppDataStore) {
    /*
    This function is invoked to save a password using the provided AppDataStore.

    Parameters:
        password: The password to be saved.
    */
    suspend operator fun invoke(password: String) {
        appDataStore.savePassword(password = password)
    }
}
