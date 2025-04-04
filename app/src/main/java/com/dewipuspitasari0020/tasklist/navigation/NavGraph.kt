package com.dewipuspitasari0020.tasklist.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dewipuspitasari0020.tasklist.model.TaskViewModel
import com.dewipuspitasari0020.tasklist.ui.screen.AddTaskScreen
import com.dewipuspitasari0020.tasklist.ui.screen.AllTaskScreen
import com.dewipuspitasari0020.tasklist.ui.screen.MainScreen
import com.dewipuspitasari0020.tasklist.ui.screen.ProfileScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    val taskViewModel = viewModel<TaskViewModel>()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route) {
            MainScreen(navController, viewModel = taskViewModel)
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(route = Screen.AddTask.route) {
            AddTaskScreen(navController, viewModel = taskViewModel)
        }
        composable(route = Screen.AllTask.route) {
            AllTaskScreen(navController, taskViewModel = taskViewModel)
        }
    }
}