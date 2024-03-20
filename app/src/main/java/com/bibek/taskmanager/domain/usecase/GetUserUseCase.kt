package com.bibek.taskmanager.domain.usecase

import com.bibek.taskmanager.data.model.UserResponse
import com.bibek.taskmanager.domain.repository.UserRepository
import com.bibek.taskmanager.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val userRepository: UserRepository)  {
    operator fun invoke(): Flow<NetworkResult<UserResponse>> {
        return userRepository.getUser()
    }
}