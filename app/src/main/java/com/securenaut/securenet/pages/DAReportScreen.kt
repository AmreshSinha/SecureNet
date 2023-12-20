package com.securenaut.securenet.pages

import AppBar
import TabsBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.securenaut.securenet.components.BottomDABar
import com.securenaut.securenet.components.DALayout.Base
import com.securenaut.securenet.components.DALayout.GeoLocation
import com.securenaut.securenet.components.DALayout.Organisation
import com.securenaut.securenet.components.DALayout.Threat
import com.securenaut.securenet.components.DropDownBar
import com.securenaut.securenet.data.IPData
import com.securenaut.securenet.components.GenAI
import com.securenaut.securenet.ui.theme.Typography
import com.securenaut.securenet.ui.theme.darkBlue

@Composable
fun DAReportScreen(navController: NavController, ipData: IPData) {
    var tabIndex by remember { mutableStateOf(3) }
    val tabs = listOf("Threat", "Organisation", "GeoLocation")

        Column(
            modifier = Modifier
                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
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
                            .padding(vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (tabIndex != 3) {
                                Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
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
                    }
                    if (tabIndex == 3) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            DropDownBar(ipData)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Summary for the IP/Domain", style = Typography.bodyMedium)
                            TextButton(
                                onClick = { tabIndex = 0 },
                                colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary)
                            ) {
                                Text(text = "See More", style = Typography.bodyMedium, color = darkBlue)
                            }
                        }
                    }
                    when (tabIndex) {
                        0 -> Threat()
                        1 -> Organisation()
                        2 -> GeoLocation()
                        3 -> Base(tabIndex, ipData)
                    }

                }
            }
    }
}