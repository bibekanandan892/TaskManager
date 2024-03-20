package com.bibek.taskmanager.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("message")
    val message: String? = null,
    @SerialName("response")
    val response: Response? = null,
    @SerialName("success")
    val success: Boolean? = null
)