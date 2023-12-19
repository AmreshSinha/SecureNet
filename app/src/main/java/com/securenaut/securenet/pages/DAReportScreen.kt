package com.securenaut.securenet.pages

import AppBar
import TabsBar
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
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.securenaut.securenet.R
import com.securenaut.securenet.components.DALayout.Base
import com.securenaut.securenet.components.DALayout.GeoLocation
import com.securenaut.securenet.components.DALayout.Organisation
import com.securenaut.securenet.components.DALayout.Threat
import com.securenaut.securenet.components.DropDownBar
import com.securenaut.securenet.ui.theme.Typography

@Composable
fun DAReportScreen(navController: NavController) {
    var tabIndex by remember { mutableStateOf(3) }
    val tabs = listOf("Threat", "Organisation", "GeoLocation")

    Scaffold(topBar = {
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
                            Row(modifier = Modifier.fillMaxWidth()) {
                                TabRow(selectedTabIndex = tabIndex) {
                                    tabs.forEachIndexed { index, title ->
                                        Tab(text = { Text(title) },
                                            selected = tabIndex == index,
                                            onClick = { tabIndex = index }
                                        )
                                    }
                                }
                            }
                        }
                    }
                    when (tabIndex) {
                        0 -> Threat()
                        1 -> Organisation()
                        2 -> GeoLocation()
                        3 -> Base()
                    }
                }
            }
        }
    }
}