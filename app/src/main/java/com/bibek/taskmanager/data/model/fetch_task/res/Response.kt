package com.bibek.taskmanager.data.model.fetch_task.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerialName("status")
    val status: Int? = null,
    @SerialName("taskDescription")
    val taskDescription: String? = null,
    @SerialName("taskId")
    val taskId: String? = null,
    @SerialName("taskTitle")
    val taskTitle: String? = null,
    @SerialName("time")
    val time: Long? = null,
    @SerialName("userId")
    val userId: String? = null
)