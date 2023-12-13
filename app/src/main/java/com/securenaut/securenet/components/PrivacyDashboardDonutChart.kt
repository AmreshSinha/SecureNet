package com.securenaut.securenet.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.securenaut.securenet.ui.theme.IconColor
import com.securenaut.securenet.ui.theme.Purple
import com.securenaut.securenet.ui.theme.White

@Composable
fun PrivacyDashboardDonutChart(
    counts: List<Float>,
    colors: List<Color>,
){
    val sum = counts.sum()
    val sweepAngles = counts.map {
        360 * (it/sum)
    }
    Canvas(modifier = Modifier.fillMaxSize().background(White)){
        var startAngle = 0f;
        for(i in 0..(counts.size-1)){
            drawArc(
                color = colors[i],
                startAngle = startAngle,
                sweepAngle = sweepAngles[i],
                useCenter = false,
                size = Size(width = size.width, height = size.width),
                style = Stroke(
                    width = 28f
                )
            )
            startAngle+=sweepAngles[i]
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChart(){
    PrivacyDashboardDonutChart(
        counts = listOf(25f,35f),
        colors = listOf(Purple, IconColor)
    )
}