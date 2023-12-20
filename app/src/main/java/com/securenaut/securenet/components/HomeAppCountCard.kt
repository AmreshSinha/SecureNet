package com.securenaut.securenet.components

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.securenaut.securenet.R
import com.securenaut.securenet.ui.theme.CardBorder
import com.securenaut.securenet.ui.theme.IconColor
import com.securenaut.securenet.ui.theme.Typography

@Composable
fun HomeAppCountCard() {

    val packageManager: PackageManager = LocalContext.current.packageManager
    val installedApplications =
        packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

    val installedApps = installedApplications.filter { appInfo ->
        appInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0
    }
Surface(modifier = Modifier.padding()) {

}
    OutlinedCard(modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 16.dp)
        .border(
            width = 1.dp,
            color = CardBorder,
            shape = RoundedCornerShape(size = 12.dp)
        )
    ){
        Row(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.padding(
                    start = 0.dp,
                    end = 20.dp,
                    top = 24.dp,
                    bottom = 24.dp
                ),
                painter = painterResource(id = R.drawable.baseline_remove_red_eye_24),
                contentDescription = "phone icon"
            )
            Column {
                Text(text = "${installedApps.size}", style = Typography.titleLarge)
                Text(text = "Total Apps", style = Typography.bodyMedium)
            }
        }
    }

//    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
//        Column (){
//            Row(modifier = Modifier.padding(16.dp),verticalAlignment = Alignment.CenterVertically) {
//                Image(
//                    modifier = Modifier.padding(
//                        start = 0.dp,
//                        end = 20.dp,
//                        top = 24.dp,
//                        bottom = 24.dp
//                    ),
//                    painter = painterResource(id = R.drawable.baseline_phone_android_24),
//                    contentDescription = "phone icon"
//                )
//                Column {
//                    Text(text = "396", style = Typography.titleLarge)
//                    Text(text = "Total Apps", style = Typography.bodyMedium)
//                }
//            }
//            Row(modifier = Modifier.padding(16.dp),verticalAlignment = Alignment.CenterVertically) {
//                Image(
//                    modifier = Modifier.padding(
//                        start = 0.dp,
//                        end = 20.dp,
//                        top = 24.dp,
//                        bottom = 24.dp
//                    ),
//                    painter = painterResource(id = R.drawable.baseline_remove_red_eye_24),
//                    contentDescription = "phone icon"
//                )
//                Column {
//                    Text(text = "12", style = Typography.titleLarge)
//                    Text(text = "Hidden Apps", style = Typography.bodyMedium)
//                }
//            }
//        }
//        Column{
//            Row(modifier = Modifier.padding(16.dp),verticalAlignment = Alignment.CenterVertically) {
//                Image(
//                    modifier = Modifier.padding(
//                        start = 0.dp,
//                        end = 20.dp,
//                        top = 24.dp,
//                        bottom = 24.dp
//                    ),
//                    painter = painterResource(id = R.drawable.baseline_attach_money_24),
//                    contentDescription = "phone icon"
//                )
//                Column {
//                    Text(text = "32", style = Typography.titleLarge)
//                    Text(text = "Paid Apps", style = Typography.bodyMedium)
//                }
//            }
//            Row(modifier = Modifier.padding(16.dp),verticalAlignment = Alignment.CenterVertically) {
//                Image(
//                    modifier = Modifier.padding(
//                        start = 0.dp,
//                        end = 20.dp,
//                        top = 24.dp,
//                        bottom = 24.dp
//                    ),
//                    painter = painterResource(id = R.drawable.baseline_error_outline_24),
//                    contentDescription = "phone icon"
//                )
//                Column {
//                    Text(text = "34", style = Typography.titleLarge)
//                    Text(text = "Dangerous Apps", style = Typography.bodyMedium)
//                }
//            }
//        }
//    }
}


@Preview(showBackground = true)
@Composable
fun HomeAppCountCard1() {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
        Column (){
            Row(modifier = Modifier.padding(16.dp),verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.padding(
                        start = 0.dp,
                        end = 20.dp,
                        top = 24.dp,
                        bottom = 24.dp
                    ),
                    painter = painterResource(id = R.drawable.baseline_phone_android_24),
                    contentDescription = "phone icon",
                    tint = IconColor
                )
                Column {
                    Text(text = "396", style = Typography.titleLarge)
                    Text(text = "Total Apps", style = Typography.bodyMedium)
                }
            }
            Row(modifier = Modifier.padding(16.dp),verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.padding(
                        start = 0.dp,
                        end = 20.dp,
                        top = 24.dp,
                        bottom = 24.dp
                    ),
                    painter = painterResource(id = R.drawable.baseline_remove_red_eye_24),
                    contentDescription = "phone icon",
                    tint = IconColor
                )
                Column {
                    Text(text = "12", style = Typography.titleLarge)
                    Text(text = "Hidden Apps", style = Typography.bodyMedium)
                }
            }
        }
        Column{
            Row(modifier = Modifier.padding(16.dp),verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.padding(
                        start = 0.dp,
                        end = 20.dp,
                        top = 24.dp,
                        bottom = 24.dp
                    ),
                    painter = painterResource(id = R.drawable.baseline_attach_money_24),
                    contentDescription = "phone icon",
                    tint = IconColor
                )
                Column {
                    Text(text = "32", style = Typography.titleLarge)
                    Text(text = "Paid Apps", style = Typography.bodyMedium)
                }
            }
            Row(modifier = Modifier.padding(16.dp),verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.padding(
                        start = 0.dp,
                        end = 20.dp,
                        top = 24.dp,
                        bottom = 24.dp
                    ),
                    painter = painterResource(id = R.drawable.baseline_error_outline_24),
                    contentDescription = "phone icon",
                    tint = IconColor
                )
                Column {
                    Text(text = "34", style = Typography.titleLarge)
                    Text(text = "Dangerous Apps", style = Typography.bodyMedium)
                }
            }
        }
    }
}