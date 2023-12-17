package com.securenaut.securenet.pages

import AppBar
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.securenaut.securenet.R
import com.securenaut.securenet.components.HomeAppBar
import com.securenaut.securenet.ui.theme.Typography
import com.securenaut.securenet.ui.theme.White
import com.securenaut.securenet.ui.theme.lightOnSurface
import com.securenaut.securenet.ui.theme.textGray
import android.provider.Settings.*
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.content.ContextCompat.startActivity




@Composable
fun SettingsScreen(navController: NavHostController,activity: ComponentActivity) {

    fun openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", activity.packageName, null)
        intent.data = uri

        try {
            activity.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // Handle the exception gracefully (e.g., show a Toast or Snackbar)
            Toast.makeText(activity, "Settings not available", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            AppBar(navController = navController, name = "Settings",onBackScreen="home")
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
                .padding(horizontal = 16.dp)
        ) {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Row(
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(26.dp, 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.info),
                            contentDescription = "info",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Column (modifier=Modifier.padding(start = 20.dp)){
                        Text(text = "About Us", style = Typography.headlineMedium)
                        Text(text = "Version 1.0", style = Typography.bodyMedium, color= textGray)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Permissions", style = Typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(all=16.dp),
                ) {
                    Row {
                        Text(
                            text = "Grant Storage Access, can help you find Unnecessary files of your deviice",
                            style = Typography.bodyMedium,
                            color = lightOnSurface
                        )
                    }
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                         Text(
                             text = "Storage Access",
                             style = Typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                         )
                        Button(onClick = { // Open app settings for Storage Access
                            openAppSettings() }) {
                            Text(
                                text = "Enable",
                                style = Typography.bodyMedium,
                                color = White
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(all=16.dp),
                ) {
                    Row {
                        Text(
                            text = "Grant usage access, can help you find unused apps",
                            style = Typography.bodyMedium,
                            color = lightOnSurface
                        )
                    }
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "Usage Data Access",
                            style = Typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        OutlinedButton(onClick = { openAppSettings() }) {
                            Text(
                                text = "Disable",
                                style = Typography.bodyMedium,
                            )
                        }
                    }
                }
            }
        }
    }


}