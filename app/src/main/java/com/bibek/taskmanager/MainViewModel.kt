package com.bibek.taskmanager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bibek.taskmanager.domain.usecase.UseCases
import com.bibek.taskmanager.presentation.navigation.graph.Navigator
import com.bibek.taskmanager.presentation.navigation.routes.Destinations
import com.bibek.taskmanager.utils.Toaster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel  @Inject constructor(
    private val useCases: UseCases,
    private val navigator: Navigator,
    private val toaster: Toaster
) : ViewModel() {

    init {
        viewModelScope.launch {

            val token= useCases.getTokenUseCase.invoke().first()
            if(!token.isNullOrEmpty()){
                navigator.navigate( Destinations.Task.route)
            }
        }

    }


}