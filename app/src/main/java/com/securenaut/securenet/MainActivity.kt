package com.securenaut.securenet

import android.Manifest
import StaticAnalysisAppList
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import androidx.navigation.navArgument
import com.securenaut.securenet.pages.HomeActivity
import com.securenaut.securenet.pages.SettingsScreen
import com.securenaut.securenet.pages.StaticAnalysisScreen
import com.securenaut.securenet.ui.theme.SecureNetTheme

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

        FirebaseApp.initializeApp(this)

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

        setContent{
            SecureNetTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home"){
                    composable("home"){
                        HomeActivity(navController)
                    }
                    composable("staticAnalysisAppList"){
                        StaticAnalysisAppList(navController)
                    }
                    composable("settings"){
                        SettingsScreen(navController)
                    }
                    composable("staticAnalysis/{app}") { backStackEntry ->
                        backStackEntry.arguments?.getString("app")
                            ?.let { StaticAnalysisScreen(navController, it) }
                    }

                }
            }
        }

        Log.wtf("rand", "Inside main activity")

        firebaseMessaging = FirebaseMessaging.getInstance()

        firebaseMessaging.token.addOnCompleteListener { task ->
            Log.wtf("rand", "Inside token listener")
            if (task.isSuccessful) {
                val token = task.result
                Log.i("rand", "Token: $token")
                // Send token to server
            } else {
                Log.i("rand", "Failed to get token")
            }
        }

    }
}
