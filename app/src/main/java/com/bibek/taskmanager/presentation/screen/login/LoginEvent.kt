package com.bibek.taskmanager.presentation.screen.login


sealed interface LoginEvent {
    object Login : LoginEvent
    data class OnPasswordTextChange(val text : String) : LoginEvent
    data class OnEmailTextChange(val text : String) : LoginEvent
    object OnPasswordVisibilityChange : LoginEvent
    object OnRememberPasswordClick : LoginEvent
    object NavigateToSignUp : LoginEvent
}