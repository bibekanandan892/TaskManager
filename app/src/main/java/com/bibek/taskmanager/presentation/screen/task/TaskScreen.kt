package com.bibek.taskmanager.presentation.screen.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bibek.taskmanager.presentation.components.DiaryHolder
import com.bibek.taskmanager.presentation.components.NavigationDrawerContent
import com.bibek.taskmanager.presentation.components.bounceClick
import com.bibek.taskmanager.utils.hdp
import com.bibek.taskmanager.utils.wdp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(uiState: TaskState, onEvent: (TaskEvent) -> Unit) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
            onEvent(TaskEvent.GetUser(context))


    }

    ModalNavigationDrawer(

        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                content = {
                    NavigationDrawerContent(
                        isProfileLoading = uiState.isProfileLoading,
                        onEvent = onEvent,
                        selectedState = uiState.filterType,
                        onFilterClick = {
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        profileImageUrl = uiState.profileImageUrl,
                        name = uiState.userName,
                        email = uiState.email
                    )
                }
            )
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
            Icon(
                imageVector = Icons.Default.Add,
                tint = MaterialTheme.colorScheme.background,
                contentDescription = null,
                modifier = Modifier
                    .bounceClick { onEvent(TaskEvent.CreateTaskClick) }
                    .padding(10.dp)
                    .size(60.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(20.dp)
            )
        }, topBar = {
            TopAppBar(
                title = { SearchBar(
                    query = uiState.query,
                    active = uiState.isSearchBarActive,
                    onSearch = {onEvent(TaskEvent.OnSearch(it))},
                    onQueryChange = {onEvent(TaskEvent.OnQueryChange(it))},
                    onActiveChange = {onEvent(TaskEvent.OnActiveChange)},
                    placeholder = { Text(text = "Tasks") },
                    leadingIcon = {Icon(
                        imageVector = Icons.Default.Search,
                        modifier = Modifier.padding(10.dp),
                        contentDescription = null
                    )}, trailingIcon = {
                        if(uiState.isSearchBarActive){
                            Icon(
                                imageVector = Icons.Default.Clear,
                                modifier = Modifier.padding(10.dp),
                                contentDescription = null
                            )
                        }

                    }) {

                } },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        modifier = Modifier
                            .bounceClick {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                            .padding(10.dp)
                            .size(30.dp),
                        contentDescription = null
                    )
                },
                actions = {



                })
        }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = 0.03f.wdp(), vertical = 0.03f.hdp()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (uiState.tasks.isNotEmpty()) {
                    LazyColumn {
                        items(uiState.tasks) {
                            Spacer(modifier = Modifier.height(10.dp))
                            DiaryHolder(task = it){
                                onEvent(TaskEvent.OnTaskClick(it))
                            }
                        }
                    }
                }
                if (uiState.isLoading) {
                    Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        CircularProgressIndicator()
                    }
                }

            }

        }
    }
}

