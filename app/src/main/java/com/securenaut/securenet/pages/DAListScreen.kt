package com.securenaut.securenet.pages

import AppBar
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.LatLng
import com.securenaut.securenet.R
import com.securenaut.securenet.components.DAAppCard
import com.securenaut.securenet.components.DAScanCard
import com.securenaut.securenet.components.HomeScanCard
import com.securenaut.securenet.data.IPDataViewModel
import com.securenaut.securenet.ui.theme.Typography
import com.securenaut.securenet.ui.theme.textGray
import kotlinx.coroutines.delay
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.securenaut.securenet.components.BottomDABar
import com.securenaut.securenet.data.GlobalStaticClass
import com.securenaut.securenet.viewmodel.BlackListView
import com.securenaut.securenet.viewmodel.BlackListViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun DAListScreen(navController: NavController, vpnButton: @Composable () -> Unit) {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }
    val ipDataViewModel = ViewModelProvider(owner = viewModelStoreOwner)[IPDataViewModel::class.java]
    val allIPDatas by ipDataViewModel.allIPData.observeAsState(listOf())
    val blacklistViewModel: BlackListViewModel = viewModel()
    Scaffold(
        topBar = {
            AppBar(navController = navController, name = "Dynamic Analysis", onBackScreen = "home")
        },
        bottomBar = {BottomDABar(blacklistViewModel)}
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
                .padding(horizontal = 16.dp)
                .fillMaxHeight()
        ) {
            Log.i("allIPData",allIPDatas.toString())
            DAScanCard(navController = navController, vpnButton)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Threats detected in", style = Typography.bodyMedium)
                Image(
                    painter = painterResource(id = R.drawable.arrowdown),
                    contentDescription = "Down Arrow"
                )
            }
            Column {
                Log.i("kuch",allIPDatas.size.toString())
                allIPDatas.forEachIndexed { index, ipData ->
                    key(ipData.id) {
                        Log.d("pkgname", ipData.packageName)
                        val packageManager: PackageManager = LocalContext.current.packageManager
                        val appInfo = packageManager.getApplicationInfo(ipData.packageName, 0)
                        val appName = packageManager.getApplicationLabel(appInfo).toString()
                        val appIcon = packageManager.getApplicationIcon(appInfo)
                        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
                        DAAppCard(appName = appName, lastScan = formatter.format(Date(ipData.timestamp)), ipData = ipData ,appIcon = appIcon, navController)
                    }
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Blocklist", style = Typography.bodyMedium)
                Image(
                    painter = painterResource(id = R.drawable.arrowdown),
                    contentDescription = "Down Arrow"
                )
            }
            Column {
                BlackListView(blacklistViewModel)
            }
        }
    }
}

