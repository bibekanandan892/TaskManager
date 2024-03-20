package com.bibek.taskmanager.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun TaskStatus(modifier: Modifier = Modifier, title: String = "To Do", color: Color = Color.Red,onClick : () -> Unit ={}) {
    Box(
        modifier = modifier
            .bounceClick(onClick =onClick)
            .background(
                color = color.copy(0.3f), shape = RoundedCornerShape(15.dp)

            )
            .border(
                2.dp, color = color, shape = RoundedCornerShape(15.dp)
            )
            .padding(vertical = 7.dp, horizontal = 12.dp), contentAlignment = Alignment.Center
    ) {
        Text(text = title, color = color)
    }
}