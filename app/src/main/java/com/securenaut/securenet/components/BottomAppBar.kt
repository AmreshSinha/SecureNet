package com.securenaut.securenet.components
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.buffer
import okio.sink
import java.io.File
import java.io.IOException
import androidx.browser.customtabs.CustomTabsIntent
import com.securenaut.securenet.data.GlobalStaticClass

@Composable
fun BottomAppBar(context: Context) {
            BottomAppBar(

                floatingActionButton = {
                },
                        actions = {
                            OutlinedButton(
                                onClick = {
                                    CoroutineScope(Dispatchers.IO).launch {

                                        val builder = CustomTabsIntent.Builder()
                                        val customTabsIntent = builder.build()

                                        // Modify the appearance of the Chrome Custom Tab if needed
                                        // builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                                        Log.i("report_pdf","http://129.154.45.152:8000/static/report_pdf?hash=${GlobalStaticClass.apkHash}")
                                        customTabsIntent.launchUrl(context, Uri.parse("http://129.154.45.152:8000/static/report_pdf?hash=${GlobalStaticClass.apkHash}"))
                                    }
                                },
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


suspend fun savePdfToFile(pdfBytes: ByteArray?) {
    if (pdfBytes != null) {
        withContext(Dispatchers.IO) {
            try {
                val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                storageDir.mkdirs()

                // Create a File object with a unique name (e.g., timestamp)
                val pdfFile = File(storageDir, "downloaded_file.pdf")

                // Use Okio to write the bytes to the file
                pdfFile.sink().buffer().use { it.write(pdfBytes) }

                withContext(Dispatchers.Main) {
                    // Notify the user that the PDF has been downloaded and saved
                    // You can use a state variable to display a message in your UI
                }
            } catch (e: IOException) {
                // Handle IO exceptions
                e.printStackTrace()
            }
        }
    }
}