package com.securenaut.securenet.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.securenaut.securenet.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSplashCard(navController: NavController){
    Card(
        onClick = {navController.navigate("splashScreen")},
        modifier = Modifier
            .fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    )
    {
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.img_9),
                contentDescription = "",
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Column (modifier = Modifier.fillMaxWidth(fraction = 0.9f)) {
                Text(
                    text = "Keep yourself informed!",
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "How to tell if your phone is hacked",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Icon(Icons.Filled.ArrowForward, contentDescription = null, tint = Color.DarkGray)
        }
    }
}