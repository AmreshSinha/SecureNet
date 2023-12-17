package com.securenaut.securenet.components
import AppBar
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.securenaut.securenet.R

@Composable
fun HorizontalScrollScreen(navController: NavController) {
    val items = (1..9).map { "Item $it" }
        AppBar(navController = navController, name = "SecureNet", onBackScreen = "home")
        Box(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                state = rememberLazyListState()
            ) {
                itemsIndexed(items) { index, item ->
                    SplashCard(index, navController)
                }
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun SplashCard(index: Int, navController: NavController) {

    val titleList = listOf(
        "Warning Signs",
        "Unrecognised apps",
        "Calls or messages you have not initiated",
        "Inappropriate pop-ups",
        "Battery draining quickly",
        "Poor performance",
        "Unusually High Data Usage",
        "Unusual activity on social accounts",
        "Protect Yourself From Hackers"
    )
    val imgList = listOf(
        R.drawable.img,
        R.drawable.img_1,
        R.drawable.img_2,
        R.drawable.img_3,
        R.drawable.img_4,
        R.drawable.img_5,
        R.drawable.img_6,
        R.drawable.img_7,
        R.drawable.img_8
    )
    val contentList = listOf(
        "Have you ever wondered \"Is my phone hacked?\" Here are some signs",
        "If you notice any unrecognised applications downloaded on your device, it could be the work of a hacker.",
        "If there are unknown calls and messages initiated from your phone, it could indicate that your device has been hacked.",
        "If you see inappropriate or X-rated advertisements pop-ups on your mobile phone, it could suggest that your phone has been compromised.",
        "It uses way more resources and battery power and becomes hotter than usual. Malware working in the background might reduce its power significantly.",
        "If your phone shows sluggish performance like crashing of apps, freezing of the screen and unexpected restarts, it is a sign of a hacked device.",
        "If you see inappropriate or X-rated advertisements pop-ups on your mobile phone, it could suggest that your phone has been compromised.",
        "If there are unrecognised activities on your social media or emails account that are connected to your phone, it could mean that a hacker has gained access to the device and it could lead to identity theft.",
        "Feeling uneasy? Don't be! SecureNaut knows how to improve your cybersecurity no matter what device you use."
    )
    val vertical = listOf(false, true, false, false, true, false, true, false, false)

        Column(
            modifier = Modifier
                .padding(vertical = 128.dp, horizontal = 44.dp)
                .size(width = 300.dp, height = 720.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = titleList[index],
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            if (vertical[index]) {
                Image(
                    painter = painterResource(id = imgList[index]),
                    contentDescription = "",
                    modifier = Modifier.padding(vertical = 64.dp)
                )
            } else {
                Image(
                    painter = painterResource(id = imgList[index]),
                    contentDescription = "",
                    modifier = Modifier.size(400.dp)
                )
            }

            Text(
                text = contentList[index],
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }


