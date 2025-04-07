package com.dewipuspitasari0020.tasklist.ui.screen

import android.app.DatePickerDialog
import android.content.Context
import android.content.res.Configuration
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dewipuspitasari0020.tasklist.R
import com.dewipuspitasari0020.tasklist.model.Task
import com.dewipuspitasari0020.tasklist.model.TaskViewModel
import com.dewipuspitasari0020.tasklist.ui.theme.TasklistTheme
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(navController: NavHostController, viewModel: TaskViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.add_task), textAlign = TextAlign.Center)
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
        AddTask(modifier = Modifier.padding(innerPadding), navController = navController, taskViewModel = viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTask(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel = viewModel(),
    navController: NavController
) {
    val context = LocalContext.current

    val title = taskViewModel.title
    val description = taskViewModel.description

    val titleError = taskViewModel.titleError
    val descriptionError = taskViewModel.descriptionError
    val selectDateError = taskViewModel.dateError

    val tasks by taskViewModel.tasks.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.task_title),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary,
        )
        TextField(
            value = title,
            onValueChange = { taskViewModel.title = it },
            modifier = Modifier.fillMaxWidth(),
            supportingText = { ErrorHint(titleError) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.inversePrimary,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )
        Text(
            text = stringResource(R.string.task_description),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary,
        )
        TextField(
            value = description,
            onValueChange = { taskViewModel.description = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            supportingText = { ErrorHint(descriptionError) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.inversePrimary,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Transparent
            ),
            maxLines = 5,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            )
        )

        DatePicker(context, selectDateError) { date ->
            taskViewModel.selectedDate = date
        }

        Button(
            onClick = {
                taskViewModel.titleError = taskViewModel.title.isBlank()
                taskViewModel.descriptionError = taskViewModel.description.isBlank()
                taskViewModel.dateError = taskViewModel.selectedDate.isBlank()

                if (!taskViewModel.titleError && !taskViewModel.descriptionError && !taskViewModel.dateError) {
                    val newTask = Task(
                        id = tasks.size + 1,
                        title = taskViewModel.title,
                        description = taskViewModel.description,
                        date = taskViewModel.selectedDate,
                        status = "active"
                    )
                    taskViewModel.addTask(newTask)
                    taskViewModel.clearForm()
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
        ) {
            Text(text = stringResource(R.string.create_new_task))
        }
    }
}

@Composable
fun DatePicker(context: Context, dateError: Boolean, onDateSelected: (String) -> Unit) {
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/${selectedMonth + 1}/$selectedYear"
            onDateSelected(date.value)
        }, year, month, day
    )
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Box(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = stringResource(R.string.add_task),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        Column {
            Button(
                onClick = { datePickerDialog.show() },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.inversePrimary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(date.value.ifEmpty { stringResource(R.string.select_date) })
            }
        }
        if (dateError) {
            Text(
                text = stringResource(R.string.input_invalid),
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError){
        Text(text= stringResource(R.string.input_invalid))
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PrevHalamanTambah() {
    TasklistTheme {
        AddTaskScreen(rememberNavController(), viewModel = TaskViewModel())
    }
}