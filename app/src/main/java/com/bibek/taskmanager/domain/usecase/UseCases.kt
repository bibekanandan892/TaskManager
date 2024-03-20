package com.bibek.taskmanager.domain.usecase

/**
 * Author: Bibekananda Nayak
 *
 * Date: 2024-02-21
 *
 * Description: This data class represents a collection of various use cases related to user authentication,
 * password management, user data storage, OAuth login, wallet operations, and admin-related operations.
 * Each use case is encapsulated as a property of the data class.
 */

data class UseCases(
    val saveEmailUseCase: SaveEmailUseCase,
    val savePasswordUseCase: SavePasswordUseCase,
    val saveUserNameUseCase: SaveUserNameUseCase,
    val getEmailUseCase: GetEmailUseCase,
    val getPasswordUseCase: GetPasswordUseCase,
    val getUserNameUseCase: GetUserNameUseCase,
    val signInUseCase: SignInUseCase,
    val signUpUseCase: SignUpUseCase,
    val saveTokenUseCase: SaveTokenUseCase,
    val getTokenUseCase: GetTokenUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase,
    val updateTaskUseCase: UpdateTaskUseCase,
    val insertTaskUseCase: InsertTaskUseCase,
    val fetchAllTaskUseCase: FetchAllTaskUseCase,
    val getUserUseCase: GetUserUseCase,
    val uploadPhotoUseCase: UploadPhotoUseCase
)
