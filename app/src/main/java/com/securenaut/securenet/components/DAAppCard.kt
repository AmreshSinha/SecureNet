package com.securenaut.securenet.components

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.securenaut.securenet.HttpWorker
import com.securenaut.securenet.R
import com.securenaut.securenet.data.GlobalStaticClass
import com.securenaut.securenet.data.IPData
import com.securenaut.securenet.pages.DAReportScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

@Composable
fun rememberDrawablePainter(drawable: Drawable): Painter {
    return remember(drawable) {
        DrawablePainter(drawable)
    }
}
class DrawablePainter(private val drawable: Drawable) : Painter() {
    override val intrinsicSize: Size
        get() = Size(drawable.intrinsicWidth.toFloat(), drawable.intrinsicHeight.toFloat())

    override fun DrawScope.onDraw() {
        drawable.setBounds(0, 0, size.width.toInt(), size.height.toInt())
        drawable.draw(drawContext.canvas.nativeCanvas)
    }
}
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DAAppCard(appName:String,lastScan:String,ipData: IPData, appIcon: Drawable, navController: NavController) {
    var isClicked by remember { mutableStateOf(false) }

    val appIconDrawable = remember { appIcon }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        onClick = {
            isClicked = !isClicked
        },

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 4.dp)

    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
                Image(
                    painter = rememberDrawablePainter(appIconDrawable),
                    contentDescription = "App Icon",
                    modifier = Modifier.size(48.dp)
                )
                Column {
                    appName?.let {
                        Text(
                            text = appName,
                            modifier = Modifier
                                .padding(start = 16.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    lastScan?.let {
                        Text(
                            text = "Last scanned on $lastScan",
                            modifier = Modifier
                                .padding(start = 14.dp),
                            textAlign = TextAlign.Center,
                            color = Color.DarkGray,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    AsyncImage(
                        model = "",
                        placeholder = painterResource(id = R.drawable.icon),
                        error = painterResource(id = R.drawable.arrow),
                        contentDescription = "The delasign logo",
                    )
                }
            }
        if(isClicked) {
            DAReportScreen(navController = navController, ipData)
        }

    }
    }
