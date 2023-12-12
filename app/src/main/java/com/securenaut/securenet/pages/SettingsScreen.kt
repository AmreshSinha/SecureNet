package com.securenaut.securenet.pages

import androidx.compose.foundation.Image
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.securenaut.securenet.R
import com.securenaut.securenet.components.HomeAppBar
import com.securenaut.securenet.ui.theme.Typography


@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = {
            HomeAppBar()
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
        ) {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row {
                    Column {
                       Image(painter = painterResource(id = R.drawable.info), contentDescription = "info",
                       modifier= Modifier.size(30.dp)
                       )
                    }
                    Column {
                        Text(text = "About Us", style = Typography.headlineMedium)
                        Text(text = "Version 1.0", style = Typography.bodyMedium)
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "")
        }
    }
}