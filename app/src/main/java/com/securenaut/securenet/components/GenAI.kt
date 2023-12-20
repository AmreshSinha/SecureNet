package com.securenaut.securenet.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.securenaut.securenet.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun GenAI(summary: String, actions: String){

    var isClicked by remember { mutableStateOf(false) }
    var isLoaded by remember { mutableStateOf(false) }
    var action by remember {
        mutableStateOf("")
    }

Column (modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            FilledTonalButton(onClick = { isClicked = true; action = summary }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Row(modifier = Modifier.padding(end = 4.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.img_10),
                            contentDescription = "",
                        )
                    }

                    Text(text = "Summarize")
                }
            }
            FilledTonalButton(onClick = { isClicked = true; action = actions }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Row(modifier = Modifier.padding(end = 4.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.img_10),
                            contentDescription = "",
                        )
                    }

                    Text(text = "Actions")
                }
            }
        }


    if (isClicked && !isLoaded) {
        OutlinedCard(
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Suggesting Actions...",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Row(modifier = Modifier.padding(end = 4.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.img_10),
                            contentDescription = "",
                        )
                    }
                }
                Text(
                    text = "Confused on what to do? Get help!",
                    color = Color.Gray,
                    style = MaterialTheme.typography.labelMedium
                )
            }

        }
    } else if (isLoaded && isClicked) {
        OutlinedCard(
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Actions",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth(fraction = 0.8f),
                        color = MaterialTheme.colorScheme.primary
                    )
                    IconButton(onClick = { isClicked = false}) {
                    Icon(Icons.Filled.Close, "", tint = MaterialTheme.colorScheme.primary)}

                }
                Text(
                    text = action,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }


}
}