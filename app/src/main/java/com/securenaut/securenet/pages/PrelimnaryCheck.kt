package com.securenaut.securenet.pages

import AppBar
import Dropdown
import android.util.Log
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.securenaut.securenet.R
import com.securenaut.securenet.components.BottomAppBar
import com.securenaut.securenet.components.PieChart
import com.securenaut.securenet.components.PieChartEntry
import com.securenaut.securenet.components.PieChartLabel
import com.securenaut.securenet.components.PrelimnaryBottomAppBar
import com.securenaut.securenet.components.SecurityScore
import com.securenaut.securenet.components.SmallElevatedCard
import com.securenaut.securenet.data.GlobalStaticClass

@Composable
fun PrelimnaryCheck (navController: NavController){
    var playstoreInstalled: Boolean = GlobalStaticClass.srcDir.contains("com.android.vending")
//
//    var normalPermissions = GlobalStaticClass.normalPermissions.intersect(GlobalStaticClass.appPermissions.toSet())

    var dangerousPermissions = GlobalStaticClass.dangerousPermissions.intersect(GlobalStaticClass.appPermissions.toSet())

    var signaturePermissions = GlobalStaticClass.signaturePermissions.intersect(GlobalStaticClass.appPermissions.toSet())

    Log.i("dangerousPermissions",dangerousPermissions.toString())

    Log.i("signaturePermissions",signaturePermissions.toString())

    Scaffold(
        topBar = {
            AppBar(navController, name = "Prelimnary App Check", onBackScreen = "staticAnalysisAppList")
        },
        bottomBar = {
            PrelimnaryBottomAppBar(navController = navController)
        }
    ){ contentPadding -> Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(contentPadding)
        .padding(horizontal = 16.dp)){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberImagePainter(data = GlobalStaticClass.appIconDrawable),
                contentDescription = "App Icon",
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = GlobalStaticClass.appName,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
//            Text(
//                text = "Last scanned on 20 Feb 2023",
//                textAlign = TextAlign.Center,
//                color = Color.DarkGray ,
//                style = MaterialTheme.typography.titleSmall
//            )
        }
//
//        ElevatedCard(
//            elevation = CardDefaults.cardElevation(
//                defaultElevation = 6.dp
//            ),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 32.dp)
//
//        ){
//            Row(modifier = Modifier.padding(16.dp)) {
//                Column (modifier = Modifier
//                    .fillMaxWidth(fraction = 0.5.toFloat())
//                    .padding(16.dp) ){
//                    Text(
//                        text = "Security Score",
//                        style = MaterialTheme.typography.titleLarge
//                    )
//                    Text(
//                        text = appSecurityDescription,
//                        color = Color.DarkGray,
//                        style = MaterialTheme.typography.titleSmall
//                    )
//                }
//                SecurityScore(appsec["security_score"] as Int)
//            }
//        }
//
//        ElevatedCard(
//            elevation = CardDefaults.cardElevation(
//                defaultElevation = 6.dp
//            ),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 16.dp)
//
//        ){
//            Column (modifier = Modifier.padding(16.dp)) {
//                Text(
//                    text = "Severity Distribution (%)",
//                    style = MaterialTheme.typography.titleLarge,
//                    textAlign = TextAlign.Center,
//                )
//                Column (modifier = Modifier
//                    .fillMaxSize()
//                    .padding(top = 16.dp), horizontalAlignment = Alignment.CenterHorizontally){
//                    val entries = listOf(
//                        PieChartEntry(Color(0xFFFFC107), highSeverityProportion),
//                        PieChartEntry(Color(0xFFF44336), warningSeverityProportion),
//                        PieChartEntry(Color(0xFF0F9D58), infoSeverityProportion),
//                        PieChartEntry(Color(0xFF2196F3), secureSeverityProportion)
//                    )
//                    PieChart(entries)
//                }
//                PieChartLabel()
//            }
//        }
//
//        Row {
//            SmallElevatedCard(iconImage = R.drawable.mobile, heading = "Privacy Risk", value = appsec["trackers"].toString(), width = 0.5f)
//            SmallElevatedCard(iconImage = R.drawable.dollar ,heading = "Risk Rating", value = riskRating, width = 1.0f)
//        }
//
//        ManifestDropdown(manifestAnalysis = GlobalStaticClass.staticAnalysisReport.getJSONObject("manifest_analysis"))
//        CertificateDropdown(certificateAnalysis = GlobalStaticClass.staticAnalysisReport.getJSONObject("certificate_analysis"))
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = "Suspicious Permissions",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        for(element in dangerousPermissions){

            GlobalStaticClass.permissionsMap[element]?.get("permission_name")
                ?.let { GlobalStaticClass.permissionsMap[element]?.get("permission_descp")
                    ?.let { it1 -> Dropdown(type = "Normal Permissions", title = it, subtitle = element, description = it1)
                        Spacer(modifier = Modifier.height(16.dp))
                    } }
        }

        Text(
            text = "Signature Permissions",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )


        for(element in signaturePermissions){

            GlobalStaticClass.permissionsMap[element]?.get("permission_name")
                ?.let { GlobalStaticClass.permissionsMap[element]?.get("permission_descp")
                    ?.let { it1 -> Dropdown(type = "Normal Permissions", title = it, subtitle = it1, description = element)
                        Spacer(modifier = Modifier.height(16.dp))
                    } }
        }
        
    }
    }
}




