package com.bibek.taskmanager.domain.usecase

import com.bibek.taskmanager.data.model.insert_task.res.InsertTaskResponse
import com.bibek.taskmanager.data.task.Task
import com.bibek.taskmanager.domain.repository.TaskRepository
import com.bibek.taskmanager.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class InsertTaskUseCase(private val taskRepository: TaskRepository)  {
    operator fun invoke(task: Task): Flow<NetworkResult<InsertTaskResponse>> {
        return taskRepository.insertTask(task)
    }
}