package com.bibek.taskmanager.domain.usecase

import com.bibek.taskmanager.data.model.delete_task.res.DeleteTaskResponse
import com.bibek.taskmanager.domain.repository.TaskRepository
import com.bibek.taskmanager.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class DeleteTaskUseCase(private val taskRepository: TaskRepository)  {
    operator fun invoke(taskId: String): Flow<NetworkResult<DeleteTaskResponse>> {
        return taskRepository.deleteTask(taskId)
    }
}