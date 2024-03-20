package com.bibek.taskmanager.data.model.signin.req


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    @SerialName("email")
    val email: String? = null,
    @SerialName("password")
    val password: String? = null
)