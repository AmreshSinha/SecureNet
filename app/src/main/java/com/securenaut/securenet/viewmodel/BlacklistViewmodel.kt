package com.securenaut.securenet.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.securenaut.securenet.data.GlobalStaticClass
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
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

class BlackListViewModel : ViewModel() {
    // Using mutableStateListOf for automatic UI updates
    val blackList = mutableStateListOf<Pair<String, String>>()
    val client = OkHttpClient()

    // Function to update the list
    fun updateList(it: Pair<String, String>) {
        blackList.add(it)
        GlobalStaticClass.blackList.add(it)
        val jsonObject = JSONObject()
        if (it.second == "IP") {
            jsonObject.put("ip", it.first)
        } else {
            jsonObject.put("domain", it.first)
        }
        makeNetworkRequest(
            client,
            "https://securenet.photoai.pro/dynamic/blacklist",
            jsonObject.toString()
        ) { result ->
            // Handle the result
            // Remember to switch back to the main thread if updating UI
        }
    }

    fun sync(){
        getNetworkRequest(
            client,
            "https://securenet.photoai.pro/dynamic/blacklist"
        ) { result ->
            val jsonObj = JSONObject(result)
            blackList.clear()
            GlobalStaticClass.blackList.clear()
            val ips = jsonObj.getJSONArray("ips")
            for (i in 0 until ips.length()) {
                val ip = ips.getString(i)
                blackList.add(Pair(ip, "IP"))
                GlobalStaticClass.blackList.add(Pair(ip, "IP"))
            }
            val domains = jsonObj.getJSONArray("domains")
            for (i in 0 until domains.length()) {
                val domain = domains.getString(i)
                blackList.add(Pair(domain, "Domain"))
                GlobalStaticClass.blackList.add(Pair(domain, "Domain"))
            }
        }
    }
}

@Composable
fun BlackListView(viewModel: BlackListViewModel) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        viewModel.blackList.forEach { pair ->
            Card (modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)){
                Row (modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Text(text = pair.first, modifier = Modifier.fillMaxWidth(fraction = 0.6f) )
                    Text(text = pair.second, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

        }
    }
}

