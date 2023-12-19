package com.securenaut.securenet

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.net.VpnService
import android.os.Build
import android.os.ParcelFileDescriptor
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

var isMyVpnServiceRunning by mutableStateOf(false)

class SecureNetVpnService : VpnService() {

    private val mConfigureIntent: PendingIntent by lazy {
        var activityFlag = PendingIntent.FLAG_UPDATE_CURRENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            activityFlag += PendingIntent.FLAG_MUTABLE
        }
        PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), activityFlag)
    }

    private lateinit var vpnInterface: ParcelFileDescriptor

    override fun onCreate() {
        UdpSendWorker.start(this, applicationContext)
        UdpReceiveWorker.start(this)
        UdpSocketCleanWorker.start()
        TcpWorker.start(this, applicationContext)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return if (intent?.action == ACTION_DISCONNECT) {
            disconnect()
            START_NOT_STICKY
        } else {
            connect()
            START_STICKY
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disconnect()
        UdpSendWorker.stop()
        UdpReceiveWorker.stop()
        UdpSocketCleanWorker.stop()
        TcpWorker.stop()
    }

    private fun connect() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            updateForegroundNotification(R.string.vpn_connected)
        }
        vpnInterface = createVpnInterface()
        val fileDescriptor = vpnInterface.fileDescriptor
        ToNetworkQueueWorker.start(fileDescriptor)
        ToDeviceQueueWorker.start(fileDescriptor)
        isMyVpnServiceRunning = true
    }

    private fun disconnect() {
        ToNetworkQueueWorker.stop()
        ToDeviceQueueWorker.stop()
        vpnInterface.close()
        isMyVpnServiceRunning = false
        stopForeground(true)
        System.gc()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateForegroundNotification(message: Int) {
        val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.createNotificationChannel(
            NotificationChannel(
                NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_ID,
                NotificationManager.IMPORTANCE_DEFAULT
            )
        )
        startForeground(
            1, Notification.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_vpn_key)
                .setContentText(getString(message))
                .setContentIntent(mConfigureIntent)
                .build()
        )
    }

    private fun createVpnInterface(): ParcelFileDescriptor {
        return Builder()
            .addAddress("10.0.0.2", 32)
            .addRoute("0.0.0.0", 0)
            .addDnsServer("114.114.114.114")
            .setSession("VPN-Demo")
            .setBlocking(true)
            .setConfigureIntent(mConfigureIntent)
            .addDisallowedApplication("com.securenaut.securenet")
            .also {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    it.setMetered(false)
                }
            }
            .establish() ?: throw IllegalStateException("Unable to initialize vpnInterface")
    }

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "SecureNetVPN"
        const val ACTION_CONNECT = "com.securenaut.securenet.CONNECT"
        const val ACTION_DISCONNECT = "com.securenaut.securenet.DISCONNECT"
    }
}