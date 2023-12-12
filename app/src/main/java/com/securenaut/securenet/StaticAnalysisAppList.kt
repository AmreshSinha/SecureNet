import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.securenaut.securenet.R

@Composable
fun StaticAnalysisAppList()
{
    CenterAlignedTopAppBarExample(name = "Static Analysis")
    Column (modifier = Modifier
        .padding(top = 64.dp, start = 16.dp, end = 16.dp)
        .verticalScroll(rememberScrollState())){
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom= 6.dp)) {
            Text(
                text = "Threat detected in  ",
                style = MaterialTheme.typography.bodyMedium
            )
            AsyncImage(
                model = "",
                placeholder = painterResource(id = R.drawable.arrowdown),
                error = painterResource(id = R.drawable.arrowdown),
                contentDescription = "The delasign logo",
            )
        }

        ElevatedCardExample(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
        ElevatedCardExample(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "1st Feb 2023")
        ElevatedCardExample(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "3rd Feb 2023")


        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top= 16.dp, bottom= 6.dp)) {
            Text(
                text = "Safe Apps  ",
                style = MaterialTheme.typography.bodyMedium

            )
            AsyncImage(
                model = "",
                placeholder = painterResource(id = R.drawable.arrowdown),
                error = painterResource(id = R.drawable.arrowdown),
                contentDescription = "The delasign logo",
            )
        }
        ElevatedCardExample(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
        ElevatedCardExample(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
        ElevatedCardExample(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
        ElevatedCardExample(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
        ElevatedCardExample(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
        ElevatedCardExample(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
        ElevatedCardExample(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
        ElevatedCardExample(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
        ElevatedCardExample(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
        ElevatedCardExample(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
        ElevatedCardExample(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")

    }
}

