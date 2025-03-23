package com.dewipuspitasari0020.tasklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dewipuspitasari0020.tasklist.ui.screen.MainScreen
import com.dewipuspitasari0020.tasklist.ui.theme.TasklistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TasklistTheme {
                MainScreen()
            }
        }
    }
}
