package com.securenaut.securenet.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.securenaut.securenet.components.HomeAppBar
import com.securenaut.securenet.components.HomeScanCard
import com.securenaut.securenet.ui.theme.SecureNetTheme

@Composable
fun HomeActivity(navController: NavHostController) {
    Scaffold(
        topBar = {
            HomeAppBar(navController)
        }
    ) { contentPadding ->
        // Screen content
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
        ) {
            HomeScanCard(navController = navController)
            HomeScanCard(navController = navController)
            HomeScanCard(navController = navController)
            HomeScanCard(navController = navController)
            HomeScanCard(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    SecureNetTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                }) {
                    Text(text = "Apps Static List")
                }
            }
        }
    }
}