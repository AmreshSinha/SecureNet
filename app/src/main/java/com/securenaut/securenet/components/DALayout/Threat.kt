package com.securenaut.securenet.components.DALayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.securenaut.securenet.R
import com.securenaut.securenet.ui.theme.Typography
import com.securenaut.securenet.ui.theme.darkBlue

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Threat() {
    Column {
        FlowRow(
            modifier = Modifier,
            content = {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.info),
//                        contentDescription = "Alert",
//                        modifier = Modifier.size(50.dp)
//                    )
                    Text(
                        text = "TOR 1",
                        color = darkBlue,
                        style = Typography.bodyMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        )
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
//                        Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Trust Score",
                        style = Typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.threat_low),
                    contentDescription = "Threat Level",
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // First Card
            ElevatedCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(13.dp, 6.dp)) {
                    Text(text = "India", style = Typography.bodyMedium)
                    Text(text = "Country", style = Typography.bodySmall)
                }
            }

            // Spacer
            Spacer(modifier = Modifier.width(8.dp))

            // Second Card
            ElevatedCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(13.dp, 6.dp)) {
                    Text(text = "47 - Moderate Risk", style = Typography.bodyMedium)
                    Text(text = "Trust Score", style = Typography.bodySmall)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}