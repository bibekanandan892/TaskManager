package com.bibek.taskmanager.di

import android.content.Context
import com.bibek.taskmanager.data.app_datastore.AppDateStoreImpl
import com.bibek.taskmanager.data.repository.TaskRepositoryImpl
import com.bibek.taskmanager.data.repository.UserRepositoryImpl
import com.bibek.taskmanager.domain.repository.AppDataStore
import com.bibek.taskmanager.domain.repository.TaskRepository
import com.bibek.taskmanager.domain.repository.UserRepository
import com.bibek.taskmanager.domain.usecase.DeleteTaskUseCase
import com.bibek.taskmanager.domain.usecase.FetchAllTaskUseCase
import com.bibek.taskmanager.domain.usecase.GetEmailUseCase
import com.bibek.taskmanager.domain.usecase.GetPasswordUseCase
import com.bibek.taskmanager.domain.usecase.GetTokenUseCase
import com.bibek.taskmanager.domain.usecase.GetUserNameUseCase
import com.bibek.taskmanager.domain.usecase.GetUserUseCase
import com.bibek.taskmanager.domain.usecase.InsertTaskUseCase
import com.bibek.taskmanager.domain.usecase.SaveEmailUseCase
import com.bibek.taskmanager.domain.usecase.SavePasswordUseCase
import com.bibek.taskmanager.domain.usecase.SaveTokenUseCase
import com.bibek.taskmanager.domain.usecase.SaveUserNameUseCase
import com.bibek.taskmanager.domain.usecase.SignInUseCase
import com.bibek.taskmanager.domain.usecase.SignUpUseCase
import com.bibek.taskmanager.domain.usecase.UpdateTaskUseCase
import com.bibek.taskmanager.domain.usecase.UploadPhotoUseCase
import com.bibek.taskmanager.domain.usecase.UseCases

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton
/**
 * Author: Bibekananda Nayak
 * Date: 2024-02-21
 * Description: This module provides dependencies for repository and use cases in the application.
 */

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // Provides a singleton instance of UseCases, combining various use cases
    @Singleton
    @Provides
    fun provideUseCases(
        appDataStore: AppDataStore,
        userRepository: UserRepository,
        taskRepository: TaskRepository
    ): UseCases {
        return UseCases(
            getEmailUseCase = GetEmailUseCase(appDataStore),
            getPasswordUseCase = GetPasswordUseCase(appDataStore),
            getUserNameUseCase = GetUserNameUseCase(appDataStore),
            saveEmailUseCase = SaveEmailUseCase(appDataStore),
            savePasswordUseCase = SavePasswordUseCase(appDataStore),
            saveUserNameUseCase = SaveUserNameUseCase(appDataStore),
            signInUseCase = SignInUseCase(userRepository),
            signUpUseCase = SignUpUseCase(userRepository),
            getTokenUseCase = GetTokenUseCase(appDataStore),
            saveTokenUseCase = SaveTokenUseCase(appDataStore),
            fetchAllTaskUseCase = FetchAllTaskUseCase(taskRepository = taskRepository),
            insertTaskUseCase = InsertTaskUseCase(taskRepository = taskRepository),
            updateTaskUseCase = UpdateTaskUseCase(taskRepository = taskRepository),
            deleteTaskUseCase = DeleteTaskUseCase(taskRepository = taskRepository),
            getUserUseCase = GetUserUseCase(userRepository),
            uploadPhotoUseCase = UploadPhotoUseCase(userRepository)
        )
    }

    // Provides a singleton instance of AppDataStore
    @Singleton
    @Provides
    fun provideAppDataStore(@ApplicationContext context: Context): AppDataStore {
        return AppDateStoreImpl(context = context)
    }
    @Singleton
    @Provides
    fun provideUserRepository(httpClint : HttpClient): UserRepository {
        return UserRepositoryImpl(httpClient = httpClint )
    }

    @Singleton
    @Provides
    fun provideTaskRepository(httpClint : HttpClient): TaskRepository {
        return TaskRepositoryImpl(httpClient = httpClint)
    }
}
