package com.bibek.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.bibek.taskmanager.presentation.navigation.graph.Navigator
import com.bibek.taskmanager.presentation.navigation.graph.SetUpNavGraph
import com.bibek.taskmanager.ui.theme.TaskManagerTheme
import com.bibek.taskmanager.utils.DispatcherProvider
import com.bibek.taskmanager.utils.Toaster
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.MessageBarPosition
import com.stevdzasan.messagebar.rememberMessageBarState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var toaster: Toaster



    @Inject
    lateinit var dispatcherProvider: DispatcherProvider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainViewModel : MainViewModel = hiltViewModel()
            val navHostController = rememberNavController()
            val messageBar = rememberMessageBarState()

            TaskManagerTheme(dynamicColor = false) {
                // A surface container using the 'background' color from the theme
                LaunchedEffect(Unit) {
                    navigator.actions.collectLatest { action ->
                        when (action) {
                            Navigator.Action.Back -> navHostController.popBackStack()
                            is Navigator.Action.Navigate -> navHostController.navigate(
                                route = action.destination,
                                builder = action.navOptions
                            )
                        }
                    }
                }

                // Observes error messages and displays them using a custom message bar UI component
                LaunchedEffect(key1 = true) {
                    withContext(dispatcherProvider.main()) {
                        toaster.errorFlow.collect {
                            messageBar.addError(it)
                        }
                    }
                }

                // Observes success messages and displays them using a custom message bar UI component
                LaunchedEffect(key1 = true) {
                    withContext(dispatcherProvider.main()) {
                        toaster.successFlow.collect {
                            messageBar.addSuccess(it)
                        }
                    }
                }

                ContentWithMessageBar(
                    messageBarState = messageBar,
                    successContainerColor = MaterialTheme.colorScheme.primary,
                    successContentColor = Color.White,
                    errorContainerColor = Color.Red,
                    errorContentColor = Color.White,
                    isEnableCopy = false,
                    lottieResource = R.raw.kotak_loading, position = MessageBarPosition.TOP
                ) {
                    SetUpNavGraph(navController = navHostController)
                }
            }
        }
    }
}





