package com.bibek.taskmanager.presentation.screen.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import com.bibek.taskmanager.presentation.components.bounceClick
import com.bibek.taskmanager.utils.Constants
import com.bibek.taskmanager.utils.hdp
import com.bibek.taskmanager.utils.resHeight
import com.bibek.taskmanager.utils.resWidth
import com.bibek.taskmanager.utils.wdp

/**
 * Author: Bibekananda Nayak
 *
 * Date: 21-02-2024
 *
 * Description: Composable function for displaying a login screen UI. It includes fields for username and password entry,
 * option for password visibility toggle, option for remembering login credentials, and a button for login. Additionally,
 * it provides a link for forgotten passwords. The UI elements are designed using Jetpack Compose.
 */
@Composable
fun LoginScreen(
    uiState: LoginState,
    onEvent: (LoginEvent) -> Unit = { }
) {
    // Main column layout for the login screen
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
            Text("Login", color = MaterialTheme.colorScheme.onPrimaryContainer, fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier.resHeight(0.02f))
            // Username input field
            InputText(
                label = "Email",
                value = uiState.email,
                onValueChange = { onEvent(LoginEvent.OnEmailTextChange(it))},
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
                    onEvent(LoginEvent.OnPasswordVisibilityChange)
                },
                value = uiState.password,
                onValueChange = { onEvent(LoginEvent.OnPasswordTextChange(it)) },
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(modifier = Modifier.resHeight(0.01f))
            // Remember me checkbox and forgot password link
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = Constants.LARGE_PADDING),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        checked = uiState.isRememberUserIdAndPassword,
                        onCheckedChange = { onEvent(LoginEvent.OnRememberPasswordClick) },
                        colors = CheckboxDefaults.colors()
                    )
                    Text(text = "Remember me", color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
                Text(
                    text = "Sign Up",
                    modifier = Modifier.bounceClick(onClick = { onEvent(LoginEvent.NavigateToSignUp) }),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

            }
        }
        // Login button
        Column {
            Button(
                modifier = Modifier.fillMaxWidth(),
                text = "Login",
                isLoading = uiState.isLoadingState
            ) {
                onEvent(LoginEvent.Login)
            }
        }
    }
}

