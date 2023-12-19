package com.securenaut.securenet.pages

import android.app.Activity
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavHostController
import com.securenaut.securenet.components.HomeAppBar
import com.securenaut.securenet.components.HomeAppCountCard
import com.securenaut.securenet.components.HomeScanCard
import com.securenaut.securenet.components.HomeSplashCard
import com.securenaut.securenet.components.HomeStaticAnalysisCard
import com.securenaut.securenet.components.PrivacyDashboard
import com.securenaut.securenet.ui.theme.SecureNetTheme
import com.securenaut.securenet.ui.theme.darkPurple
import com.securenaut.securenet.ui.theme.orange
import com.securenaut.securenet.ui.theme.pinkRed
import java.io.File
import java.util.Calendar


fun getGrantedPermissions(appPackage: String,packageManager: PackageManager): MutableList<String> {
    val granted: MutableList<String> = ArrayList()
    try {
        val pi = packageManager.getPackageInfo(appPackage, PackageManager.GET_PERMISSIONS)
        for (i in pi.requestedPermissions.indices) {
            if (pi.requestedPermissionsFlags[i] and PackageInfo.REQUESTED_PERMISSION_GRANTED != 0) {
                granted.add(pi.requestedPermissions[i])
            }
        }
    } catch (e: Exception) {
    }
    return granted
}

@Composable
fun HomeActivity(navController: NavHostController) {

    Scaffold(
        topBar = {
            HomeAppBar(navController)
        }
    ) { contentPadding ->
        // Screen content

        var cameraApps=0f
        var microphoneApps=0f
        var locationApps=0f


        val packageManager: PackageManager = LocalContext.current.packageManager
        val installedApplications = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

        // Filter out system apps
        val installedApps = installedApplications.filter { appInfo ->
            appInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0
        }

        Log.i("count_apps",installedApps.size.toString())

        for (appInfo in installedApps) {
            val appName = appInfo.loadLabel(packageManager).toString()
            val packageName = appInfo.packageName
            val grantedPermissions = getGrantedPermissions(packageName,packageManager)
            Log.i("src_dir", "Source dir : " + appInfo.sourceDir);
            Log.i("text_name","$appName $packageName")

            val packageInfo = packageManager.getPackageArchiveInfo(packageName, 0)
            val sourceDir = packageInfo?.applicationInfo?.sourceDir
            try {
                val packageInfo = packageManager.getPackageInfo(packageName, 0)
                val sourceDir = packageInfo.applicationInfo.sourceDir
                val file = File(sourceDir)
                // Now "file" refers to the APK file of the installed app

                Log.i("file_found",file.name + " " + file.absolutePath)

            } catch (e: PackageManager.NameNotFoundException) {
                Log.i("app_err",e.message.toString())
                // Handle the exception if the package is not found
            }

//            Log.i("permission_info",getGrantedPermissions(packageName,pm).toString())
            if(packageName.contains("instagram")) Log.i("found_whatsapp","$appName $packageName")
            if("android.permission.CAMERA" in grantedPermissions) ++cameraApps
            if("android.permission.RECORD_AUDIO" in grantedPermissions) ++microphoneApps
            if("android.permission.ACCESS_FINE_LOCATION" in grantedPermissions || "android.permission.ACCESS_COARSE_LOCATION" in grantedPermissions) ++locationApps
        }

        val mUsageStatsManager = LocalContext.current.getSystemService(Activity.USAGE_STATS_SERVICE) as UsageStatsManager
//        val cal: Calendar = Calendar.getInstance()
//        cal.add(Calendar.YEAR, -1)
//
//        val stats = mUsageStatsManager.queryEvents(cal.timeInMillis,
//            System.currentTimeMillis())
//
//        Log.i("event_stats",stats.toString())

//        val usageStatsManager = LocalContext.current.getSystemService(Activity.USAGE_STATS_SERVICE) as UsageStatsManager?
//        val cal = Calendar.getInstance()
//        cal.add(Calendar.DAY_OF_YEAR, -1)
//        val events: MutableList<UsageEvents.Event> = ArrayList()
//
//        val usageEvents =
//            usageStatsManager!!.queryEvents(cal.timeInMillis, System.currentTimeMillis())
//
//        while (usageEvents.hasNextEvent()) {
//            val event = UsageEvents.Event()
//            usageEvents.getNextEvent(event)
//            events.add(event)
//        }
//
//        for(event in events){
//            Log.i("package_name",event.toString())
//            Log.i("event_type",event.eventType.toString())
//        }
//
//        Log.i("event_stats",events.size.toString())
//
//
//
//        val queryUsageStats = mUsageStatsManager
//            .queryUsageStats(
//                UsageStatsManager.INTERVAL_DAILY, cal.timeInMillis,
//                System.currentTimeMillis()
//            )
//
//        Log.i("usage_stats",queryUsageStats[0].toString())


        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
        ) {
            HomeScanCard(navController = navController)
            HomeAppCountCard()
            HomeStaticAnalysisCard(navController = navController)
            PrivacyDashboard(privacyDashboardData = listOf(
                mapOf(
                    "color" to darkPurple,
                    "label" to "Camera",
                    "count" to cameraApps
                ),
                mapOf(
                    "color" to orange,
                    "label" to "Location",
                    "count" to locationApps
                ),
                mapOf(
                    "color" to pinkRed,
                    "label" to "Microphone",
                    "count" to microphoneApps
                )
            )
            )
            HomeSplashCard(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    SecureNetTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                }) {
                    Text(text = "Apps Static List")
                }
            }
        }
    }
}