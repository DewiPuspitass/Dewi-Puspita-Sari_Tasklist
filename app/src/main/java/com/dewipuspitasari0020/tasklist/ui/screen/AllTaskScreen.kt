package com.dewipuspitasari0020.tasklist.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dewipuspitasari0020.tasklist.R
import com.dewipuspitasari0020.tasklist.model.TaskViewModel
import com.dewipuspitasari0020.tasklist.ui.theme.TasklistTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllTaskScreen(navController: NavHostController, taskViewModel: TaskViewModel) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.all_task), textAlign = TextAlign.Center)
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                    titleContentColor = MaterialTheme.colorScheme.inverseSurface,
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.inverseSurface
                        )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },

                )
        }
    ) { innerPadding ->
        val tasks by taskViewModel.tasks.collectAsStateWithLifecycle()
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(tasks.isEmpty()){
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
                            text = "Tidak ada Task",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(tasks) { task ->
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
                                    if (task.status == "active") {
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
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewAllTaskScreen() {
    TasklistTheme {
        AllTaskScreen(rememberNavController(), taskViewModel = TaskViewModel())
    }
}