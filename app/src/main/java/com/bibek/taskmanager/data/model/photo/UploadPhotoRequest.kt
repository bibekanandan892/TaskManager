package com.bibek.taskmanager.data.model.photo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UploadPhotoRequest(
    val photo : String? = null
)


@Serializable
data class UploadPhotoResponse(
    @SerialName("message")
    val message: String? = null,
    @SerialName("response")
    val response: String? = null,
    @SerialName("success")
    val success: Boolean? = null
)