package com.securenaut.securenet.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


data class PieChartEntry(val color: Color, val percentage: Float)

fun calculateStartAngles(entries: List<PieChartEntry>): List<Float> {
    var totalPercentage = 0f
    return entries.map { entry ->
        val startAngle = totalPercentage * 360
        totalPercentage += entry.percentage
        startAngle
    }
}

@Composable
fun PieChart(entries: List<PieChartEntry>) {
    Canvas(modifier = Modifier.size(200.dp)) {
        val startAngles = calculateStartAngles(entries)
        entries.forEachIndexed { index, entry ->
            drawArc(
                color = entry.color,
                startAngle = startAngles[index]+3.6f,
                sweepAngle = entry.percentage * 360f,
                useCenter = true,
                topLeft = Offset.Zero,
                size = this.size
            )

        }
    }
}

@Composable
fun PieChartLabel(){
    Row (modifier = Modifier.padding(vertical = 16.dp), horizontalArrangement = Arrangement.Center){
        Row (modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically){
            Canvas(modifier = Modifier.size(10.dp), onDraw = {
                drawCircle(color = Color(0xFFF44336))
            })
            Text(
                text = "  High",
                style = MaterialTheme.typography.titleSmall,
            )
        }
        Row (modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Canvas(modifier = Modifier.size(10.dp), onDraw = {
                drawCircle(color = Color(0xFFFFC107))
            })
            Text(
                text = "  Warning",
                style = MaterialTheme.typography.titleSmall,
            )
        }
        Row (modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically){
            Canvas(modifier = Modifier.size(10.dp), onDraw = {
                drawCircle(color = Color(0xFF2196F3))
            })
            Text(
                text = "  Low",
                style = MaterialTheme.typography.titleSmall,
            )
        }
        Row (modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Canvas(modifier = Modifier.size(10.dp), onDraw = {
                drawCircle(color = Color(0xFF0F9D58))
            })
            Text(
                text = "  Secure",
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}