package com.bibek.taskmanager.domain.usecase

import com.bibek.taskmanager.data.model.signin.res.SignInResponse
import com.bibek.taskmanager.domain.repository.UserRepository
import com.bibek.taskmanager.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class SignInUseCase(private val userRepository: UserRepository)  {
    operator fun invoke(email : String, password : String): Flow<NetworkResult<SignInResponse>> {
        return userRepository.loginUser(email, password)
    }
}