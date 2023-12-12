package com.securenaut.securenet

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.securenaut.securenet.pages.AppsStaticList
import com.securenaut.securenet.ui.theme.Black
import com.securenaut.securenet.ui.theme.CardBorder
import com.securenaut.securenet.ui.theme.Purple
import com.securenaut.securenet.ui.theme.SecureNetTheme
import com.securenaut.securenet.ui.theme.Typography
import com.securenaut.securenet.ui.theme.White

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecureNetTheme {
                HomeAppBar(this@HomeActivity)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(context: Context) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }
                },
                title = {
                    Surface(modifier = Modifier.fillMaxWidth()) {
                        Row(horizontalArrangement = Arrangement.Center) {
                            Text(
                                "SecureNet",
                                style = Typography.headlineSmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Open Settings"
                        )
                    }
                }
            )

        }
    ) { contentPadding ->
        // Screen content
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).padding(contentPadding)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                OutlinedCard(
                    border = BorderStroke(0.5.dp, Color.Black),
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = CardBorder,
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                    ){
                        Row(modifier = Modifier
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(0.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Column(modifier = Modifier.weight(1f)){
                                Text(text = "Your device has", style = Typography.bodyMedium)
                                Text(text = "17 Threats", style = Typography.headlineMedium)
                            }
                            Image(
                                painter = painterResource(id = R.drawable.threat_low),
                                contentDescription = "My Image",
                                modifier = Modifier.fillMaxHeight()
                            )
                        }
                        Text(text = "Last scan performed 3 hours ago.", style = Typography.bodyMedium)
                        Row (modifier = Modifier
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                            ) {
                            Button(onClick = {
                                val intent = Intent(context, AppsStaticList::class.java)
                                startActivity(context, intent, null)
                            }) {
                                Text(
                                    text = "Scan",
                                    style = Typography.bodyMedium,
                                    color = White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    SecureNetTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                }) {
                    Text(text = "Apps Static List")
                }
            }
        }
    }
}