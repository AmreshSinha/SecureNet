package com.securenaut.securenet.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.securenaut.securenet.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(navController: NavHostController){
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Button"
                )
            }
        },
        title = {
            Surface(modifier = Modifier.fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.Center,modifier = Modifier.background(color = Color.White)) {
                    Text(
                        "SecureNet",
                        style = Typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
        actions = {
            IconButton(onClick = {navController.navigate("settings")}) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Open Settings"
                )
            }
        }
    )
}