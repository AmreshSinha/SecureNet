package com.securenaut.securenet.pages

import AppBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.securenaut.securenet.R
import com.securenaut.securenet.components.BottomDABar
import com.securenaut.securenet.components.DALayout.Base
import com.securenaut.securenet.components.DropDownBar
import com.securenaut.securenet.ui.theme.Typography

@Composable
fun DAReportScreen(navController: NavController) {
    Scaffold(topBar = {
        AppBar(navController = navController, name = "Dynamic Analysis", onBackScreen = "home")
    },
        bottomBar = {
            BottomDABar()
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 40.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.icon),
                                contentDescription = "Icon Image"
                            )
                            Text(
                                text = "Instagram",
                                style = Typography.headlineMedium
                            )
                        }
                    }
                    Base()
                }
            }
        }
    }
}