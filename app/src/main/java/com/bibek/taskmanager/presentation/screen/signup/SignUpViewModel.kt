package com.bibek.taskmanager.presentation.screen.signup

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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCases: UseCases,
    private val navigator: Navigator,
    private val toaster: Toaster
) : ViewModel() {

    var uiState = MutableStateFlow(SignUpState())
        private set

    init {


    }


    fun onEvent(event: SignUpEvent) {
        viewModelScope.launch {
            when (event) {
                SignUpEvent.NavigateToTask -> navigator.navigate(Destinations.Task.route)
                is SignUpEvent.OnEmailTextChange -> updateEmail(event)
                is SignUpEvent.OnPasswordTextChange -> updatePassword(event)
                SignUpEvent.OnPasswordVisibilityChange -> updatePasswordVisibility()
                is SignUpEvent.OnUserNameTextChange -> updateUserName(event)
                SignUpEvent.SignUp -> signUp()
            }
        }
    }

    private suspend fun signUp() {
        useCases.signUpUseCase.invoke(
            email = uiState.value.email,
            password = uiState.value.password,
            userName = uiState.value.userName
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
                    if(networkResult.data?.response != null) {
                        useCases.savePasswordUseCase.invoke(password = uiState.value.password)
                        useCases.saveEmailUseCase.invoke(email = uiState.value.email)
                        useCases.saveUserNameUseCase.invoke(userName = uiState.value.userName)
                        useCases.saveTokenUseCase.invoke(token = networkResult.data.response)
                        navigator.navigate(Destinations.Task.route){
                            popUpTo(Destinations.Login.route){
                                inclusive = true
                            }
                        }
                    }else{
                        toaster.error(networkResult.message?: "Something Went Wrong")
                    }

                }

            }
        }
    }


    private fun updateLoading(isLoading: Boolean) {
        uiState.update { loginState -> loginState.copy(isLoadingState = isLoading) }
    }


    private fun updatePasswordVisibility() {
        uiState.update { loginState -> loginState.copy(isPasswordVisible = !loginState.isPasswordVisible) }
    }

    private fun updatePassword(event: SignUpEvent.OnPasswordTextChange) {
        uiState.update { loginState -> loginState.copy(password = event.text) }
    }

    private fun updateEmail(event: SignUpEvent.OnEmailTextChange) {
        uiState.update { loginState -> loginState.copy(email = event.text.take(40)) }
    }

    private fun updateUserName(event: SignUpEvent.OnUserNameTextChange) {
        uiState.update { loginState -> loginState.copy(userName = event.text.take(40)) }
    }
}