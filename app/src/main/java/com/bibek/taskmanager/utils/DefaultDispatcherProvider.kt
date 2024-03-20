package com.bibek.taskmanager.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultDispatcherProvider : DispatcherProvider {
    override fun main(): CoroutineDispatcher = Dispatchers.Main

    override fun default(): CoroutineDispatcher  = Dispatchers.Default

    override fun io(): CoroutineDispatcher =Dispatchers.IO
}
