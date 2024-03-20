package com.bibek.taskmanager.presentation.screen.signup

import androidx.compose.runtime.Immutable


@Immutable
data class SignUpState(
    val email : String = "",
    val password : String = "",
    val userName : String = "",
    val isLoadingState : Boolean =  false,
    val isPasswordVisible : Boolean = true
)


