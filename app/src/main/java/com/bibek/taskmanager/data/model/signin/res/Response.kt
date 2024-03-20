package com.bibek.taskmanager.data.model.signin.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerialName("token")
    val token: String? = null
)