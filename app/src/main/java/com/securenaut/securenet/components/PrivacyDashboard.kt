package com.securenaut.securenet.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.securenaut.securenet.ui.theme.CardBorder
import com.securenaut.securenet.ui.theme.IconColor
import com.securenaut.securenet.ui.theme.Purple
import com.securenaut.securenet.ui.theme.Typography

@Composable
fun PrivacyDashboard(privacyDashboardData : List<Map<String,Any>>) {
    val permissionTilesData = privacyDashboardData.map { mapOf(
        "label" to it["label"],
        "color" to it["color"],
    ) }
    val countsData = privacyDashboardData.map {it["count"] as Float}
    val colorData = privacyDashboardData.map {it["color"] as Color}
    print(countsData)
    print(colorData)
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(276.dp)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        OutlinedCard(
            border = BorderStroke(0.5.dp, Color.Black), modifier = Modifier.border(
                width = 1.dp, color = CardBorder, shape = RoundedCornerShape(size = 12.dp)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.padding(end = 40.dp)
                ){
                    Text(text = "Privacy", style = Typography.titleLarge)
                    Text(text = "Dashboard", style = Typography.titleLarge)
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = "Apps using",
                        style = Typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 16.dp),
                        text = "permissions of",
                        style = Typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis
                    )
                    for(e in permissionTilesData){
                        PermissionTile(circleColor = e["color"] as Color, label = e["label"] as String)
                    }
                }
                PrivacyDashboardDonutChart(
                    counts = countsData,
                    colors = colorData
                )
            }
        }
    }
}

@Composable
fun PermissionTile(circleColor: Color, label: String) {
    val size = 12f
    val sizeToDp = size / LocalContext.current.resources.displayMetrics.density
    Row(
        modifier = Modifier.padding(start = 4.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier
                .height(sizeToDp.dp)
                .width(sizeToDp.dp)
        ) {
            drawCircle(
                circleColor,
                radius = size
            )
        }
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = label,
            style = Typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    PrivacyDashboard(privacyDashboardData = listOf(
        mapOf(
            "color" to Color.Blue,
            "label" to "Camera",
            "count" to 36f
        ),
        mapOf(
            "color" to Color.Yellow,
            "label" to "Location",
            "count" to 36f
        ),
        mapOf(
            "color" to Color.Red,
            "label" to "Microphone",
            "count" to 36f
        )
    ))
}