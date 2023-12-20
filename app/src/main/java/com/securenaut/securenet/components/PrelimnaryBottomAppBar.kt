package com.securenaut.securenet.components
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.securenaut.securenet.HttpWorker
import com.securenaut.securenet.data.GlobalStaticClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun generateSignatureHash(signature: android.content.pm.Signature?): String {
    return try {
        val md = MessageDigest.getInstance("SHA-256")
        val signatureBytes = signature?.toByteArray()

        val bytes = md.digest(signatureBytes)

        bytes.joinToString("") { "%02X".format(it) }
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
        ""
    }
}

@SuppressLint("CommitPrefEdits")
@Composable
fun PrelimnaryBottomAppBar(navController: NavController) {

    val packageInfo = LocalContext.current.packageManager.getPackageInfo(GlobalStaticClass.packageName, PackageManager.GET_SIGNATURES)

    BottomAppBar(
        floatingActionButton = {
        },
        actions = {
            Button(
                onClick = {

                    GlobalScope.launch(Dispatchers.Main) {

                        Log.i("card_button_clicked: ","${GlobalStaticClass.appName}")

                        Log.i("found_report","not report found")
                        val staticAnalysisDataString = HttpWorker().postApk(GlobalStaticClass.apkFile)

                        GlobalStaticClass.summary = HttpWorker().summarizeReport(hash = GlobalStaticClass.apkHash)
                        GlobalStaticClass.action = HttpWorker().actionReport(hash = GlobalStaticClass.apkHash)

                        Log.i("btn_press_resp",staticAnalysisDataString)

                        GlobalStaticClass.staticAnalysisReport = JSONObject(staticAnalysisDataString)

                        navController.navigate("staticAnalysis")

//                        Log.i("static_hash_apk",GlobalStaticClass.staticAnalysisReport["md5"] as String)
//
//                        val signatures: Array<out android.content.pm.Signature>? = packageInfo.signatures
//                        val signatureHash = generateSignatureHash(signatures?.get(0))
//
//                        Log.i("signatureHash",signatureHash)
//
//                        Log.i("signatureHashLength",signatureHash.length.toString())
//
//                        if(GlobalStaticClass.signaturHashMap[signatureHash]!=null){
//                            Log.i("found_hash","hash found")
//
//                            GlobalStaticClass.sharedPrefInstance.getString(signatureHash,"")
//                                ?.let { Log.i("apk_hash_saved", it) }
//
//                            val staticAnalysisDataString =
//                                GlobalStaticClass.sharedPrefInstance.getString(signatureHash,"")
//                                    ?.let { HttpWorker().getReport(it) }
//
//                            Log.i("staticAnalysisDataString",staticAnalysisDataString.toString())
//
//                            GlobalStaticClass.staticAnalysisReport = JSONObject(staticAnalysisDataString)
//                        }
//                        else {
//                            Log.i("found_report","not report found")
//                            val staticAnalysisDataString = HttpWorker().postApk(GlobalStaticClass.apkFile)
//
//                            Log.i("btn_press_resp",staticAnalysisDataString)
//
//                            GlobalStaticClass.staticAnalysisReport = JSONObject(staticAnalysisDataString)
//
//                            Log.i("static_hash_apk",GlobalStaticClass.staticAnalysisReport["md5"] as String)
////
////                            GlobalStaticClass.sharedPrefInstance.edit().putString(signatureHash,
////                                GlobalStaticClass.staticAnalysisReport["md5"] as String
////                            )
//
//                            GlobalStaticClass.signaturHashMap[signatureHash] = GlobalStaticClass.staticAnalysisReport["md5"] as String
//
////
////                            GlobalStaticClass.sharedPrefInstance.getString(signatureHash,"")
////                                ?.let { Log.i("apk_hash_saved", it) }
//
//                        }
//                        navController.navigate("staticAnalysis")
                    }

                },
                modifier = Modifier.padding(horizontal = 16.dp),

                ) {Text(
                text = "Upload App for Static Analysis",
                color = Color.White,

                )}
        },
    )
}