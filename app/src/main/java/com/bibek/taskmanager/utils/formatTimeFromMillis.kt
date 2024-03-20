package com.bibek.taskmanager.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.util.Base64
import androidx.compose.ui.graphics.Color
import com.bibek.taskmanager.ui.theme.Done
import com.bibek.taskmanager.ui.theme.InProgress
import com.bibek.taskmanager.ui.theme.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun formatTimeFromMillis(timeMillis: Long? = System.currentTimeMillis()): String {
    val sdf = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault())
    val calendar = Calendar.getInstance()
    if (timeMillis != null) {
        calendar.timeInMillis = timeMillis
        return sdf.format(calendar.time)
    }
    return "NA"

}

fun getColor(status: Int): Color {
    return when(status){
        1-> ToDo
        2-> InProgress
        3 -> Done
        else -> ToDo
    }
}


suspend fun base64ToUri(context: Context, base64String: String): Uri? {
    return withContext(Dispatchers.IO) {
        var outputStream: OutputStream? = null
        try {
            // Decode the Base64 string to a byte array
            val decodedBytes = android.util.Base64.decode(base64String, android.util.Base64.DEFAULT)
            // Create a temporary file to store the decoded image
            val tempFile = File.createTempFile("temp_image", null, context.cacheDir)
            // Write the byte array to the file
            outputStream = FileOutputStream(tempFile)
            outputStream.write(decodedBytes)
            // Return the URI of the temporary file
            Uri.fromFile(tempFile)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            outputStream?.close()
        }
    }
}


suspend fun uriToBase64(contentResolver: ContentResolver, uri: Uri): String? {
    return withContext(Dispatchers.IO) {
        var inputStream: InputStream? = null
        var byteArrayOutputStream: ByteArrayOutputStream? = null
        try {
            inputStream = contentResolver.openInputStream(uri)
            byteArrayOutputStream = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var bytesRead: Int
            while (inputStream!!.read(buffer).also { bytesRead = it } != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead)
            }
            val byteArray = byteArrayOutputStream.toByteArray()
            Base64.encodeToString(byteArray, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            inputStream?.close()
            byteArrayOutputStream?.close()
        }
    }
}