package com.bibek.taskmanager.data.repository

import com.bibek.taskmanager.data.model.delete_task.req.TaskDeleteRequest
import com.bibek.taskmanager.data.model.delete_task.res.DeleteTaskResponse
import com.bibek.taskmanager.data.model.fetch_task.res.FetchTaskResponse
import com.bibek.taskmanager.data.model.insert_task.res.InsertTaskResponse
import com.bibek.taskmanager.data.model.update_task.res.UpdateTaskResponse
import com.bibek.taskmanager.data.task.Task
import com.bibek.taskmanager.domain.repository.TaskRepository
import com.bibek.taskmanager.utils.Endpoints
import com.bibek.taskmanager.utils.NetworkResult
import com.bibek.taskmanager.utils.handleResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val httpClient: HttpClient) : TaskRepository {
    override  fun getTask(): Flow<NetworkResult<FetchTaskResponse>> {
        return handleResponse {
            httpClient.get(urlString = Endpoints.FetchTasks.route)
        }
    }

    override  fun insertTask(task: Task): Flow<NetworkResult<InsertTaskResponse>> {
        return handleResponse {
            httpClient.post {
                url(Endpoints.InsertTask.route)
                setBody(task)
            }
        }
    }

    override fun updateTask(task: Task): Flow<NetworkResult<UpdateTaskResponse>> {
        return handleResponse {
            httpClient.patch {
                url(Endpoints.UpdateTask.route)
                setBody(task)
            }
        }
    }

    override  fun deleteTask(taskId: String): Flow<NetworkResult<DeleteTaskResponse>> {
        return handleResponse {
            httpClient.delete {
                url(Endpoints.DeleteTask.route)
                setBody(TaskDeleteRequest(taskId = taskId))
            }
        }
    }
}