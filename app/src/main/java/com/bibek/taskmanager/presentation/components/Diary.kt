package com.bibek.taskmanager.presentation.components


import java.time.Instant

data class Diary (
    var _id: String = "",
    var ownerId: String = "qwer",
    var mood: String ="",
    var title: String = "qwer",
    var description: String = "qwer",
    var images: List<String> = listOf(),
    var date: Instant = Instant.now(),
)