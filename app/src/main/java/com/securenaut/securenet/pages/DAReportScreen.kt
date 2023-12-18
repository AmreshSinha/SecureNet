package com.securenaut.securenet.pages

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.google.android.gms.maps.model.LatLng
import com.securenaut.securenet.components.MapBox

@Composable
fun DAReportScreen() {
    val dynamicPosition = LatLng(40.7128, -74.0060)
   Surface {
       MapBox(markerPosition = dynamicPosition)
   }
}