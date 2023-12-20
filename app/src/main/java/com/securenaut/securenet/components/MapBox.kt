package com.securenaut.securenet.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapBox(markerPosition: LatLng) {
val singapore = markerPosition
val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(singapore, 10f)
}
GoogleMap(
modifier = Modifier.fillMaxSize(),
cameraPositionState = cameraPositionState
) {
    Marker(
        state = MarkerState(position = markerPosition),
        title = "Marker",
        snippet = "Dynamic Marker"
    )
}
}