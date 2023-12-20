package com.securenaut.securenet.components.DALayout

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.securenaut.securenet.R
import com.securenaut.securenet.components.DropDownBar
import com.securenaut.securenet.components.GenAI
import com.securenaut.securenet.components.MapBox
import com.securenaut.securenet.data.IPData
import com.securenaut.securenet.ui.theme.Typography
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun Base(tabIndex:Int, ipData: IPData) {

    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

//            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
//                DropDownBar()
//            }


            Spacer(modifier = Modifier.height(16.dp))
//            Row (modifier = Modifier.fillMaxWidth()) {
//                GenAI(summary = "Summarize sdjdvs svjs sjvnsv sjnvsjnv sfjvnsfj ssjvnsnjv kjsnvfksj sj kskjd skjd", actions = "Supporting line text lorem ipsum dolor sit amet, consectetur. Supporting line text lorem ipsum dolor sit amet, consectetur. Supporting line text lorem ipsum dolor sit amet, consectetur.")
//
//            }

//            Row(
//                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = "Summary for the IP/Domain", style = Typography.bodyMedium)
//                Text(text = "See More", style = Typography.bodyMedium)
//            }
            Spacer(modifier = Modifier.height(16.dp))
            ElevatedCard() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(26.dp, 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(verticalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "47 - Moderate Risk", style = Typography.bodyLarge)
                        Text(text = "Trust Score", style = Typography.bodySmall,modifier = Modifier.padding(top = 4.dp))
                    }
                    Image(
                        painter = painterResource(id = R.drawable.threat_low),
                        contentDescription = "Threat Level",
                        modifier = Modifier.fillMaxHeight()
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            if(ipData.ip != null){
                val dynamicPosition = LatLng(ipData.latitude!!, ipData.longitude!!)
                Log.d("ipData", "${ipData.latitude}::${ipData.longitude}")
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)){
                    MapBox(markerPosition = dynamicPosition)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
//            Row {
//                ElevatedCard(modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()
//                ) {
//                    Column(modifier = Modifier.padding(13.dp, 6.dp)) {
//                        Text(text = "India", style = Typography.bodyMedium)
//                        Text(text = "Country", style = Typography.bodySmall)
//                    }
//                }
//                Spacer(modifier = Modifier.width(8.dp))
//                ElevatedCard(modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()) {
//                    Column(modifier = Modifier.padding(13.dp, 6.dp)) {
//                        Text(text = "47 - Moderate Risk", style = Typography.bodyMedium)
//                        Text(text = "Trust Score", style = Typography.bodySmall)
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//            Row {
//                ElevatedCard(modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()
//                ) {
//                    Column(modifier = Modifier.padding(13.dp, 6.dp)) {
//                        Text(text = "India", style = Typography.bodyMedium)
//                        Text(text = "Country", style = Typography.bodySmall)
//                    }
//                }
//                Spacer(modifier = Modifier.width(8.dp))
//                ElevatedCard(modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()) {
//                    Column(modifier = Modifier.padding(13.dp, 6.dp)) {
//                        Text(text = "47 - Moderate Risk", style = Typography.bodyMedium)
//                        Text(text = "Trust Score", style = Typography.bodySmall)
//                    }
//                }
//            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column (modifier = Modifier.padding(26.dp, 12.dp)){
                        Text(text = "D VOIS Broadband PVT Ltd", style = Typography.bodyLarge)
                        Text(text = "Organization", style = Typography.bodySmall,modifier = Modifier.padding(top = 4.dp))
                    }
                }
            }
        }
        }
    }
