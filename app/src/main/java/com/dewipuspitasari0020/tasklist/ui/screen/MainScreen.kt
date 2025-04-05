package com.dewipuspitasari0020.tasklist.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dewipuspitasari0020.tasklist.R
import com.dewipuspitasari0020.tasklist.model.TaskViewModel
import com.dewipuspitasari0020.tasklist.navigation.Screen
import com.dewipuspitasari0020.tasklist.ui.theme.TasklistTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, viewModel: TaskViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column (
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier.padding(top = 10.dp)
                    ){
                        Text(text = stringResource(R.string.welcome_back), fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary)
                        Text(text = stringResource(R.string.developer_name), fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(
                        onClick = { navController.navigate(Screen.Profile.route )},
                        modifier = Modifier.padding(18.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.fotoprofile),
                            contentDescription = "Profile",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                },
                modifier = Modifier.height(100.dp),
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddTask.route)
                },
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.add_task),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ){  innerPadding ->
        ScreenContent(Modifier.padding(innerPadding), viewModel, navController)
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier, taskViewModel: TaskViewModel, navController: NavHostController) {
    val tasks by taskViewModel.tasks.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.inverseOnSurface),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.task),
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.secondary,
            )
            TextButton(onClick = {
                navController.navigate(Screen.AllTask.route)
            }) {
                Text(
                    text = stringResource(R.string.see_all),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        }

        if(tasks.isEmpty() || tasks.none { it.status != "inactive" }){
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Tidak ada Ongoing Task",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }else{
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(tasks.filter { it.status == "active" }) { task ->
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clip(RoundedCornerShape(16.dp))
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = task.title,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Text(
                                text = task.description,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Text(
                                text = "Due date: ${task.date}",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Row {
                                ElevatedButton(
                                    onClick = { taskViewModel.doneTask(task) },
                                    colors = ButtonDefaults.elevatedButtonColors(
                                        containerColor = MaterialTheme.colorScheme.inversePrimary,
                                        contentColor = MaterialTheme.colorScheme.secondary
                                    ),
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = stringResource(R.string.done))
                                }
                                ElevatedButton(
                                    onClick = {
                                        shareData(
                                            context = context,
                                            message = context.getString(R.string.share_template,
                                                task.title, task.description, task.date)
                                        )
                                    },
                                    colors = ButtonDefaults.elevatedButtonColors(
                                        containerColor = MaterialTheme.colorScheme.inversePrimary,
                                        contentColor = MaterialTheme.colorScheme.secondary
                                    )
                                ) {
                                    Text(text = stringResource(R.string.share))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun shareData (context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if(shareIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(shareIntent)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PrevMain() {
    TasklistTheme  {
        MainScreen(rememberNavController(), viewModel = TaskViewModel())
    }
}