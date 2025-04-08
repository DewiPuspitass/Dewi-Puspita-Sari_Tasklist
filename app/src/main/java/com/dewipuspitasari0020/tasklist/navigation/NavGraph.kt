package com.dewipuspitasari0020.tasklist.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dewipuspitasari0020.tasklist.SplashScreen1
import com.dewipuspitasari0020.tasklist.model.TaskViewModel
import com.dewipuspitasari0020.tasklist.ui.screen.AddTaskScreen
import com.dewipuspitasari0020.tasklist.ui.screen.AllTaskScreen
import com.dewipuspitasari0020.tasklist.ui.screen.MainScreen
import com.dewipuspitasari0020.tasklist.ui.screen.ProfileScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    val taskViewModel = viewModel<TaskViewModel>()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ){
        composable(route = Screen.SplashScreen.route) {
            SplashScreen1(navController = navController)
        }
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