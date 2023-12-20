package com.securenaut.securenet.components
import android.content.Context
import android.os.Environment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.securenaut.securenet.HttpWorker
import com.securenaut.securenet.data.GlobalStaticClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.buffer
import okio.sink
import org.json.JSONObject
import java.io.File
import java.io.IOException


fun makeNetworkRequest(client: OkHttpClient, url: String, data: String, onSuccess: (String) -> Unit) {
    val request = Request.Builder().url(url).post(data.toRequestBody()).build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // Handle failure
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                onSuccess(response.body?.string() ?: "")
            }
        }
    })
}

fun getNetworkRequest(client: OkHttpClient, url: String, onSuccess: (String) -> Unit) {
    val request = Request.Builder().url(url).build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // Handle failure
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                onSuccess(response.body?.string() ?: "")
            }
        }
    })
}
@Composable
fun BottomDABar() {
    val snackbarHostState = remember { SnackbarHostState() }

        val openAlertDialog = remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()
        val client = OkHttpClient()
        val scope = rememberCoroutineScope()
        Box {
            BottomAppBar(

                floatingActionButton = {

                },
                actions = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.5f)
                            .padding(16.dp)
                    ) {
                        Button(onClick = {
                            coroutineScope.launch(Dispatchers.IO) {
                                getNetworkRequest(
                                    client,
                                    "https://securenet.photoai.pro/dynamic/blacklist"
                                ) { result ->

                                    // Handle the result
                                    // Remember to switch back to the main thread if updating UI
                                    val jsonObj = JSONObject(result)
                                    GlobalStaticClass.blackList.clear()
                                    val ips = jsonObj.getJSONArray("ips")
                                    for (i in 0 until ips.length()) {
                                        val ip = ips.getString(i)
                                        GlobalStaticClass.blackList.add(Pair(ip, "IP"))
                                    }
                                    val domains = jsonObj.getJSONArray("domains")
                                    for (i in 0 until domains.length()) {
                                        val domain = domains.getString(i)
                                        GlobalStaticClass.blackList.add(Pair(domain, "Domain"))
                                    }
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Blacklist Synced!")
                                    }
                                }
                            }
                        }) {

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Sync Now",
                                    color = Color.White,
                                )

                            }
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = { openAlertDialog.value = !openAlertDialog.value },
                            modifier = Modifier.padding(horizontal = 16.dp),

                            ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.Add, "", tint = Color.White)
                                Text(
                                    text = "Blacklist",
                                    color = Color.White,

                                    )
                            }
                        }
                    }


                },
            )
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
        when {
            // ...
            openAlertDialog.value -> {
                AlertDialogExample(
                    onDismissRequest = { openAlertDialog.value = false },
                    onConfirmation = {
                        openAlertDialog.value = false
                        GlobalStaticClass.blackList.add(it)
                        val jsonObject = JSONObject()
                        if (it.second == "IP") {
                            jsonObject.put("ip", it.first)
                        } else {
                            jsonObject.put("domain", it.first)
                        }
                        coroutineScope.launch(Dispatchers.IO) {
                            makeNetworkRequest(
                                client,
                                "https://securenet.photoai.pro/dynamic/blacklist",
                                jsonObject.toString()
                            ) { result ->
                                // Handle the result
                                // Remember to switch back to the main thread if updating UI
                            }
                        }

                    },
                    dialogTitle = "Alert dialog example",
                    dialogText = "This is an example of an alert dialog with buttons.",
                    icon = Icons.Default.Info
                )
            }
        }
    }


@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: (Pair<String, String>) -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    var text by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("IP", "Domain")
    var selectedIndex by remember { mutableStateOf(0) }

    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {

            Column {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("IP/Domain") }
                )

                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedButton(
                        onClick = { expanded = true }
                    ) {
                        Text(text = options[selectedIndex])
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        options.forEachIndexed { index, option ->
                            DropdownMenuItem(
                                text = { Text(text = option) },
                                onClick = {
                                    selectedIndex = index
                                    expanded = false
                                }
                            )

                        }
                    }
                }
            }

        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation(Pair(text, options[selectedIndex]))
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}
