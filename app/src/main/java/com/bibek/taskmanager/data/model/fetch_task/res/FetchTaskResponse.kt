package com.bibek.taskmanager.data.model.fetch_task.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FetchTaskResponse(
    @SerialName("message")
    val message: String? = null,
    @SerialName("response")
    val response: List<Response?>? = null,
    @SerialName("success")
    val success: Boolean? = null
)