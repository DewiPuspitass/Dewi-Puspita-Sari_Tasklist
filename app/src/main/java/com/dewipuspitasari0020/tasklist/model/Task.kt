package com.dewipuspitasari0020.tasklist.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    var status: String
)
