package com.securenaut.securenet.pages

import AppBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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


@Composable
fun DAListScreen(navController: NavHostController) {
    val dynamicPosition = LatLng(40.7128, -74.0060)
    val dummyArray = listOf("Item 1", "Item 2", "Item 3")
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
        ) {
            DAScanCard(navController = navController)
            Column(modifier = Modifier.padding(horizontal=16.dp)){
                Row (verticalAlignment = Alignment.CenterVertically){
                    Text(text = "Threats detected in",style = Typography.bodyMedium)
                    Image(painter = painterResource(id = R.drawable.arrowdown), contentDescription ="Down Arrow" )
                }
                DAAppCard(appName="Instagram",lastScan="18th Dec 2023")
                DAAppCard(appName="Instagram",lastScan="18th Dec 2023")
                DAAppCard(appName="Instagram",lastScan="18th Dec 2023")
                Spacer(modifier = Modifier.height(16.dp))
                    Row (verticalAlignment = Alignment.CenterVertically){
                        Text(text = "Safe Apps", style = Typography.bodyMedium)
                        Image(painter = painterResource(id = R.drawable.arrowdown), contentDescription ="Down Arrow" )
                    }
                    DAAppCard(appName="Instagram",lastScan="18th Dec 2023")
                    DAAppCard(appName="Instagram",lastScan="18th Dec 2023")
                    DAAppCard(appName="Instagram",lastScan="18th Dec 2023")
            }
        }
    }
}
