package com.bibek.taskmanager.data.task

sealed class TaskType(val type : String) {
    object ToDo : TaskType(type = "ToDo")
    object InProgress : TaskType(type = "InProgress")
    object Done : TaskType(type = "Done")
    object All : TaskType(type = "All")
}