import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.securenaut.securenet.R
import com.securenaut.securenet.components.AppCard
import com.securenaut.securenet.components.HomeAppBar
import com.securenaut.securenet.data.GlobalStaticClass
import com.securenaut.securenet.pages.getGrantedPermissions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


@Composable
fun StaticAnalysisAppList(navController: NavController) {

    // Observe the data from the view model
    AppBar(navController = navController, name = "Static Analysis", onBackScreen = "home")
    Column(
        modifier = Modifier
            .padding(top = 64.dp, start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        val packageManager: PackageManager = LocalContext.current.packageManager
        val installedApplications =
            packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        var appDataList: MutableList<MutableMap<String, Any>> = mutableListOf()
        val installedApps = installedApplications.filter { appInfo ->
            appInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0
        }

        Log.i("count_apps", installedApps.size.toString())

        for (appInfo in installedApps) {
            try {
                val appName = appInfo.loadLabel(packageManager).toString()
                val packageName = appInfo.packageName
                Log.i("src_dir", "Source dir : " + appInfo.sourceDir);
                Log.i("package_name", "$packageName")
                val packageInfo = packageManager.getPackageInfo(packageName, 0)
                val sourceDir = packageInfo.applicationInfo.sourceDir
                GlobalStaticClass.srcDir=sourceDir
                val apkFile = File(sourceDir)
                val grantedPermissions = getGrantedPermissions(packageName, packageManager)
                GlobalStaticClass.appPermissions=grantedPermissions
                val appIconDrawable = appInfo.loadIcon(packageManager)
                Log.i("app_name", "$appName")
                Log.i(
                    "file_found",
                    apkFile.name + " " + apkFile.absolutePath + " " + appIconDrawable.toString()
                )
                appDataList.add(
                    mapOf(
                        "appName" to appName,
                        "appIconDrawable" to appIconDrawable,
                        "apkFile" to apkFile,
                        "packageName" to packageName
                    ) as MutableMap<String, Any>
                )
            } catch (e: PackageManager.NameNotFoundException) {
                Log.i("app_err", e.message.toString())
                // Handle the exception if the package is not found
            }
        }

//        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom= 6.dp)) {
//            Text(
//                text = "Threat detected in  ",
//                style = MaterialTheme.typography.bodyMedium
//            )
//            AsyncImage(
//                model = "",
//                placeholder = painterResource(id = R.drawable.arrowdown),
//                error = painterResource(id = R.drawable.arrowdown),
//                contentDescription = "The delasign logo",
//            )
//        }

        for (appData in appDataList) {
            AppCard(
                navController = navController,
                appName = appData["appName"] as String,
                packageName = appData["packageName"] as String,
                lastScan = "7th May 2023",
                appIconDrawable = appData["appIconDrawable"] as Drawable,
                apkFile = appData["apkFile"] as File
            )
        }

//        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top= 16.dp, bottom= 6.dp)) {
//            Text(
//                text = "Safe Apps  ",
//                style = MaterialTheme.typography.bodyMedium
//
//            )
//            AsyncImage(
//                model = "",
//                placeholder = painterResource(id = R.drawable.arrowdown),
//                error = painterResource(id = R.drawable.arrowdown),
//                contentDescription = "The delasign logo",
//            )
//        }

//        DisposableEffect(Unit) {
//            viewModel.getRecentScannedAppsDetails()
//            Log.d("lostofapp", "disposible: called")
//            onDispose { }
//        }

    }
}

//@Preview
//@Composable
//fun StaticAnalysisAppList1()
//{
//    Scaffold(
//        topBar = {
//            AppBar(name = "Static Analysis")
//        }
//    ){
//        contentPadding -> Surface(
//            modifier = Modifier.padding(contentPadding)
//        ){
//        Column (
//            modifier = Modifier
//                .verticalScroll(rememberScrollState())
//                .padding(start = 16.dp, end = 16.dp)
//        ){
//            Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom= 6.dp)) {
//                Text(
//                    text = "Threat detected in  ",
//                    style = MaterialTheme.typography.bodyMedium
//                )
//                AsyncImage(
//                    model = "",
//                    placeholder = painterResource(id = R.drawable.arrowdown),
//                    error = painterResource(id = R.drawable.arrowdown),
//                    contentDescription = "The delasign logo",
//                )
//            }
//
//            AppCard(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
//            AppCard(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "1st Feb 2023")
//            AppCard(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "3rd Feb 2023")
//
//
//            Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top= 16.dp, bottom= 6.dp)) {
//                Text(
//                    text = "Safe Apps  ",
//                    style = MaterialTheme.typography.bodyMedium
//
//                )
//                AsyncImage(
//                    model = "",
//                    placeholder = painterResource(id = R.drawable.arrowdown),
//                    error = painterResource(id = R.drawable.arrowdown),
//                    contentDescription = "The delasign logo",
//                )
//            }
//            AppCard(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
//            AppCard(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
//            AppCard(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
//            AppCard(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
//            AppCard(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
//            AppCard(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
//            AppCard(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
//            AppCard(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
//            AppCard(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
//            AppCard(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
//            AppCard(name = "Instagram", imageUrl = "https://www.figma.com/file/XkWwY3inOCWMVKhNdE6L6E/SIH-'23?type=design&node-id=256-3490&mode=design&t=IAxsfSYe8rFD6amG-4", lastScan = "7th May 2023")
//
//        }
//    }
//    }
//}
