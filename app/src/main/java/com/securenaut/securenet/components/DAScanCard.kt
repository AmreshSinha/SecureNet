package com.securenaut.securenet.components

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.securenaut.securenet.R
import com.securenaut.securenet.VpnActivity
import com.securenaut.securenet.data.IPDataViewModel
import com.securenaut.securenet.pages.DAReportScreen
import com.securenaut.securenet.ui.theme.CardBorder
import com.securenaut.securenet.ui.theme.Typography
import com.securenaut.securenet.ui.theme.White
import com.securenaut.securenet.ui.theme.textGray

@Composable
fun DAScanCard(navController: NavController, vpnButton: @Composable () -> Unit) {
    val context = LocalContext.current
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }
    val ipDataViewModel = ViewModelProvider(owner = viewModelStoreOwner)[IPDataViewModel::class.java]
    val allIPDatas by ipDataViewModel.allIPData.observeAsState(listOf())
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        OutlinedCard(
            border = BorderStroke(0.5.dp, Color.Black),
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = CardBorder,
                    shape = RoundedCornerShape(size = 12.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(0.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Trust Score", style = Typography.bodyMedium)
                        Row (verticalAlignment = Alignment.CenterVertically){
                            Text(text = "${allIPDatas.size} Threats", style = Typography.headlineSmall)
                            Text(text = "", style = Typography.bodyMedium, color = textGray)
                        }
                        Text(text = "performed 3 hours ago", style = Typography.bodyMedium)
                    }
                    Image(
                        painter = painterResource(id = R.drawable.threat_low),
                        contentDescription = "My Image",
                        modifier = Modifier.fillMaxHeight()
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    vpnButton()
                }
            }
        }
    }

}