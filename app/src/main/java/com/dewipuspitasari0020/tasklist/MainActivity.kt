package com.dewipuspitasari0020.tasklist

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dewipuspitasari0020.tasklist.navigation.Screen
import com.dewipuspitasari0020.tasklist.navigation.SetupNavGraph
import com.dewipuspitasari0020.tasklist.ui.theme.TasklistTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TasklistTheme{
                SetupNavGraph()
            }
        }
    }
}

@Composable
fun SplashScreen1(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(3000L)
        navController.navigate("mainScreen") {
            popUpTo("splashScreen") { inclusive = true }
        }
    }

    Column (
        modifier = Modifier.fillMaxSize().background(color = colorResource(id = R.color.blue_screen)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.splashscreen),
            contentDescription = "Logo",
            modifier = Modifier.width(200.dp).height(200.dp)
        )
    }
}
