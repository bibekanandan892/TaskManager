package com.bibek.taskmanager.presentation.screen.edit

import android.content.ContentResolver
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bibek.taskmanager.data.task.Task
import com.bibek.taskmanager.data.task.TaskStatus
import com.bibek.taskmanager.domain.usecase.UseCases
import com.bibek.taskmanager.presentation.navigation.graph.Navigator
import com.bibek.taskmanager.presentation.navigation.routes.Destinations
import com.bibek.taskmanager.utils.NetworkResult
import com.bibek.taskmanager.utils.Toaster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.UUID
import javax.inject.Inject
import android.util.Base64

@HiltViewModel
class EditViewModel @Inject constructor(
    private val useCases: UseCases,
    private val navigator: Navigator,
    private val toaster: Toaster
) : ViewModel() {

    var uiState = MutableStateFlow(EditState())
        private set

    init {
        // Asynchronously initialize username and password states
        viewModelScope.launch {
//            uiState.update { loginState ->
//                loginState.copy(
//                    password = useCases.getPasswordUseCase.invoke().first() ?: "",
//                    username = useCases.getUserNameUseCase.invoke().first() ?: ""
//                )
//            }
        }
    }

    fun setUpUiState(taskString: String?) {
        if(!taskString.isNullOrEmpty() && taskString != "{task}") {
            val task = Json.decodeFromString<Task>(taskString)

            uiState.update { editState ->
                editState.copy(
                    taskTitle = task.taskTitle ?: "",
                    taskDescription = task.taskDescription ?: "",
                    time = task.time ?: System.currentTimeMillis(),
                    taskId = task.taskId ?: "",
                    status = when(task.status){
                        1-> TaskStatus.ToDo.name
                        2->TaskStatus.InProgress.name
                        3 -> TaskStatus.Done.name
                        else-> TaskStatus.All.name
                    }, isUpdateScreen =true
                )
            }

        }
    }


    fun onEvent(event: EditEvent) {
        viewModelScope.launch {
            when (event) {
                EditEvent.UpdateTaskClick -> updateTask()
                is EditEvent.CreateTaskClick -> createTask()
                is EditEvent.OnTaskStatusClick -> updateStatus(event.status)
                EditEvent.OnBackPressed -> navigator.back()
                is EditEvent.OnDescriptionTextChange -> updateDescription(event)
                is EditEvent.OnTitleTextChange -> updateTitle(event)
                is EditEvent.OnDeleteClick -> deleteTask(event.taskId)
            }
        }
    }

    private suspend fun deleteTask(taskId: String) {
        useCases.deleteTaskUseCase.invoke(taskId).collectLatest { networkResult ->
            updateLoading(false)
            when (networkResult) {

                is NetworkResult.Error -> {
                    toaster.error(networkResult.message ?: "Unknown Error")
                }

                is NetworkResult.Loading -> {
                    updateLoading(true)
                }

                is NetworkResult.Success -> {
                    navigator.navigate(Destinations.Task.route)
                }

            }
        }
    }

    private suspend fun updateTask() {
        useCases.updateTaskUseCase.invoke(task = Task(
            status = getStatusInt(),
            taskDescription = uiState.value.taskDescription,
            taskId = uiState.value.taskId,
            taskTitle = uiState.value.taskTitle,
            time = uiState.value.time,
            userId = useCases.getEmailUseCase.invoke().first()
        )).collectLatest { networkResult ->
            updateLoading(false)
            when (networkResult) {

                is NetworkResult.Error -> {
                    toaster.error(networkResult.message ?: "Unknown Error")
                }

                is NetworkResult.Loading -> {
                    updateLoading(true)
                }

                is NetworkResult.Success -> {
                    navigator.navigate(Destinations.Task.route)
                }

            }
        }
    }

    private suspend fun createTask() {
        useCases.insertTaskUseCase.invoke(
            task = Task(
                status = getStatusInt(),
                taskDescription = uiState.value.taskDescription,
                taskId = UUID.randomUUID().toString(),
                taskTitle = uiState.value.taskTitle,
                time = System.currentTimeMillis(),
                userId = useCases.getEmailUseCase.invoke().first()
            )
        ).collectLatest { networkResult ->
            updateLoading(false)
            when (networkResult) {

                is NetworkResult.Error -> {
                    toaster.error(networkResult.message ?: "Unknown Error")
                }

                is NetworkResult.Loading -> {
                    updateLoading(true)
                }

                is NetworkResult.Success -> {
                    navigator.navigate(Destinations.Task.route)

                }

            }
        }
    }

    private fun updateStatus(status: String) {
        uiState.update { editState -> editState.copy(status = status) }
    }


    private fun updateLoading(isLoading: Boolean) {
        uiState.update { loginState -> loginState.copy(isLoading = isLoading) }
    }

    private fun updateDescription(event: EditEvent.OnDescriptionTextChange) {
        uiState.update { editState -> editState.copy(taskDescription = event.text) }
    }

    private fun updateTitle(event: EditEvent.OnTitleTextChange) {
        uiState.update { editState -> editState.copy(taskTitle = event.text) }
    }

    private fun getStatusInt(): Int {
        return when (uiState.value.status) {
            TaskStatus.Done.name -> 3
            TaskStatus.InProgress.name -> 2
            TaskStatus.ToDo.name -> 1
            else -> 1

        }
    }

    suspend fun uriToBase64(contentResolver: ContentResolver, uri: Uri): String? {
        return withContext(Dispatchers.IO) {
            var inputStream: InputStream? = null
            var byteArrayOutputStream: ByteArrayOutputStream? = null
            try {
                inputStream = contentResolver.openInputStream(uri)
                byteArrayOutputStream = ByteArrayOutputStream()
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (inputStream!!.read(buffer).also { bytesRead = it } != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead)
                }
                val byteArray = byteArrayOutputStream.toByteArray()
                Base64.encodeToString(byteArray, Base64.DEFAULT)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            } finally {
                inputStream?.close()
                byteArrayOutputStream?.close()
            }
        }
    }
}