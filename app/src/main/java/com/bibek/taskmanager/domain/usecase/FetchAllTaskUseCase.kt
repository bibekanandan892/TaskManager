package com.bibek.taskmanager.domain.usecase

import com.bibek.taskmanager.data.model.fetch_task.res.FetchTaskResponse
import com.bibek.taskmanager.domain.repository.TaskRepository
import com.bibek.taskmanager.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class FetchAllTaskUseCase(private val taskRepository: TaskRepository)  {
    operator fun invoke(): Flow<NetworkResult<FetchTaskResponse>> {
        return taskRepository.getTask()
    }
}