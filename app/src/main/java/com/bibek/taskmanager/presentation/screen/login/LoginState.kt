package com.bibek.taskmanager.presentation.screen.login

import androidx.compose.runtime.Immutable


@Immutable
data class LoginState(
    val email : String = "",
    val password : String = "",
    val isLoadingState : Boolean =  false,
    val isRememberUserIdAndPassword : Boolean = false,
    val isPasswordVisible : Boolean = true)


