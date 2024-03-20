package com.bibek.taskmanager.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.bibek.taskmanager.utils.formatTimeFromMillis

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteTopBar(
    isUpdateScreen: Boolean=false,
    time: Long,
    onBackPressed: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {

    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Arrow Icon"
                )
            }
        },
        title = {
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = if(isUpdateScreen) "Update" else "Add Task",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = formatTimeFromMillis(time),
                    style = TextStyle(fontSize = MaterialTheme.typography.bodySmall.fontSize),
                    textAlign = TextAlign.Center
                )
            }
        },
        actions = {
            if(isUpdateScreen){
                IconButton(onClick = {
                    onDeleteClick.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Date Icon",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

        }
    )


}

