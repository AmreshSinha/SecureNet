package com.securenaut.securenet.components.DALayout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.securenaut.securenet.components.MapBox
import com.securenaut.securenet.ui.theme.Typography

@Composable
fun GeoLocation() {
    val dynamicPosition = LatLng(40.7128, -74.0060)
    Surface {
        Row {
            ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                Column (modifier = Modifier.padding(26.dp, 12.dp)){
                    Text(text = "D VOIS Broadband PVT Ltd", style = Typography.bodyLarge)
                    Text(text = "Organization", style = Typography.bodySmall,modifier = Modifier.padding(top = 4.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)){
            MapBox(markerPosition = dynamicPosition)
        }
    }
}