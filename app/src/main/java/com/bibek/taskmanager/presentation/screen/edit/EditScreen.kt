package com.bibek.taskmanager.presentation.screen.edit


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.bibek.taskmanager.data.task.TaskStatus
import com.bibek.taskmanager.presentation.components.Button
import com.bibek.taskmanager.presentation.components.TaskStatus
import com.bibek.taskmanager.presentation.components.WriteTopBar
import kotlinx.coroutines.launch

@Composable
fun EditScreen(editViewModel: EditViewModel) {
    val uiState by editViewModel.uiState.collectAsState()
    val onEvent = editViewModel::onEvent
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            WriteTopBar(
                time = uiState.time,
                onBackPressed = { onEvent(EditEvent.OnBackPressed) },
                onDeleteClick = {
                    onEvent(EditEvent.OnDeleteClick(uiState.taskId))
                }, isUpdateScreen = uiState.isUpdateScreen)
        }) {
        WriteContent(
            paddingValues = it,
            uiState = uiState,
            onEvent = onEvent
        )
    }
}

@Composable
fun WriteContent(
    paddingValues: PaddingValues,
    uiState: EditState,
    onEvent: (EditEvent) -> Unit,
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current


    LaunchedEffect(key1 = scrollState.maxValue) {
        scrollState.scrollTo(scrollState.maxValue)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .navigationBarsPadding()
            .padding(top = paddingValues.calculateTopPadding())
            .padding(bottom = 24.dp)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(state = scrollState)
        ) {
            Spacer(modifier = Modifier.height(15.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                TaskStatus(
                    title = "To Do",
                    color = if (uiState.status == TaskStatus.ToDo.name) Color.Red else Color.Gray
                ) {
                    onEvent(EditEvent.OnTaskStatusClick(TaskStatus.ToDo.name))
                }
                Spacer(modifier = Modifier.width(10.dp))

                TaskStatus(
                    title = "In Progress",
                    color = if (uiState.status == TaskStatus.InProgress.name) Color.Yellow else Color.Gray
                ) {
                    onEvent(EditEvent.OnTaskStatusClick(TaskStatus.InProgress.name))
                }
                Spacer(modifier = Modifier.width(10.dp))

                TaskStatus(
                    title = "Done",
                    color = if (uiState.status == TaskStatus.Done.name) Color.Green else Color.Gray
                ) {
                    onEvent(EditEvent.OnTaskStatusClick(TaskStatus.Done.name))
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.taskTitle,
                onValueChange = {
                    onEvent(EditEvent.OnTitleTextChange(it))
                }, textStyle = TextStyle(fontSize = TextUnit(20f, TextUnitType.Sp)),
                placeholder = { Text(text = "Title", fontSize = TextUnit(20f, TextUnitType.Sp)) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Unspecified,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),

                    ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        scope.launch {
                            scrollState.animateScrollTo(Int.MAX_VALUE)
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    }
                ),
                maxLines = 1,
                singleLine = true
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.taskDescription,
                onValueChange = { onEvent(EditEvent.OnDescriptionTextChange(it)) },
                placeholder = { Text(text = "Tell me about it.") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Unspecified,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.clearFocus()
                    }
                )
            )
        }

        Column(verticalArrangement = Arrangement.Bottom) {
            Spacer(modifier = Modifier.height(12.dp))

            Spacer(modifier = Modifier.height(12.dp))
            Button(text = if(uiState.isUpdateScreen) "Update" else "Save", isLoading = false, modifier = Modifier.fillMaxWidth()) {
                if(uiState.isUpdateScreen){
                    onEvent(EditEvent.UpdateTaskClick)
                }else{
                    onEvent(EditEvent.CreateTaskClick)
                }
            }
        }
    }
}