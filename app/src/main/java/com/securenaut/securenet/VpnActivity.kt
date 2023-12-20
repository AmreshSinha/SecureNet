package com.securenaut.securenet

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.VpnService
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.securenaut.securenet.data.IPDataDatabase
import com.securenaut.securenet.pages.DAListScreen
import kotlinx.coroutines.*
import com.securenaut.securenet.protocol.Packet
import com.securenaut.securenet.ui.theme.SecureNetTheme
import kotlin.coroutines.CoroutineContext


class VpnActivity : ComponentActivity(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job + CoroutineName("VpnActivity")

    private val vpnContent = registerForActivityResult(VpnContent()) {
        if (it) {
            startVpn()
        }
    }

    private var currentHandleAckId by mutableStateOf(0L)
    private var totalInputCount by mutableStateOf(0L)
    private var totalOutputCount by mutableStateOf(0L)

    private val dataUpdater = launch(Dispatchers.IO, start = CoroutineStart.LAZY) {
        while (isActive) {
            if (lifecycle.currentState == Lifecycle.State.RESUMED) {
                currentHandleAckId = Packet.globalPackId.get()
                totalInputCount = ToNetworkQueueWorker.totalInputCount
                totalOutputCount = ToDeviceQueueWorker.totalOutputCount
            }
            delay(16L)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecureNetTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    DAListScreen(navController){
                        Button(onClick = {
                            // Make sure we have the permission

                            if (isMyVpnServiceRunning) {
                                stopVpn()
                                dataUpdater.cancel()
                            } else {
                                dataUpdater.start()
                                prepareVpn()
                            }

                        }) {
                            val text = if (isMyVpnServiceRunning) {
                                "Turn Off VPN"
                            } else {
                                "Turn On VPN"
                            }
                            Text(text = text, color = Color.White)
                        }
                    }
//                    Column(modifier = Modifier.fillMaxWidth()) {
//                        Text(text = "VPN")
//                        Text(text = "AckId:$currentHandleAckId")
//                        Text(text = "Device->Network Bytes:${ToNetworkQueueWorker.totalInputCount}")
//                        Text(text = "Network->Device Bytes:${ToDeviceQueueWorker.totalOutputCount}")
//
//                    }
                }
            }
        }
    }

    private fun prepareVpn() {
        VpnService.prepare(this@VpnActivity)?.let {
            vpnContent.launch(it)
        } ?: kotlin.run {
            startVpn()
        }
    }

    private fun startVpn() {
        startService(Intent(this@VpnActivity, SecureNetVpnService::class.java))
    }

    private fun stopVpn() {
        startService(Intent(this@VpnActivity, SecureNetVpnService::class.java).also { it.action = SecureNetVpnService.ACTION_DISCONNECT })
    }

    class VpnContent : ActivityResultContract<Intent, Boolean>() {
        override fun createIntent(context: Context, input: Intent): Intent {
            return input
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
            return resultCode == RESULT_OK
        }
    }

    companion object {
        const val TAG = "VpnActivity"
    }

}