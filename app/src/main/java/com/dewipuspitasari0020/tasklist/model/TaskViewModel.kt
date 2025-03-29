package com.dewipuspitasari0020.tasklist.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel : ViewModel(){
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
//    val tasks: StateFlow<List<Task>> get() = _tasks.asStateFlow()
    val tasks = _tasks.asStateFlow()

    fun addTask(task: Task) {
        _tasks.value += task
        println("DEBUG: Task added - ${task.title} ${task.description} ${task.date}")
    }

    fun removeTask(task: Task) {
        _tasks.value -= task
    }
}