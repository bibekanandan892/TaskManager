package com.bibek.taskmanager.utils

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
@Composable
@Stable
fun Float.wdp() : Dp{
    val context = LocalContext.current
    val density = LocalDensity.current.density
    val screenWidth = context.resources.displayMetrics.widthPixels / density
    val value = if(this > 1f) 1f else this
    return (screenWidth * (value * 100) / 100).dp
}

@Composable
@Stable
fun  Float.hdp() : Dp {
    val context = LocalContext.current
    val density = LocalDensity.current.density
    val screenHeight = context.resources.displayMetrics.heightPixels / density
    val value = if(this > 1f) 1f else this
    return (screenHeight * (value * 100) / 100).dp
}

fun Modifier.resHeight(@FloatRange(from = 0.0, to = 1.0) fraction : Float) = composed {
    val context = LocalContext.current
    val density = LocalDensity.current.density
    val screenHeight = context.resources.displayMetrics.heightPixels / density
    this.height((screenHeight * (fraction*100) / 100).dp)
}
fun Modifier.resWidth(@FloatRange(from = 0.0, to = 1.0) fraction : Float) = composed {
    val context = LocalContext.current
    val density = LocalDensity.current.density
    val screenWidth = context.resources.displayMetrics.widthPixels / density
    this.width((screenWidth * (fraction*100) / 100).dp)
}