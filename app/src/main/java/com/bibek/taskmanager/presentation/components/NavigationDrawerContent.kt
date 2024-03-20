package com.bibek.taskmanager.presentation.components

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.bibek.taskmanager.CameraActivity
import com.bibek.taskmanager.data.task.TaskStatus
import com.bibek.taskmanager.presentation.screen.task.TaskEvent
import com.bibek.taskmanager.ui.theme.Done
import com.bibek.taskmanager.ui.theme.InProgress
import com.bibek.taskmanager.ui.theme.ToDo
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawerContent(
    name :String? = null,
    email : String? = null,
    profileImageUrl: Uri? = null,
    isProfileLoading: Boolean = false,
    selectedState: String = TaskStatus.ToDo.name,
    onEvent: (TaskEvent) -> Unit,
    onFilterClick : ()-> Unit

) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val profilePicture =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.data != null && result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.extras?.getParcelable<Uri?>("photoUri")
                uri?.let { uri ->
                    scope.launch {
                        onEvent(TaskEvent.UploadPhoto(context = context, uri))
                    }

                }
            }
        }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .bounceClick {
                        profilePicture.launch(Intent(context, CameraActivity::class.java))
                    }
                    .size(100.dp)
                    .background(color = Color.Black, shape = CircleShape),
                contentAlignment = Alignment.Center

            ) {

                val painter = rememberAsyncImagePainter(profileImageUrl)

                if (isProfileLoading ) {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp))
                } else {
                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .fillMaxSize()
                            .background(color = Color.Black, shape = CircleShape),
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }

            NavigationDrawerItem(label = {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    Text(text = name?: "" , fontSize = MaterialTheme.typography.bodyLarge.fontSize )
                }
            }, onClick = {
            }, selected = false)

            NavigationDrawerItem(label = {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    Text(text = email?: "" )
                }
            }, onClick = {
            }, selected = false)

        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                TaskStatus(
                    modifier = Modifier.fillMaxWidth(),
                    title = "All",
                    color = if (selectedState == TaskStatus.All.name) Color.White else Color.Gray
                ) {
                    onFilterClick.invoke()
                    onEvent(TaskEvent.OnTaskStatusClick( TaskStatus.All.name))
                }
                Spacer(modifier = Modifier.height(10.dp))

                TaskStatus(
                    modifier = Modifier.fillMaxWidth(),

                    title = "To Do",
                    color = if (selectedState == TaskStatus.ToDo.name) ToDo else Color.Gray
                ) {
                    onFilterClick.invoke()
                    onEvent(TaskEvent.OnTaskStatusClick( TaskStatus.ToDo.name))
                }
                Spacer(modifier = Modifier.height(10.dp))

                TaskStatus(
                    modifier = Modifier.fillMaxWidth(),

                    title = "In Progress",
                    color = if (selectedState == TaskStatus.InProgress.name) InProgress else Color.Gray
                ) {
                    onFilterClick.invoke()
                    onEvent(TaskEvent.OnTaskStatusClick( TaskStatus.InProgress.name))
                }
                Spacer(modifier = Modifier.height(10.dp))

                TaskStatus(
                    modifier = Modifier.fillMaxWidth(),

                    title = "Done",
                    color = if (selectedState == TaskStatus.Done.name) Done else Color.Gray
                ) {
                    onFilterClick.invoke()
                    onEvent(TaskEvent.OnTaskStatusClick( TaskStatus.Done.name))
                }
                Spacer(modifier = Modifier.height(10.dp))

            }

            NavigationDrawerItem(label = {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Sign Out")
                }
            }, onClick = {
                onEvent(TaskEvent.OnSignOutClick)
            }, selected = false)

            NavigationDrawerItem(label = {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "version 0.0.1")
                }
            }, onClick = {
            }, selected = false)
        }


    }
}