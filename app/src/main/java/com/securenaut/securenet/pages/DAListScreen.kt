package com.securenaut.securenet.pages

import AppBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.LatLng
import com.securenaut.securenet.R
import com.securenaut.securenet.components.DAAppCard
import com.securenaut.securenet.components.DAScanCard
import com.securenaut.securenet.components.HomeScanCard
import com.securenaut.securenet.ui.theme.Typography
import com.securenaut.securenet.ui.theme.textGray
import kotlinx.coroutines.delay


@Composable
fun DAListScreen(navController: NavHostController, vpnButton: @Composable () -> Unit) {
    val dynamicPosition = LatLng(40.7128, -74.0060)

    // Dummy array of app names
    val appNames = listOf("Instagram", "Facebook", "Twitter", "WhatsApp", "Snapchat")

    var isLoading by remember { mutableStateOf(true) }
    var scannedApp by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    // Coroutine to simulate scanning effect
    LaunchedEffect(isLoading) {
        if (isLoading) {
            for (appName in appNames) {
                delay(1000) // Simulating the delay during scanning
                scannedApp = appName
            }
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            AppBar(navController = navController, name = "Dynamic Analysis", onBackScreen = "home")
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
                .padding(horizontal = 16.dp)
                .fillMaxHeight()
        ) {
            DAScanCard(navController = navController, vpnButton)
            if (isLoading) {
                Row(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = scannedApp ?: "", style = Typography.bodySmall, color= textGray)
                }
            } else {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Threats detected in", style = Typography.bodyMedium)
                        Image(
                            painter = painterResource(id = R.drawable.arrowdown),
                            contentDescription = "Down Arrow"
                        )
                    }
                    DAAppCard(appName = "Instagram", lastScan = "18th Dec 2023")
                    DAAppCard(appName = "Instagram", lastScan = "18th Dec 2023")
                    DAAppCard(appName = "Instagram", lastScan = "18th Dec 2023")
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Safe Apps", style = Typography.bodyMedium)
                        Image(
                            painter = painterResource(id = R.drawable.arrowdown),
                            contentDescription = "Down Arrow"
                        )
                    }
                    DAAppCard(appName = "Instagram", lastScan = "18th Dec 2023")
                    DAAppCard(appName = "Instagram", lastScan = "18th Dec 2023")
                    DAAppCard(appName = "Instagram", lastScan = "18th Dec 2023")
                }
            }
        }
    }
}
