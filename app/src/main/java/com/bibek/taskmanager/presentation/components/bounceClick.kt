package com.bibek.taskmanager.presentation.components







import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

/**
 * Author: Bibekananda Nayak
 * Date: 2024-02-21
 * Description: This function adds a bounce effect to a composable button when clicked.
 */

fun Modifier.bounceClick(onClick: () -> Unit = {}) = composed {
    // State to track the button's state (pressed or idle)
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    // State to animate the scale of the button for the bounce effect
    val scale by animateFloatAsState(
        if (buttonState == ButtonState.Pressed) 0.90f else 1f,
        label = ""
    )

    // Apply scale transformation to the button
    this.graphicsLayer {
        scaleX = scale
        scaleY = scale
    }
        // Make the button clickable
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        )
        // Handle pointer input to track button press and release events
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    // Wait for button release or cancellation and transition to idle state
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    // Wait for first pointer down event and transition to pressed state
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}

// Enum to represent button states
enum class ButtonState { Pressed, Idle }

