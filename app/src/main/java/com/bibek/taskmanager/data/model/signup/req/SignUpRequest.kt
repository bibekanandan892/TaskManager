package com.bibek.taskmanager.data.model.signup.req


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    @SerialName("emailAddress")
    val emailAddress: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("password")
    val password: String? = null
)