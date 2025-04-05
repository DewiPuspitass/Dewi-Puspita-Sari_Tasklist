package com.dewipuspitasari0020.tasklist.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel : ViewModel(){
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.asStateFlow()

    var title by mutableStateOf("")
    var titleError by mutableStateOf(false)

    var description by mutableStateOf("")
    var descriptionError by mutableStateOf(false)

    var selectedDate by mutableStateOf("")
    var dateError by mutableStateOf(false)

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

    fun clearForm() {
        title = ""
        description = ""
        selectedDate = ""
        titleError = false
        descriptionError = false
        dateError = false
    }
}