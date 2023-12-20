package com.securenaut.securenet.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.securenaut.securenet.ui.theme.CardBorder
import com.securenaut.securenet.ui.theme.Purple
import com.securenaut.securenet.ui.theme.Typography

@Composable
fun HomeStaticAnalysisCard(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        OutlinedCard(
            border = BorderStroke(0.5.dp, Color.Black), modifier = Modifier.border(
                width = 1.dp, color = CardBorder, shape = RoundedCornerShape(size = 12.dp)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                Text(
                    text = "Static Analysis",
                    style = Typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = "No Vulnerable Apps Found", style = Typography.titleLarge)
                Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.End) {
                    ElevatedButton(
                        onClick = {
                                  navController.navigate("staticAnalysisAppList")
                        },
                        border = BorderStroke(width = 1.dp, color = Purple)
                    ) {
                        Text(text = "Proceed", style = Typography.bodyMedium, color = Purple)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeStaticAnalysisCard1() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        OutlinedCard(
            border = BorderStroke(0.5.dp, Color.Black), modifier = Modifier.border(
                width = 1.dp, color = CardBorder, shape = RoundedCornerShape(size = 12.dp)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                Text(text = "Static Analysis", style = Typography.labelSmall)
                Text(text = "No Threats Found", style = Typography.titleLarge)
                Text(
                    text = "Last App Installed 3 hours ago",
                    style = Typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis
                )
                Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.End) {
                    ElevatedButton(
                        onClick = {

                        },
                        border = BorderStroke(width = 1.dp, color = Purple)
                    ) {
                        Text(text = "Scan", style = Typography.labelSmall, color = Purple)
                    }
                }
            }
        }
    }
}