package com.securenaut.securenet.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.securenaut.securenet.R


@Composable
fun AppCard(name: String, lastScan: String, imageUrl: String) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    )
    {
        Row (modifier = Modifier.fillMaxWidth().padding(10.dp), verticalAlignment = Alignment.CenterVertically){
            AsyncImage(
                model = imageUrl,
                placeholder = painterResource(id = R.drawable.icon),
                error = painterResource(id = R.drawable.icon),
                contentDescription = "The delasign logo",
            )
            Column {
                Text(
                    text = name,
                    modifier = Modifier
                        .padding(start = 16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium

                )
                Text(
                    text = "Last scanned on $lastScan",
                    modifier = Modifier
                        .padding(start = 14.dp),
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray ,
                    style = MaterialTheme.typography.titleSmall

                )
            }
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                AsyncImage(
                    model = "",
                    placeholder = painterResource(id = R.drawable.icon),
                    error = painterResource(id = R.drawable.arrow),
                    contentDescription = "The delasign logo",
                )
            }
        }

    }
}