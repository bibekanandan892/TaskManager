package com.bibek.taskmanager.presentation.screen.task

import android.content.Context
import android.net.Uri
import com.bibek.taskmanager.data.task.Task

sealed interface TaskEvent {
    data class OnTaskClick(val task : Task) : TaskEvent
    object OnSignOutClick : TaskEvent
    data class UploadPhoto(val context: Context,val photo : Uri) : TaskEvent
    object CreateTaskClick : TaskEvent
    data class OnSearch(val text: String) : TaskEvent
    data class OnQueryChange(val text :String): TaskEvent
    object OnActiveChange : TaskEvent
    data class OnTaskStatusClick(val status : String): TaskEvent
    data class GetUser(val context: Context): TaskEvent

}