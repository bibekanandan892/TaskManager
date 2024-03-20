package com.bibek.taskmanager.utils

/**
 * Author: Bibekananda Nayak
 * Date: 21-02-2024
 * Description: This Kotlin code defines a sealed class `Endpoint` that encapsulates various API endpoints as objects.
 * Each object represents a specific API endpoint with its corresponding route.
 * This design pattern helps in organizing and managing endpoint URLs in a structured manner within the codebase.
 */

sealed class Endpoints(val route: String) {
    companion object{
        const val BASE_URL = "http://192.168.43.233:8080"
    }
    // Login endpoint for OAuth2 authentication
    object SignIn: Endpoints(route = "$BASE_URL/sign_in")
    object SingUp: Endpoints(route = "$BASE_URL/sign_up")
    object InsertTask: Endpoints(route = "$BASE_URL/insert_task")
    object UpdateTask: Endpoints(route = "$BASE_URL/update_task")
    object UploadPhoto: Endpoints(route = "$BASE_URL/update_photo")
    object DeleteTask: Endpoints(route = "$BASE_URL/delete_task")
    object FetchTasks: Endpoints(route = "$BASE_URL/fetch_task")
    object GetUser: Endpoints(route = "$BASE_URL/get_user")
}
