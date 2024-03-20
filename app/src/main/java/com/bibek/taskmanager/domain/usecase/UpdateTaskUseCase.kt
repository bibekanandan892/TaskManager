package com.bibek.taskmanager.domain.usecase

import com.bibek.taskmanager.data.model.update_task.res.UpdateTaskResponse
import com.bibek.taskmanager.data.task.Task
import com.bibek.taskmanager.domain.repository.TaskRepository
import com.bibek.taskmanager.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class UpdateTaskUseCase(private val taskRepository: TaskRepository)  {
    operator fun invoke(task: Task): Flow<NetworkResult<UpdateTaskResponse>> {
        return taskRepository.updateTask(task)
    }
}