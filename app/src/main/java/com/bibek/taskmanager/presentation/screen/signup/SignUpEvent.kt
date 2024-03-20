package com.bibek.taskmanager.presentation.screen.signup

sealed interface SignUpEvent {
    object SignUp : SignUpEvent
    data class OnPasswordTextChange(val text : String) : SignUpEvent
    data class OnEmailTextChange(val text : String) : SignUpEvent
    data class OnUserNameTextChange(val text : String) : SignUpEvent
    object OnPasswordVisibilityChange : SignUpEvent
    object NavigateToTask : SignUpEvent
}