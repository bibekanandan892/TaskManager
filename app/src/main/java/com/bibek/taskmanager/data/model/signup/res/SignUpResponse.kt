package com.bibek.taskmanager.data.model.signup.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    @SerialName("message")
    val message: String? = null,
    @SerialName("response")
    val response: String? = null,
    @SerialName("success")
    val success: Boolean? = null
)