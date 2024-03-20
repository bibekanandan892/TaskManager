package com.bibek.taskmanager.data.model.update_task.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateTaskResponse(
    @SerialName("message")
    val message: String? = null,
    @SerialName("response")
    val response: String? = null,
    @SerialName("success")
    val success: Boolean? = null
)