package com.bibek.taskmanager.domain.usecase

import com.bibek.taskmanager.data.model.signup.res.SignUpResponse
import com.bibek.taskmanager.domain.repository.UserRepository
import com.bibek.taskmanager.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class SignUpUseCase(private val userRepository: UserRepository)  {
    operator fun invoke(email : String, password : String,userName : String): Flow<NetworkResult<SignUpResponse>> {
        return userRepository.signUpUser(email = email, name = userName, password = password)
    }
}