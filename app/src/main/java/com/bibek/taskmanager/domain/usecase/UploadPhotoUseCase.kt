package com.bibek.taskmanager.domain.usecase

import com.bibek.taskmanager.data.model.photo.UploadPhotoResponse
import com.bibek.taskmanager.domain.repository.UserRepository
import com.bibek.taskmanager.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class UploadPhotoUseCase(private val userRepository: UserRepository)  {
    operator fun invoke(photo: String): Flow<NetworkResult<UploadPhotoResponse>> {
        return userRepository.uploadProfile(photo = photo)
    }
}