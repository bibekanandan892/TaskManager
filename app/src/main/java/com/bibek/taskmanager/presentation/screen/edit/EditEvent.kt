package com.bibek.taskmanager.presentation.screen.edit


sealed interface EditEvent {

    object UpdateTaskClick : EditEvent
    object CreateTaskClick : EditEvent
    data class OnTaskStatusClick(val status : String): EditEvent
    data class OnDeleteClick(val taskId : String): EditEvent
    object OnBackPressed : EditEvent

    data class OnTitleTextChange(val text : String) : EditEvent
    data class OnDescriptionTextChange(val text : String) : EditEvent

}