import android.annotation.SuppressLint
import android.content.pm.ModuleInfo
import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.securenaut.securenet.ui.theme.Typography

@Composable
fun Component(color : Color, content: Color, image: ImageVector, text: String ) {
      Button(
          onClick = {},
          colors =  ButtonDefaults.buttonColors(
              containerColor = color,
              contentColor = content),
      ) {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Icon(image, "Floating action button.", tint = content, modifier = Modifier.size(16.dp))
                Text(
                    text = text,
                    color = content,
                    modifier = Modifier.padding(start = 4.dp),
                    style = MaterialTheme.typography.labelSmall
                );
            }
      }
}
@SuppressLint("UnrememberedMutableState")
@Composable
fun Dropdown(type: String, title: String, subtitle: String, description: String) {
    var isClicked by remember { mutableStateOf(false) }

    val map: Map<String, @Composable (String) -> Unit> = mapOf(
        "warning" to {Component(color = Color(0xFFFFC008), image = Icons.Filled.Warning, text = "Warning" , content = Color.Black) },
        "secure" to {Component(color = Color(0xFF28A745), image = Icons.Filled.Check, text = "Secure", content = Color.White) },
        "info" to {Component(color = Color(0xFF14A2B8), image = Icons.Filled.Info, text = "Info", content = Color.White ) },
        "high" to {Component(color = Color(0xFFDC3545), image = Icons.Filled.Lock, text = "High", content = Color.White )}
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)

    ){
        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable{isClicked = !isClicked}
        ){
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier= Modifier.fillMaxWidth(fraction = 0.9f)) {
                    Text(text = title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    map[type]?.invoke(type)
                }

                if(isClicked) {
                    Icon(Icons.Filled.KeyboardArrowUp, "")
                }
                else{
                    Icon(Icons.Filled.KeyboardArrowDown, "")
                }

            }
            Text(text = subtitle,style= Typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant);
        }
        if(isClicked) {
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Divider(color = Color.Gray, thickness = 1.dp)

                Text(
                    text = description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(vertical = 8.dp)

                )
            }
        }

    }

}

















