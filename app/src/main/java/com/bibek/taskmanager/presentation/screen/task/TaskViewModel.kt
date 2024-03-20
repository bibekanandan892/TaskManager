package com.bibek.taskmanager.presentation.screen.task

import android.content.Context
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
import com.bibek.taskmanager.utils.base64ToUri
import com.bibek.taskmanager.utils.uriToBase64
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject


@HiltViewModel
class TaskViewModel @Inject constructor(
    private val useCases: UseCases,
    private val navigator: Navigator,
    private val toaster: Toaster
) : ViewModel() {

    var uiState = MutableStateFlow(TaskState())
        private set
    var allTask = MutableStateFlow<List<Task>>(listOf())

    init {
        // Asynchronously initialize username and password states
        viewModelScope.launch {
            fetchTasks()

        }
    }

    private suspend fun getUser(context: Context) {
        useCases.getUserUseCase.invoke().collectLatest { networkResult ->
            updateProfileLoading(false)
            when (networkResult) {

                is NetworkResult.Error -> {
                    toaster.error(networkResult.message ?: "Unknown Error")
                }

                is NetworkResult.Loading -> {
                    updateProfileLoading(true)
                }

                is NetworkResult.Success -> {
                    networkResult.data?.response?.let {
                        uiState.update { taskState ->
                            taskState.copy(
                                profileImageUrl = base64ToUri(context, it.profilePhoto?:""),
                                userName = it.name,
                                email = it.emailAddress
                            )
                        }
                    }
                }
            }
        }
    }


    fun onEvent(event: TaskEvent) {
        viewModelScope.launch {
            when (event) {
                TaskEvent.OnSignOutClick -> signOut()
                is TaskEvent.OnTaskClick -> navigator.navigate(Destinations.Edit.passTask(Json.encodeToString(event.task)))
                TaskEvent.CreateTaskClick -> navigator.navigate(Destinations.Edit.route)
                TaskEvent.OnActiveChange -> updateActiveChange()
                is TaskEvent.OnQueryChange -> updateQuery(event)
                is TaskEvent.OnSearch -> onSearch(event)
                is TaskEvent.OnTaskStatusClick -> updateTaskType(event)
                is TaskEvent.UploadPhoto -> uploadPhoto(event.context,event.photo)
                is TaskEvent.GetUser -> getUser(event.context)
            }
        }
    }

    private suspend fun uploadPhoto(context: Context,photo: Uri) {
        uriToBase64(context.contentResolver, photo)?.let {
            useCases.uploadPhotoUseCase.invoke(it).collectLatest { networkResult ->
                updateProfileLoading(false)
                when (networkResult) {

                    is NetworkResult.Error -> {
                        toaster.error(networkResult.message ?: "Unknown Error")
                    }

                    is NetworkResult.Loading -> {
                        updateProfileLoading(true)
                    }

                    is NetworkResult.Success -> {
                        networkResult.data?.let {
                            uiState.update { taskState ->
                                taskState.copy(
                                    profileImageUrl = photo,
                                )
                            }
                        }
                    }
                }
            }

        }
    }

    private fun updateTaskType(event: TaskEvent.OnTaskStatusClick) {
        uiState.update { taskState ->
            taskState.copy(tasks = allTask.value.filter { task: Task ->
                if (event.status == TaskStatus.All.name) true else task.status == when (event.status) {
                    TaskStatus.ToDo.name -> 1
                    TaskStatus.InProgress.name -> 2
                    TaskStatus.Done.name -> 3
                    else -> true
                }
            })
        }
        uiState.update { taskState -> taskState.copy(filterType = event.status) }
    }

    private fun onSearch(event: TaskEvent.OnSearch) {
        uiState.update { taskState ->
            taskState.copy(tasks = if (event.text.isEmpty()) allTask.value else allTask.value.filter { task: Task ->
                task.taskTitle?.any {
                    it.toString().contains(event.text, ignoreCase = true)
                } ?: false
            })
        }
    }

    private fun updateQuery(event: TaskEvent.OnQueryChange) {
        uiState.update { taskState ->
            taskState.copy(tasks = if (event.text.isEmpty()) allTask.value else allTask.value.filter { task: Task ->
                task.taskTitle?.any {
                    it.toString().contains(event.text, ignoreCase = true)
                } ?: false
            })
        }
        uiState.update { taskState -> taskState.copy(query = event.text) }
    }

    private fun updateActiveChange() {
//        uiState.update { taskState -> taskState.copy(isSearchBarActive = !taskState.isSearchBarActive) }
    }

    private fun signOut() {
        navigator.navigate(Destinations.Login.route) {
            popUpTo(Destinations.Login.route)
        }
    }

    private suspend fun fetchTasks() {
        useCases.fetchAllTaskUseCase.invoke().collectLatest { networkResult ->
            updateLoading(false)
            when (networkResult) {

                is NetworkResult.Error -> {
                    toaster.error(networkResult.message ?: "Unknown Error")
                }

                is NetworkResult.Loading -> {
                    updateLoading(true)
                }

                is NetworkResult.Success -> {
                    networkResult.data?.response?.let {
                        allTask.update { tasks ->
                            it.map {
                                Task(
                                    status = it?.status,
                                    taskDescription = it?.taskDescription,
                                    taskId = it?.taskId,
                                    taskTitle = it?.taskTitle,
                                    time = it?.time,
                                    userId = it?.userId
                                )
                            }
                        }
                        uiState.update { taskState ->
                            taskState.copy(tasks = it.map {
                                Task(
                                    status = it?.status,
                                    taskDescription = it?.taskDescription,
                                    taskId = it?.taskId,
                                    taskTitle = it?.taskTitle,
                                    time = it?.time,
                                    userId = it?.userId
                                )
                            })
                        }
                    }

                }

            }

        }
    }

    private fun updateLoading(isLoading: Boolean) {
        uiState.update { taskState -> taskState.copy(isLoading = isLoading) }
    }
    private fun updateProfileLoading(isLoading: Boolean) {
        uiState.update { taskState -> taskState.copy(isProfileLoading = isLoading) }
    }

}