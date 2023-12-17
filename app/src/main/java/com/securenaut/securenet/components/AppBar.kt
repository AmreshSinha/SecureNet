import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.securenaut.securenet.ui.theme.Typography

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(navController: NavController, name: String,onBackScreen:String) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = {navController.navigate(onBackScreen)}){Icon(Icons.Filled.ArrowBack, "Floating action button.", tint = MaterialTheme.colorScheme.primary)}
        },
        title = {
                Row(modifier = Modifier.background(color = Color.White),
                    horizontalArrangement = Arrangement.Center) {
                    Text(
                        modifier = Modifier.background(color = Color.White),
                        text = name,
                        maxLines = 1,
                        style = Typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        overflow = TextOverflow.Ellipsis
                    )
                }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White)
    )
}