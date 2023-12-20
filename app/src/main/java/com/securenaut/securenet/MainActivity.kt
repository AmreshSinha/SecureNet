package com.securenaut.securenet

import StaticAnalysisAppList
import android.Manifest
import android.app.AppOpsManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.usage.UsageStatsManager
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Process
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import androidx.navigation.navArgument
import com.securenaut.securenet.components.HorizontalScrollScreen
import com.securenaut.securenet.pages.DAListScreen
import com.securenaut.securenet.pages.DAReportScreen
import com.securenaut.securenet.pages.HomeActivity
import com.securenaut.securenet.pages.PrelimnaryCheck
import com.securenaut.securenet.pages.SettingsScreen
import com.securenaut.securenet.pages.SplashScreen
import com.securenaut.securenet.pages.StaticAnalysisScreen
import com.securenaut.securenet.ui.theme.SecureNetTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.content.Context
import android.content.pm.ApplicationInfo
import com.securenaut.securenet.data.GlobalStaticClass
import com.securenaut.securenet.pages.getGrantedPermissions
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.security.DigestInputStream
import java.security.MessageDigest

fun getApkHash(apkFile: File): String{
    val md5Digest = MessageDigest.getInstance("MD5")
    FileInputStream(apkFile).use { fileInputStream ->
        DigestInputStream(fileInputStream, md5Digest).use { digestInputStream ->
            // Read the file content and update the digest
            val buffer = ByteArray(8192)
            while (digestInputStream.read(buffer) != -1) {
                // Read file content
            }
        }
    }

    // Get the MD5 hash as a byte array
    val hashBytes = md5Digest.digest()

    // Convert the byte array to a hexadecimal string
    val hexString = StringBuilder()
    for (byte in hashBytes) {
        hexString.append(String.format("%02x", byte))
    }

    return hexString.toString()
}

class MainActivity() : ComponentActivity() {

    private lateinit var firebaseMessaging: FirebaseMessaging

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
            Log.d("YES","MAIN ALLOWED PERMISSION")
        } else {
            Log.d("YES","MAIN NOT ALLOWED PERMISSION")
            // TODO: Inform user that that your app will not show notifications.
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.wtf("rand", "Inside main activity")
        super.onCreate(savedInstanceState)

        // Create the NotificationChannel.
        val name = getString(R.string.channel_name)
        val descriptionText = getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val mChannel = NotificationChannel(getString(R.string.channel_id), name, importance)
        mChannel.description = descriptionText
        // Register the channel with the system. You can't change the importance
        // or other notification behaviors after this.
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)

        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.POST_NOTIFICATIONS) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected, and what
                // features are disabled if it's declined. In this UI, include a
                // "cancel" or "no thanks" button that lets the user continue
                // using your app without granting the permission.
            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher.launch(
                    Manifest.permission.POST_NOTIFICATIONS)
            }
        }

//        startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));

        setContent{

            GlobalStaticClass.sharedPrefInstance = getSharedPreferences("securenet_pref", Context.MODE_PRIVATE)

            Log.wtf("rand", "Inside main activity")
            FirebaseApp.initializeApp(this)
            firebaseMessaging = FirebaseMessaging.getInstance()


            // SETTING DEVICE FCM TOKEN ON SERVER ON EACH LAUNCH
            firebaseMessaging.token.addOnCompleteListener { task ->
                Log.wtf("rand", "Inside token listener")
                if (task.isSuccessful) {
                    val token = task.result
                    Log.i("rand_token", "$token abc")
                    CoroutineScope(Dispatchers.IO).launch {
                        HttpWorker().setFCMToken(token = token)
                    }
                    Log.i("rand_token","Updated Token")
                } else {
                    Log.i("rand", "Failed to get token")
                }
            }

            val packageManager: PackageManager = LocalContext.current.packageManager
            val installedApplications =
                packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            var appDataList: MutableList<MutableMap<String, Any>> = mutableListOf()
            val installedApps = installedApplications.filter { appInfo ->
                appInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0
            }

            Log.i("count_apps", installedApps.size.toString())

            for (appInfo in installedApps) {
                try {
                    val appName = appInfo.loadLabel(packageManager).toString()
                    val packageName = appInfo.packageName
                    Log.i("src_dir", "Source dir : " + appInfo.sourceDir);
                    Log.i("package_name", "$packageName")
                    val packageInfo = packageManager.getPackageInfo(packageName, 0)
                    val sourceDir = packageInfo.applicationInfo.sourceDir
                    GlobalStaticClass.srcDir=sourceDir
                    val apkFile = File(sourceDir)
                    val grantedPermissions = getGrantedPermissions(packageName, packageManager)
                    GlobalStaticClass.appPermissions=grantedPermissions
                    val appIconDrawable = appInfo.loadIcon(packageManager)
                    Log.i("app_name", "$appName")
                    Log.i(
                        "file_found",
                        apkFile.name + " " + apkFile.absolutePath + " " + appIconDrawable.toString()
                    )
                    appDataList.add(
                        mapOf(
                            "appName" to appName,
                            "appIconDrawable" to appIconDrawable,
                            "apkFile" to apkFile,
                            "packageName" to packageName,
                            "apkHash" to getApkHash(apkFile)
                        ) as MutableMap<String, Any>
                    )
                } catch (e: PackageManager.NameNotFoundException) {
                    Log.i("app_err", e.message.toString())
                    // Handle the exception if the package is not found
                }
            }

            GlobalStaticClass.installedAppsData=appDataList


            SecureNetTheme {
                val navController = rememberNavController()
                // Observe the data from the view model

                NavHost(navController = navController, startDestination = "home"){
                    composable("splash_screen") {
                        SplashScreen(navController = navController)
                    }
                    composable("home"){
                        HomeActivity(navController)
//                        DAReportScreen(navController)

                    }
                    composable("dynamic"){

                    }
                    composable("staticAnalysisAppList"){
                        StaticAnalysisAppList(navController)
                    }
                    composable("settings"){
                        SettingsScreen(navController,activity = this@MainActivity)
                    }
                    composable("staticAnalysis") {
                        StaticAnalysisScreen(navController)
                    }
                    composable("prelimnaryCheck") {
                        PrelimnaryCheck(navController)
                    }
                    composable("DAReport") {
                        DAReportScreen(navController)
                    }

                    composable("splashScreen") {
                        HorizontalScrollScreen(navController = navController)
                    }
                }
            }
        }

    }
}
