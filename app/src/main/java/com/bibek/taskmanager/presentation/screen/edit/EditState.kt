package com.bibek.taskmanager.presentation.screen.edit

import com.bibek.taskmanager.data.task.TaskStatus
import java.util.UUID

data class EditState(
    val isLoading : Boolean = false,
    val taskTitle : String = "",
    val taskDescription: String = "",
    val time : Long = System.currentTimeMillis(),
    val status : String = TaskStatus.ToDo.name,
    val taskId : String = UUID.randomUUID().toString(),
    val isUpdateScreen: Boolean = false


)
