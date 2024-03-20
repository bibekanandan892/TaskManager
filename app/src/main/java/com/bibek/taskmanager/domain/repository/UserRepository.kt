package com.bibek.taskmanager.domain.repository

import com.bibek.taskmanager.data.model.UserResponse
import com.bibek.taskmanager.data.model.photo.UploadPhotoResponse
import com.bibek.taskmanager.data.model.signin.res.SignInResponse
import com.bibek.taskmanager.data.model.signup.res.SignUpResponse
import com.bibek.taskmanager.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {
     fun loginUser(email : String , password : String) : Flow<NetworkResult<SignInResponse>>
     fun signUpUser(email : String, name : String, password: String) : Flow<NetworkResult<SignUpResponse>>
     fun getUser() : Flow<NetworkResult<UserResponse>>
     fun uploadProfile(photo : String) : Flow<NetworkResult<UploadPhotoResponse>>


}