package com.securenaut.securenet.components
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomAppBar() {
            BottomAppBar(

                floatingActionButton = {
                },
                        actions = {
                            Button(
                                onClick = { /* do something */ },
                                modifier = Modifier.padding(horizontal = 16.dp),

                                ) {Text(
                                text = "Scan",
                                color = Color.White,

                                )}
                            OutlinedButton(
                                onClick = { /* do something */ },
                                ) {
                                Row (verticalAlignment = Alignment.CenterVertically){
                                    Icon(Icons.Filled.ArrowForward, "Floating action button.", tint = MaterialTheme.colorScheme.primary)
                                    Text(
                                        text = "Download Report",
                                        color = MaterialTheme.colorScheme.primary,
                                    )}
                            }
                },
            )
        }