package com.dewipuspitasari0020.tasklist.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel : ViewModel(){
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.asStateFlow()

    fun addTask(task: Task) {
        _tasks.value += task
        println("DEBUG: Task added - ${task.title} ${task.description} ${task.date}")
    }

    fun doneTask(task: Task) {
        _tasks.value = _tasks.value.map {
            if (it.id == task.id) it.copy(status = "inactive") else it
        }
        println("DEBUG: Task ${task.title} status updated to inactive")
    }
}