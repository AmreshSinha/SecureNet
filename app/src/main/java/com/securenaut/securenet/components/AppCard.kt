package com.securenaut.securenet.components

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.google.gson.JsonParser
import com.securenaut.securenet.HttpWorker
import com.securenaut.securenet.R
import com.securenaut.securenet.data.GlobalStaticClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.security.DigestInputStream
import java.security.MessageDigest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppCard(navController: NavController, appName: String, packageName: String, apkHash: String, lastScan: String, appIconDrawable: Drawable, apkFile: File) {
    var isLoading by remember { mutableStateOf(false) }

    var lastDone: String?=GlobalStaticClass.sharedPrefInstance.getString(apkHash,null)

    if(isLoading) Text(text = "")

    Card(
        onClick = {

            GlobalStaticClass.apkFile=apkFile
            GlobalStaticClass.appIconDrawable = appIconDrawable
            GlobalStaticClass.appName=appName
            GlobalStaticClass.apkHash=apkHash
            GlobalStaticClass.packageName=packageName
            GlobalStaticClass.lastScan=lastScan

            GlobalScope.launch(Dispatchers.Main) {
                Log.i("card_button_clicked: ","$appName")
                navController.navigate("prelimnaryCheck")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 8.dp)

    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            appIconDrawable?.let {
                Image(
                    painter = rememberImagePainter(data = it),
                    contentDescription = "App Icon",
                    modifier = Modifier.size(48.dp)
                )
                Column {
                    appName?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(start = 16.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Text(
                        text = if(lastDone==null) "Never Scanned" else "Last scanned on $lastDone",
                        modifier = Modifier
                            .padding(start = 14.dp),
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.titleSmall
                    )
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
        }
    }
}