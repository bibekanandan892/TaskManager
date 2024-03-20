package com.bibek.taskmanager.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.bibek.taskmanager.utils.Constants

@Composable
fun InputText(modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    trailingIcon: ImageVector? = null,
    tailOnClick: () -> Unit = {},
    leadingIcon: ImageVector = Icons.Default.Email,
    visualTransformation: Boolean = false,

    ) {
    val keyboard = LocalSoftwareKeyboardController.current

    OutlinedTextField(modifier = modifier
        .padding(horizontal = Constants.LARGE_PADDING),value = value, onValueChange = onValueChange, singleLine = true, keyboardActions = KeyboardActions(onDone = {
        keyboard?.hide()
    }), keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        leadingIcon = {
            Icon(imageVector = leadingIcon,contentDescription = null)
        }, placeholder = {
            Text(text = label)
        },trailingIcon = {
            if (trailingIcon != null) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { tailOnClick() }
                )
            }
        },
        visualTransformation = if (visualTransformation) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    )
}