package com.securenaut.securenet.pages

import AppBar
import Dropdown
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.securenaut.securenet.R
import com.securenaut.securenet.components.BottomAppBar
import com.securenaut.securenet.components.GenAI
import com.securenaut.securenet.components.HomeAppBar
import com.securenaut.securenet.components.PieChart
import com.securenaut.securenet.components.PieChartEntry
import com.securenaut.securenet.components.PieChartLabel
import com.securenaut.securenet.components.SecurityScore
import com.securenaut.securenet.components.SmallElevatedCard
import com.securenaut.securenet.data.GlobalStaticClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

@Composable
fun StaticAnalysisScreen(navController: NavController) {

    Log.i("INSIDE STATIC ANALYSIS SCREEN","INSIDE THIS SCREEN")

    Log.i("INSIDE STATIC ANALYSIS SCREEN",GlobalStaticClass.staticAnalysisReport.toString())

    val appsec = GlobalStaticClass.staticAnalysisReport["appsec"] as JSONObject;

    val appSecurityDescription : String = when(appsec["security_score"]){
        in 0..29 -> "Caution! The app scored low; consider uninstalling to safeguard your device."
        in 30..39 -> "Proceed with caution! The app's score indicates moderate risk."
        in 40..59 -> "The app looks okay, but keep an eye out for any unusual activity."
        else -> "Green light! The app seems safe. Enjoy your worry-free experience."
    }

    val riskRating : String = when(appsec["security_score"]){
        in 0..29 -> "D"
        in 30..39 -> "C"
        in 40..59 -> "B"
        else -> "A"
    }

    val totalSeverity : Float = ((appsec["high"] as JSONArray).length()
            + (appsec["warning"] as JSONArray).length()
            + (appsec["info"] as JSONArray).length()
            + (appsec["secure"] as JSONArray).length()).toFloat()

    val highSeverityProportion = (appsec["high"] as JSONArray).length()/totalSeverity
    val warningSeverityProportion = (appsec["warning"] as JSONArray).length()/totalSeverity
    val infoSeverityProportion = (appsec["info"] as JSONArray).length()/totalSeverity
    val secureSeverityProportion = (appsec["secure"] as JSONArray).length()/totalSeverity

    Log.i("severity_proportions",highSeverityProportion.toString() + " " + warningSeverityProportion.toString() + " " + infoSeverityProportion.toString() + " " + secureSeverityProportion.toString())

    Scaffold(
        topBar = {
            AppBar(navController, name = "Static Analysis", onBackScreen = "prelimnaryCheck")
        },
        bottomBar = {
            BottomAppBar(context = LocalContext.current)
        }

    ) { contentPadding -> Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(contentPadding)
        .fillMaxWidth()
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
            Text(
                text = "Last scanned on 20 Feb 2023",
                textAlign = TextAlign.Center,
                color = Color.DarkGray ,
                style = MaterialTheme.typography.titleSmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        GenAI()

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
                        text = appSecurityDescription,
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                SecurityScore(appsec["security_score"] as Int)
            }
        }

        Card(
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
                        PieChartEntry(Color(0xFFFFC107), highSeverityProportion),
                        PieChartEntry(Color(0xFFF44336), warningSeverityProportion),
                        PieChartEntry(Color(0xFF0F9D58), infoSeverityProportion),
                        PieChartEntry(Color(0xFF2196F3), secureSeverityProportion)
                    )
                    PieChart(entries)
                }
                PieChartLabel()
            }
        }

        Row {
            SmallElevatedCard(iconImage = R.drawable.mobile, heading = "Privacy Risk", value = appsec["trackers"].toString(), width = 0.5f)
            Spacer(modifier = Modifier.width(16.dp))
            SmallElevatedCard(iconImage = R.drawable.dollar ,heading = "Risk Rating", value = riskRating, width = 1.0f)
        }
        
        ManifestDropdown(manifestAnalysis = GlobalStaticClass.staticAnalysisReport.getJSONObject("manifest_analysis"))
        Spacer(modifier = Modifier.height(16.dp))
        CertificateDropdown(certificateAnalysis = GlobalStaticClass.staticAnalysisReport.getJSONObject("certificate_analysis"))
        Spacer(modifier = Modifier.height(16.dp))
        for(i in 0 until appsec.getJSONArray("high").length()){

            val element = appsec.getJSONArray("high").getJSONObject(i)

            Dropdown(type = "high", title = element.getString("section").toUpperCase(), subtitle = element.getString("title"), description = element.getString("description"))
            Spacer(modifier = Modifier.height(16.dp))
        }

        for(i in 0 until appsec.getJSONArray("warning").length()){

            val element = appsec.getJSONArray("warning").getJSONObject(i)

            Dropdown(type = "warning", title = element.getString("section").toUpperCase(), subtitle = element.getString("title"), description = element.getString("description"))
            Spacer(modifier = Modifier.height(16.dp))
        }

        for(i in 0 until appsec.getJSONArray("info").length()){

            val element = appsec.getJSONArray("info").getJSONObject(i)

            Dropdown(type = "info", title = element.getString("section").toUpperCase(), subtitle = element.getString("title"), description = element.getString("description"))
            Spacer(modifier = Modifier.height(16.dp))
        }

        for(i in 0 until appsec.getJSONArray("secure").length()){

            val element = appsec.getJSONArray("secure").getJSONObject(i)

            Dropdown(type = "secure", title = element.getString("section").toUpperCase(), subtitle = element.getString("title"), description = element.getString("description"))
            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}
}
@SuppressLint("UnrememberedMutableState")
@Composable
fun ManifestDropdown(manifestAnalysis : JSONObject){
    var isClicked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)

    ){
        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { isClicked = !isClicked }
        ){
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier= Modifier.fillMaxWidth(fraction = 0.9f)) {
                    Text(text = "Manifest Analysis",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }

                if(isClicked) {
                    Icon(Icons.Filled.KeyboardArrowUp, "")
                }
                else{
                    Icon(Icons.Filled.ArrowDropDown, "")
                }

            }
//            Text(text = subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant);
        }
        if(isClicked) {
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Divider(color = Color.Gray, thickness = 1.dp)

                val manifestFindings = manifestAnalysis["manifest_findings"] as JSONArray

                for(i in 0 until manifestFindings.length()){
                    Text(
                        text = manifestFindings.getJSONObject(i)["description"].toString(),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(vertical = 8.dp)

                    )
                }
            }
        }

    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun CertificateDropdown(certificateAnalysis : JSONObject){
    var isClicked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)

    ){
        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { isClicked = !isClicked }
        ){
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier= Modifier.fillMaxWidth(fraction = 0.9f)) {
                    Text(text = "Certificate Analysis",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }

                if(isClicked) {
                    Icon(Icons.Filled.KeyboardArrowUp, "")
                }
                else{
                    Icon(Icons.Filled.ArrowDropDown, "")
                }

            }
//            Text(text = subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant);
        }
        if(isClicked) {
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Divider(color = Color.Gray, thickness = 1.dp)

                val certificateFindings = certificateAnalysis["certificate_findings"] as JSONArray

                for(i in 0 until certificateFindings.length()){
                    Text(
                        text = (certificateFindings.getJSONArray(i) as JSONArray).get(1).toString(),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(vertical = 8.dp)

                    )
                }
            }
        }

    }
}