package com.dewipuspitasari0020.tasklist.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dewipuspitasari0020.tasklist.R
import com.dewipuspitasari0020.tasklist.ui.theme.TasklistTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.profile), textAlign = TextAlign.Center)
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.fotoprofile),
                contentDescription = stringResource(R.string.photo_profile),
                modifier = Modifier
                    .size(132.dp)
                    .clip(CircleShape),
            )
            Row (
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 18.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.inverseSurface, shape = RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(16.dp)),
            ){
                Column (
                    modifier = Modifier.padding(16.dp)
                ){
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = stringResource(R.string.add_task),
                        tint = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = stringResource(R.string.developer_name), color = MaterialTheme.colorScheme.inverseOnSurface)
                }
            }
            Row (
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.inverseSurface, shape = RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(16.dp)),
            ){
                Column (
                    modifier = Modifier.padding(16.dp)
                ){
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = stringResource(R.string.add_task),
                        tint = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = stringResource(R.string.developer_nim), color = MaterialTheme.colorScheme.inverseOnSurface)
                }
            }
            Row (
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.inverseSurface, shape = RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(16.dp)),
            ){
                Column (
                    modifier = Modifier.padding(16.dp)
                ){
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = stringResource(R.string.add_task),
                        tint = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = stringResource(R.string.developer_class), color = MaterialTheme.colorScheme.inverseOnSurface)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewProfileScreen() {
    TasklistTheme {
        ProfileScreen(rememberNavController())
    }
}