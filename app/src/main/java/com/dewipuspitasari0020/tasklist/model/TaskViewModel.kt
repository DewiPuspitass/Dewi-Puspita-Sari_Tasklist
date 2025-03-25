package com.dewipuspitasari0020.tasklist.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel(){
    private val _tasks = mutableStateListOf<Task>()
    val tasks: List<Task> get() = _tasks

    fun addTask(task: Task) {
        _tasks.add(task)
    }

    fun removeTask(task: Task) {
        _tasks.remove(task)
    }
}