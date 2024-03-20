package com.bibek.taskmanager.domain.repository

import com.bibek.taskmanager.data.model.delete_task.res.DeleteTaskResponse
import com.bibek.taskmanager.data.model.fetch_task.res.FetchTaskResponse
import com.bibek.taskmanager.data.model.insert_task.res.InsertTaskResponse
import com.bibek.taskmanager.data.model.update_task.res.UpdateTaskResponse
import com.bibek.taskmanager.data.task.Task
import com.bibek.taskmanager.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

     fun getTask(): Flow<NetworkResult<FetchTaskResponse>>
     fun insertTask(task: Task): Flow<NetworkResult<InsertTaskResponse>>
     fun updateTask(task: Task): Flow<NetworkResult<UpdateTaskResponse>>
     fun deleteTask(taskId: String): Flow<NetworkResult<DeleteTaskResponse>>

}