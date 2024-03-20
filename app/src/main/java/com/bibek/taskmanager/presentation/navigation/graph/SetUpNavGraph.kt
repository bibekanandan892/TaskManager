package com.bibek.taskmanager.presentation.navigation.graph


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bibek.taskmanager.presentation.navigation.routes.Destinations
import com.bibek.taskmanager.presentation.screen.edit.EditScreen
import com.bibek.taskmanager.presentation.screen.edit.EditViewModel
import com.bibek.taskmanager.presentation.screen.login.LoginScreen
import com.bibek.taskmanager.presentation.screen.login.LoginScreenViewModel
import com.bibek.taskmanager.presentation.screen.signup.SignUpScreen
import com.bibek.taskmanager.presentation.screen.signup.SignUpViewModel
import com.bibek.taskmanager.presentation.screen.task.TaskScreen
import com.bibek.taskmanager.presentation.screen.task.TaskViewModel

/**
 * Author: Bibekananda Nayak
 * Date: 21-02-2024
Description: This Composable function defines the navigation graph setup
for the application using Jetpack Compose and the Navigation component.
It specifies the destinations and associated screens, along with view models obtained
via Hilt dependency injection. Each destination is mapped to a specific screen
Composable function, facilitating navigation between different screens of the application.
 */
@Composable
fun SetUpNavGraph(
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = Destinations.Login.route
    ) {
        // Login screen destination
        composable(
            route = Destinations.Login.route,
        ) {
            val loginScreenViewModel: LoginScreenViewModel = hiltViewModel()
            val uiState by loginScreenViewModel.uiState.collectAsState()
            LoginScreen(
                uiState = uiState ,
                onEvent = loginScreenViewModel::onEvent
            )
        }

        composable(
            route = Destinations.SignUpScreen.route,
        ) {
            val signUpViewModel: SignUpViewModel = hiltViewModel()
            val uiState by signUpViewModel.uiState.collectAsState()
            SignUpScreen(
                uiState = uiState ,
                onEvent = signUpViewModel::onEvent
            )
        }

        composable(
            route = Destinations.Task.route,
        ) {
            val taskViewModel: TaskViewModel = hiltViewModel()
            val uiState by taskViewModel.uiState.collectAsState()
            TaskScreen(
                uiState = uiState ,
                onEvent = taskViewModel::onEvent
            )
        }

        composable(
            route = Destinations.Edit.route, arguments = listOf(navArgument("task") { type = NavType.StringType })
        ) {backStackEntry ->
            val task = backStackEntry.arguments?.getString("task")
            val editViewModel: EditViewModel = hiltViewModel()
            editViewModel.setUpUiState(taskString = task)

            EditScreen(
                editViewModel = editViewModel,
            )
        }

    }
}
