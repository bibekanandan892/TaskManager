package com.bibek.taskmanager.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.bibek.taskmanager.utils.Constants

/**
 * Author: Bibekananda Nayak
 * Date: 21-02-2024
 * Description: This function defines a composable Button component in Jetpack Compose.
 *              It takes several parameters including text, isLoading (to indicate loading state),
 *              and onClick lambda function. The button appearance changes based on the loading state:
 *              when isLoading is true, a CircularProgressIndicator is shown, otherwise, the button
 *              with the specified text is displayed. The button also supports click animation.
 */
@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String,
    isLoading: Boolean,
    onClick: () -> Unit = {}
) {
    Row {
        // Show button if not in loading state
        AnimatedVisibility(visible = !isLoading) {
            Box(
                modifier = modifier
                    .bounceClick(onClick)
                    .height(45.dp)
                    .padding(horizontal = Constants.LARGE_PADDING)
                    .background(color = MaterialTheme.colorScheme.onPrimaryContainer, shape = RoundedCornerShape(4.dp))
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = text, color = MaterialTheme.colorScheme.surface)
            }
        }
        // Show CircularProgressIndicator if in loading state
        if (isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }
}

// Enum to represent the state of the button (not used in the provided code)
