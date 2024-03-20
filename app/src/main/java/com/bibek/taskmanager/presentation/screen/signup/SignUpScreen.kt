package com.bibek.taskmanager.presentation.screen.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import com.bibek.taskmanager.R
import com.bibek.taskmanager.presentation.components.Button
import com.bibek.taskmanager.presentation.components.InputText
import com.bibek.taskmanager.utils.hdp
import com.bibek.taskmanager.utils.resHeight
import com.bibek.taskmanager.utils.resWidth
import com.bibek.taskmanager.utils.wdp

@Composable
fun SignUpScreen(uiState : SignUpState,onEvent: (SignUpEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 0.03f.wdp(), vertical = 0.03f.hdp()),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Inner column for aligning elements at the center
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Jkbk logo image


            Spacer(modifier = Modifier.resHeight(0.01f))
            // Mobile login image

            Image(
                painter = painterResource(id = R.drawable.logo),
                modifier = Modifier
                    .resHeight(0.3f)
                    .resWidth(0.7f),
                contentDescription = "Mobile Login Image"
            )
            Spacer(modifier = Modifier.resHeight(0.01f))
            Text("Sign Up", color = MaterialTheme.colorScheme.onPrimaryContainer, fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier.resHeight(0.02f))
            // Username input field
            InputText(
                label = "User Name",
                value = uiState.userName,
                onValueChange = { onEvent(SignUpEvent.OnUserNameTextChange(it))},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = Icons.Default.AccountBox
            )
            Spacer(modifier = Modifier.resHeight(0.01f))

            InputText(
                label = "Email",
                value = uiState.email,
                onValueChange = { onEvent(SignUpEvent.OnEmailTextChange(it))},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.resHeight(0.01f))
            // Password input field with visibility toggle
            InputText(
                label = "Password",
                leadingIcon = Icons.Default.Lock,
                trailingIcon = Icons.Default.Lock,
                visualTransformation = uiState.isPasswordVisible,
                tailOnClick = {
                    onEvent(SignUpEvent.OnPasswordVisibilityChange)
                },
                value = uiState.password,
                onValueChange = { onEvent(SignUpEvent.OnPasswordTextChange(it)) },
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(modifier = Modifier.resHeight(0.01f))
            // Remember me checkbox and forgot password link

        }
        // Login button
        Column {
            Button(
                modifier = Modifier.fillMaxWidth(),
                text = "Sign Up",
                isLoading = uiState.isLoadingState
            ) {
                onEvent(SignUpEvent.SignUp)
            }
        }
    }
}