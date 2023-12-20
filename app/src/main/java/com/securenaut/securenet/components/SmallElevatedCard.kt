package com.securenaut.securenet.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.securenaut.securenet.R
@Composable
fun SmallElevatedCard(iconImage:Int, heading:String, value: String, width : Float){
    Surface(modifier = Modifier.padding(6.dp)){
        Card(
            modifier = Modifier
                .fillMaxWidth(fraction = width)
                .padding(top = 16.dp, bottom = 16.dp)

        ){
            Row (modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically){
                Column (modifier = Modifier.fillMaxWidth(fraction = 0.3f)){
                    AsyncImage(
                        model = "",
                        placeholder = painterResource(id = iconImage),
                        error = painterResource(id = iconImage),
                        contentDescription = "The delasign logo",
                    )
                }
                Column (modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = value,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Text(
                        text = heading,
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }

        }
    }
}