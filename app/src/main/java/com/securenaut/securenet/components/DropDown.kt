import android.content.pm.ModuleInfo
import android.graphics.drawable.Icon
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

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
@Composable
fun Dropdown(type: String, title: String) {
    val map: Map<String, @Composable (String) -> Unit> = mapOf(
        "medium" to {Component(color = Color(0xFFFFC008), image = Icons.Filled.Warning, text = "Medium" , content = Color.Black) },
        "secure" to {Component(color = Color(0xFF28A745), image = Icons.Filled.Check, text = "Secure", content = Color.White) },
        "info" to {Component(color = Color(0xFF14A2B8), image = Icons.Filled.Info, text = "Info", content = Color.White ) },
        "high" to {Component(color = Color(0xFFDC3545), image = Icons.Filled.Lock, text = "High", content = Color.White )}
    )

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)

    ){
        Column (modifier = Modifier.fillMaxWidth().padding(8.dp)){
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier= Modifier.fillMaxWidth(fraction = 0.9f)) {
                    Text(text = title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    map[type]?.invoke(type)
                }
                Icon(Icons.Filled.ArrowDropDown, "")
            }
            Text(text = "This application has no privacy trackers", color = MaterialTheme.colorScheme.onSurfaceVariant);
        }
    }

}

















