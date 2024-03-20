package com.bibek.taskmanager.presentation.screen.task

import android.net.Uri
import com.bibek.taskmanager.data.task.Task
import com.bibek.taskmanager.data.task.TaskStatus

data class TaskState(
    val tasks : List<Task> = listOf(),
    val filterType : String = TaskStatus.All.name,
    val isLoading : Boolean = false,
    val profileImageUrl :Uri? = null,
    val email : String? = null,
    val userName : String? = null,
    val isProfileLoading : Boolean = false,
    val isSearchBarActive : Boolean = false,
    val query : String ="",

)
