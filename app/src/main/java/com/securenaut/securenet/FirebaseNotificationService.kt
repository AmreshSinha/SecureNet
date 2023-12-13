package com.securenaut.securenet

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import androidx.activity.ComponentActivity

class FirebaseNotificationService : FirebaseMessagingService() {

    private var id = 1;

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("MyClassTag", "Message data: ${remoteMessage.data}")
        // Display push notification
        var builder = NotificationCompat.Builder(this, getString(R.string.channel_id))
            .setSmallIcon(R.drawable.icon)
            .setContentTitle("Test Notif")
            .setContentText("Test Content")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

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