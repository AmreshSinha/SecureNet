package com.securenaut.securenet.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.securenaut.securenet.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppCard(navController: NavController, name: String, lastScan: String, appIconDrawable: Drawable) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        onClick = {
            navController.navigate("staticAnalysis/${name}")
        },
        modifier = Modifier
            .fillMaxWidth().padding(horizontal = 0.dp, vertical = 16.dp)

    )
    {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = rememberImagePainter(data = appIconDrawable),
                contentDescription = "App Icon",
                modifier = Modifier.size(48.dp)
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