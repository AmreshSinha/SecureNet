package com.securenaut.securenet.components.DALayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.securenaut.securenet.R
import com.securenaut.securenet.ui.theme.Typography

@Composable
fun Organisation() {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "ASN", style = Typography.bodyMedium)
        Image(
            painter = painterResource(id = R.drawable.info),
            contentDescription = "Alert",
            modifier = Modifier.size(20.dp)
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row {
        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column (modifier = Modifier.padding(26.dp, 12.dp)){
                Text(text = "D VOIS Broadband PVT Ltd", style = Typography.bodyLarge)
                Text(text = "Organization", style = Typography.bodySmall,modifier = Modifier.padding(top = 4.dp))
            }
        }
    }
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