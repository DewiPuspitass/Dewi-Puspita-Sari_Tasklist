package com.dewipuspitasari0020.tasklist.navigation

sealed class Screen(val route: String) {
    data object SplashScreen: Screen("splashScreen")
    data object Home: Screen("mainScreen")
    data object Profile: Screen("profileScreen")
    data object AddTask: Screen("addTaskScreen")
    data object AllTask: Screen("allTaskScreen")
}