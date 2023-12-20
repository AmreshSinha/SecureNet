package com.securenaut.securenet

import android.Manifest
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import com.securenaut.securenet.data.GlobalStaticClass
import java.io.File

class FirebaseNotificationService : FirebaseMessagingService() {

    private var id = 1;

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("MyClassTag", "Message data: ${remoteMessage.data}")
        // Display push notification
        var builder = NotificationCompat.Builder(this, getString(R.string.channel_id))
            .setSmallIcon(R.drawable.icon)
            .setContentTitle(remoteMessage.data["title"])
            .setContentText(remoteMessage.data["body"])
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val packageManager = this.packageManager

        val installedApplications =
            packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        var appDataList: MutableList<MutableMap<String, Any>> = mutableListOf()
        val installedApps = installedApplications.filter { appInfo ->
            appInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0
        }

        val sharedPref = getSharedPreferences("securenet_install_pref", Context.MODE_PRIVATE)

        val allKeys = sharedPref.all.keys

        var cnt=0;

//        createNotification(title = "An app did a malicious activity", content = "called abuse ip/domain")

        for (appInfo in installedApps) {

            val appName = appInfo.loadLabel(packageManager).toString()
            val packageName = appInfo.packageName
            Log.i("src_dir", "Source dir : " + appInfo.sourceDir);
            Log.i("package_name", "$packageName")

//            if(packageName==remoteMessage.data["package_name"]){
//                createNotification(title = "${appName} did a malicious activity", content = "called abuse ip/domain")
//            }

            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            val sourceDir = packageInfo.applicationInfo.sourceDir
            GlobalStaticClass.srcDir=sourceDir
            val apkFile = File(sourceDir)
            if(!allKeys.contains(getApkHash(apkFile))){
                cnt+=1
            }
        }

        createNotification(title = "You have new applications installed on your device", content = "$cnt apps were recently installed")

    }

    fun createNotification(title: String, content: String) {

        // Create a notification
        val builder = NotificationCompat.Builder(this, getString(R.string.channel_id))
            .setSmallIcon(R.drawable.icon) // Replace with your own icon
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Show the notification
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define.
            Log.d("MyClassTag", "CHECKING PERMISSION")
            if (ActivityCompat.checkSelfPermission(
                    this@FirebaseNotificationService,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("MyClassTag", "ALLOWED PERMISSION")
                notify(id, builder.build())
                ++id
            }
            else{
                Log.d("MyClassTag", "NOT ALLOWED PERMISSION")
            }
        }
    }

    private fun handleDataMessage(data: Map<String, String>) {
        // Handle data payload of FCM here
    }

    private fun handleNotificationMessage(notification: RemoteMessage.Notification) {
        // Handle notification payload of FCM here
    }
}