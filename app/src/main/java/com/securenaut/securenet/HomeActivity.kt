package com.securenaut.securenet

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.securenaut.securenet.pages.AppsStaticList
import com.securenaut.securenet.ui.theme.SecureNetTheme
import com.securenaut.securenet.ui.theme.Typography

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("Inside Home")
        setContent {
            SecureNetTheme {
                Surface(modifier = Modifier.fillMaxSize()){
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally){
                        Button(onClick = {
                            val intent = Intent(this@HomeActivity,AppsStaticList::class.java)
                            startActivity(intent)
                        }) {
                            Text(text = "Apps Static List", style = Typography.headlineLarge)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    SecureNetTheme {
        Surface(modifier = Modifier.fillMaxSize()){
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Button(onClick = {
                }) {
                    Text(text = "Apps Static List")
                }
            }
        }
    }
}