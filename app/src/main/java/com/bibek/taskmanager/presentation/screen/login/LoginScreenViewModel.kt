package com.bibek.taskmanager.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bibek.taskmanager.domain.usecase.UseCases
import com.bibek.taskmanager.presentation.navigation.graph.Navigator
import com.bibek.taskmanager.presentation.navigation.routes.Destinations
import com.bibek.taskmanager.utils.NetworkResult
import com.bibek.taskmanager.utils.Toaster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *Author: Bibekananda Nayak
 *
 *Date: 21-02-2024
 *
 * Description:
This ViewModel class, LoginScreenViewModel, is responsible for managing the login screen logic in an Android application.
It utilizes Hilt for dependency injection and incorporates various state variables to manage the UI states such as username,
password, loading indicator, visibility of password, and remembering user credentials.

The ViewModel initializes the username and password states asynchronously using coroutines in its init block.
It provides a function, oAuthLogin(), to handle the OAuth login process. This function interacts with the
defined use cases to perform the login operation. It observes the network result using collectLatest,
updating the UI states accordingly based on whether the operation succeeds, encounters an error,
or is still loading. Upon successful login, it saves the login response, password, and username if
the user chooses to remember them, displays a success message, and navigates to the dashboard destination.

 */

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val useCases: UseCases,
    private val navigator: Navigator,
    private val toaster: Toaster
) : ViewModel() {

    var uiState = MutableStateFlow(LoginState())
        private set

    init {
        // Asynchronously initialize username and password states
        viewModelScope.launch {
            uiState.update { loginState ->
                loginState.copy(
                    password = useCases.getPasswordUseCase.invoke().first() ?: "",
                    email = useCases.getEmailUseCase.invoke().first() ?: ""
                )
            }
        }
    }


    fun onEvent(event: LoginEvent) {
        viewModelScope.launch {
            when (event) {
                LoginEvent.Login -> login()
                is LoginEvent.OnPasswordTextChange -> updatePassword(event)
                LoginEvent.OnPasswordVisibilityChange -> updatePasswordVisibility()
                LoginEvent.OnRememberPasswordClick -> updateSaveUserAndPassword()
                is LoginEvent.OnEmailTextChange -> updateEmail(event)
                LoginEvent.NavigateToSignUp -> navigator.navigate(Destinations.SignUpScreen.route)
            }
        }
    }

    private suspend fun login() {
        useCases.signInUseCase.invoke(
            email = uiState.value.email,
            password = uiState.value.password,
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
                    if (networkResult.data?.response?.token != null) {
                        useCases.savePasswordUseCase.invoke(password = uiState.value.password)
                        useCases.saveEmailUseCase.invoke(email = uiState.value.email)
                        useCases.saveTokenUseCase.invoke(token = networkResult.data.response.token)
                        navigator.navigate(Destinations.Task.route) {
                            popUpTo(Destinations.Login.route) {
                                inclusive = true
                            }
                        }
                    } else {
                        toaster.error(networkResult.message ?: "Something Went Wrong")
                    }

                }

            }
        }
    }


    private fun updateLoading(isLoading: Boolean) {
        uiState.update { loginState -> loginState.copy(isLoadingState = isLoading) }
    }

    private fun updateSaveUserAndPassword() {
        uiState.update { loginState -> loginState.copy(isRememberUserIdAndPassword = !loginState.isRememberUserIdAndPassword) }
    }

    private fun updatePasswordVisibility() {
        uiState.update { loginState -> loginState.copy(isPasswordVisible = !loginState.isPasswordVisible) }
    }

    private fun updatePassword(event: LoginEvent.OnPasswordTextChange) {
        uiState.update { loginState -> loginState.copy(password = event.text) }
    }

    private fun updateEmail(event: LoginEvent.OnEmailTextChange) {
        uiState.update { loginState -> loginState.copy(email = event.text.take(40)) }
    }

}
