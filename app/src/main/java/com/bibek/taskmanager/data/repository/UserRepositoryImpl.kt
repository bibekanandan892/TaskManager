package com.bibek.taskmanager.data.repository

import com.bibek.taskmanager.data.model.UserResponse
import com.bibek.taskmanager.data.model.photo.UploadPhotoRequest
import com.bibek.taskmanager.data.model.photo.UploadPhotoResponse
import com.bibek.taskmanager.data.model.signin.req.SignInRequest
import com.bibek.taskmanager.data.model.signin.res.SignInResponse
import com.bibek.taskmanager.data.model.signup.req.SignUpRequest
import com.bibek.taskmanager.data.model.signup.res.SignUpResponse
import com.bibek.taskmanager.domain.repository.UserRepository
import com.bibek.taskmanager.utils.Endpoints
import com.bibek.taskmanager.utils.NetworkResult
import com.bibek.taskmanager.utils.handleResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val httpClient: HttpClient) :
    UserRepository {


    override fun loginUser(
        email: String,
        password: String
    ): Flow<NetworkResult<SignInResponse>> {
        return handleResponse {
            httpClient.post {
                url(Endpoints.SignIn.route)
                setBody(SignInRequest(email, password))
            }
        }
    }

    override fun signUpUser(
        email: String,
        name: String,
        password: String
    ): Flow<NetworkResult<SignUpResponse>> {
        return handleResponse {
            httpClient.post {
                url(Endpoints.SingUp.route)
                setBody(SignUpRequest(emailAddress = email, password = password, name = name))
            }
        }
    }

    override fun getUser(): Flow<NetworkResult<UserResponse>> {
        return handleResponse {
            httpClient.get(Endpoints.GetUser.route)
        }
    }

    override fun uploadProfile(photo: String): Flow<NetworkResult<UploadPhotoResponse>> {
        return handleResponse {
            httpClient.patch {
                url(Endpoints.UploadPhoto.route)
                setBody(UploadPhotoRequest(photo = photo))
            }
        }
    }

}