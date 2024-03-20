package com.bibek.taskmanager.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerialName("emailAddress")
    val emailAddress: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("profilePhoto")
    val profilePhoto: String? = null,
    @SerialName("password")
    val password: String? = null,
    @SerialName("userId")
    val userId: String? = null
)