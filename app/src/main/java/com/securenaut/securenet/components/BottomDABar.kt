package com.securenaut.securenet.components
import android.content.Context
import android.os.Environment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
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
import com.securenaut.securenet.HttpWorker
import com.securenaut.securenet.data.GlobalStaticClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.buffer
import okio.sink
import java.io.File
import java.io.IOException

@Composable
fun BottomDABar() {
            BottomAppBar(

                floatingActionButton = {

                },
                        actions = {
                            Row (modifier = Modifier
                                .fillMaxWidth(fraction = 0.5f)
                                .padding(horizontal = 16.dp)){
                                Text(
                                    text = "IP does not exist in community",
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                Button(
                                    onClick = {},
                                    modifier = Modifier.padding(horizontal = 16.dp),

                                    ) {
                                    Row (verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Filled.Add, "", tint = Color.White)
                                        Text(
                                            text = "Blacklist",
                                            color = Color.White,

                                            )
                                    }
                                }
                            }


                },
            )
        }

