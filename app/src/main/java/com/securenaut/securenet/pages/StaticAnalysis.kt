package com.securenaut.securenet.pages

import AppBar
import Dropdown
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.securenaut.securenet.R
import com.securenaut.securenet.components.BottomAppBar
import com.securenaut.securenet.components.HomeAppBar
import com.securenaut.securenet.components.PieChart
import com.securenaut.securenet.components.PieChartEntry
import com.securenaut.securenet.components.PieChartLabel
import com.securenaut.securenet.components.SecurityScore
import com.securenaut.securenet.components.SmallElevatedCard

@Composable
fun StaticAnalysisScreen(navController: NavController, app: String) {
    Scaffold(
        topBar = {
            AppBar(navController, name = "Static Analysis")
        },
        bottomBar = {
            BottomAppBar()
        }

    ) { contentPadding -> Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(contentPadding)
        .fillMaxWidth()
        .padding(horizontal = 16.dp)){

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4",
                placeholder = painterResource(id = R.drawable.icon),
                error = painterResource(id = R.drawable.icon),
                contentDescription = "The delasign logo",
            )
            Text(
                text = app,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge

            )
            Text(
                text = "Last scanned on 20 Feb 2023",
                textAlign = TextAlign.Center,
                color = Color.DarkGray ,
                style = MaterialTheme.typography.titleSmall
            )
        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)

        ){
            Row(modifier = Modifier.padding(16.dp)) {
                Column (modifier = Modifier
                    .fillMaxWidth(fraction = 0.5.toFloat())
                    .padding(16.dp) ){
                    Text(
                        text = "Security Score",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Write a description for what the security score means or how it is calculated.",
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                SecurityScore(34)
            }
        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)

        ){
            Column (modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Severity Distribution (%)",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                )
                Column (modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp), horizontalAlignment = Alignment.CenterHorizontally){
                    val entries = listOf(
                        PieChartEntry(Color(0xFFFFC107), 0.4f),
                        PieChartEntry(Color(0xFFF44336), 0.25f),
                        PieChartEntry(Color(0xFF0F9D58), 0.25f),
                        PieChartEntry(Color(0xFF2196F3), 0.1f)
                    )
                    PieChart(entries)
                }
                PieChartLabel()

            }
        }

        Row {
            SmallElevatedCard(iconImage = R.drawable.mobile, heading = "Privacy Risk", value = "0", width = 0.5f)
            SmallElevatedCard(iconImage = R.drawable.dollar ,heading = "Risk Rating", value = "A", width = 1.0f)
        }

        Column {
            Dropdown(type = "medium", title = "Certificate", subtitle = "This application has no privacy trackers", description = "Application vulnerable to Janus VulnerabilityApplication vulnerable to Janus VulnerabilityApplication vulnerable to Janus Vulnerability")
            Dropdown(type = "secure", title = "Trackers", subtitle = "This application has no privacy trackers", description = "Application vulnerable to Janus VulnerabilityApplication vulnerable to Janus VulnerabilityApplication vulnerable to Janus Vulnerability")
            Dropdown(type = "info", title = "Manifest", subtitle = "This application has no privacy trackers", description = "Application vulnerable to Janus VulnerabilityApplication vulnerable to Janus VulnerabilityApplication vulnerable to Janus Vulnerability")
            Dropdown(type = "high", title = "Secrets", subtitle = "This application has no privacy trackers", description = "Application vulnerable to Janus VulnerabilityApplication vulnerable to Janus VulnerabilityApplication vulnerable to Janus Vulnerability")
        }



    }
}
}


