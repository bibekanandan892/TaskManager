package com.bibek.taskmanager.data.model.delete_task.req


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskDeleteRequest(
    @SerialName("taskId")
    val taskId: String? = null
)