package com.securenaut.securenet

import android.content.Intent
import android.net.Uri
import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.securenaut.securenet.data.GlobalStaticClass
import com.securenaut.securenet.data.IPData
import com.securenaut.securenet.data.IPDataDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

interface HttpCallback {
    fun onSuccess(response: String?)
    fun onFailure(error: Exception?)
}

class HttpWorker {
    private val client = OkHttpClient.Builder().build()
    private var globalContext: Context? = null
    fun setContext(context: Context){
        globalContext = context
    }
    fun get(url: String, query: String){
        try{
            val request: Request = Request.Builder().url(url + query).get().build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    callback.onFailure(e)
                }
                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val respStr = response.body?.string()!!
                        Log.d("Response", respStr)
                        val application = globalContext?.applicationContext
                        val db = IPDataDatabase.getInstance(application!!)
                        Log.d("Response", "Got DB")
                        val dao = db.ipDataDao()
                        Log.d("Response", "Got Dao")
                        var jsonObj: JSONObject
                        try {
                            jsonObj = JSONObject(respStr)
                            Log.d("Response", "Parsed to JSON")
                            var isMal: Boolean = false
                            Log.d("Response", "Before Checking")
                            if (jsonObj["type"] == "ip") {
                                Log.d("Response", "Inside IP")
                                if (jsonObj["is_known_attacker"] == true || jsonObj["is_known_abuser"] == true || jsonObj["is_threat"] == true) {
                                    isMal = true
                                }
                                Log.d("Response", "IP Finishing")
                            } else if (jsonObj["type"] == "domain") {
                                Log.d("Response", "Inside Domain")
                                val score = jsonObj["score"]

                                when (score) {
                                    is Int -> {
                                        if (score < 0) {
                                            isMal = true
                                        }
                                    }
                                    is Double -> {
                                        if (score < 0.0){
                                            isMal = true
                                        }
                                    }
                                }
                                Log.d("Response", "Domain Finishing")
                            }
                            Log.d("Response", "Here...")
                            if (isMal) {
                                Log.d("Response", "Threat detected :: ${respStr}!!")
                                val obj = jsonObj["request"] as org.json.JSONObject
                                val ipData = IPData(
                                    id = 0,
                                    packageName = obj.optString("package", null),
                                    ip = obj.optString("ip", null),
                                    domain = obj.optString("domain", null),
                                    port = obj.optInt("port", -1),
                                    proto = obj.optInt("protocol", -1),
                                    timestamp = System.currentTimeMillis()
                                )
                                GlobalScope.launch {
                                    dao.addIPData(ipData)
                                }
                            }
                            Log.d("Response", "End:" + jsonObj.toString())
                        } catch (ex: Exception) {
                            Log.i("Response", ex.toString())
                            Log.i("Response", ex.stackTraceToString())
                        }
                    }
                }
            })
        }catch (e: Exception) {
            Log.e("HttpWorkerError",e.toString())
            Log.e("HttpWorkerStackTrace",e.stackTraceToString())
//            callback.onFailure(e)
            }
    }
    fun post(url: String, body: String, ) {
//              callback: HttpCallback) {
        try {
            val request: Request = Request.Builder().url(url).post(body.toRequestBody()).build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    callback.onFailure(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.i("Response", response.body?.string()!!)
                    val application = globalContext?.applicationContext
                    val db = IPDataDatabase.getInstance(application!!)
                    val dao = db.ipDataDao()
                    val jsonObj = JSONObject(response.body?.string())
                    var isMal: Boolean = false
                    if(jsonObj["type"] == "ip"){
                        if (jsonObj["is_known_attacker"] == true || jsonObj["is_known_abuser"] == true || jsonObj["is_threat"] == true){
                            isMal = true
                        }
                    }else if(jsonObj["type"] == "ip"){
                        if ((jsonObj["score"] as Int) < 0){
                            isMal = true
                        }
                    }
                    if(isMal){
                        var obj = jsonObj["request"] as Map<String, Any>
                        val ipData = IPData(id=0, packageName=obj["package"] as String, ip=obj["ip"] as String, domain = obj["domain"] as String, port = obj["port"] as Int, proto = obj["protocol"] as Int, timestamp = System.currentTimeMillis())
                        GlobalScope.launch {
                            dao.addIPData(ipData)
                        }
                    }
                    Log.i("Response", "End:" + jsonObj.toString())
//                    callback.onSuccess(response.body?.string())
                }
            })
        } catch (e: Exception) {
//            callback.onFailure(e)
        }
    }

    suspend fun postApk(apkFile: File,hash: String){

        withContext(Dispatchers.IO) {
            var jsonObjString : String=""
            var jsonObj : JSONObject

            val scanRequest: Request = Request.Builder()
                .url("http://129.154.45.152:8000/static/report_json?hash=${hash}")
                .get()
                .build()

            GlobalStaticClass.staticAnalysisReportString=""

            client.newCall(scanRequest).execute().use { response ->
                // Check if the request was successful (HTTP 200-299)
//                Log.i("api_resp", response.body?.string().toString())
//                Log.i("api_resp_message", response.message)

                if(response.code==200){
                    val responseData = response.body?.string()
                    println("Received data: $responseData")
                    jsonObj = JSONObject(responseData)
                    GlobalStaticClass.staticAnalysisReport = jsonObj
                    GlobalStaticClass.apkHash = hash
                    GlobalStaticClass.staticAnalysisReportString=responseData!!
                }
            }

            if(GlobalStaticClass.staticAnalysisReportString!="") return@withContext

            Log.i("POST_APK_REQ","MAKING REQUEST FOR APK")
            // Build the multipart request body
            val uploadRequestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM) // Set the media type to multipart form data
                .addFormDataPart(
                    "file", "base.apk",
                    okhttp3.RequestBody.create("multipart/form-data".toMediaTypeOrNull(), apkFile)
                )
                .build()

            // Create a Request object with the necessary headers and the multipart form data
            val uploadRequest = Request.Builder()
                .url("http://129.154.45.152:8000/static/upload")
                .post(uploadRequestBody)
                .build()

            // Use the OkHttpClient to send the request

            Log.i("upload_response","MAKING REQUEST FOR APK")

            client.newCall(uploadRequest).execute().use { response ->

                response.body?.string()?.let { Log.i("sa_resp", it) }

                // Check if the request was successful (HTTP 200-299)
//                Log.i("api_resp", response.body.toString())
//                Log.i("api_resp_message", response.message)
//                Log.i("api_resp_code", "Resp code: ${response.code}")
                jsonObj = JSONObject(response.body?.string())["static"] as JSONObject

                Log.i("json_obj",jsonObj.toString())

            }

            GlobalStaticClass.staticAnalysisReport = jsonObj
            GlobalStaticClass.apkHash = jsonObj["md5"] as String

            Log.i("md5_hash",GlobalStaticClass.apkHash)

            Log.i("making_scan_api_call", "http://129.154.45.152:8000/static/report_json?hash=${jsonObj["md5"] as String}")

            getReport(hash = (jsonObj["md5"] as String))

            // Create a request with the API endpoint and the form body
//            val scanRequest: Request = Request.Builder()
//                .url("https://securenet.photoai.pro/static/report_json?hash=${jsonObj["md5"] as String}")
//                .get()
//                .build()
//
//            client.newCall(scanRequest).execute().use { response ->
//                // Check if the request was successful (HTTP 200-299)
////                Log.i("api_resp", response.body?.string().toString())
////                Log.i("api_resp_message", response.message)
//                Log.i("api_resp_code", "Resp code: ${response.code}")
////
////                Log.i("api_resp", response.body?.string().toString())
//
//                jsonObjString=response.body?.string()!!
//
//                Log.i("resp_body",jsonObjString)
//
////                    if(response.body?.string()!=null) jsonObjString= response.body?.string()!!
//                jsonObjString
//            }
//            Log.i("json_resp","$jsonObj")
//            jsonObjString
        }
    }

    fun reportLongPolling(hash: String){

        val scanRequest: Request = Request.Builder()
            .url("http://129.154.45.152:8000/static/report_json?hash=${hash}")
            .get()
            .build()

        client.newCall(scanRequest).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.code==200) {
                    // Handle the response data
                    val responseData = response.body?.string()
                    println("Received data: $responseData")
                    GlobalStaticClass.staticAnalysisReportString=responseData!!
                }
                else{
                    Log.i("longpolling","yes")
                    reportLongPolling(hash)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                // Handle failure, such as connection issues
                e.printStackTrace()

                // Retry long polling after a delay
                reportLongPolling(hash)
            }
        })

    }

    suspend fun getReport(hash: String){

        // Define your form data (key-value pairs)
//            val formData = mapOf(
//                "file" to appData["apkFile"]
//            )

        Log.i("apk_hash",hash)

        withContext(Dispatchers.IO){
            reportLongPolling(hash = hash)
        }

//        return withContext(Dispatchers.IO) {
//
//            // Create a request with the API endpoint and the form body
//            val scanRequest: Request = Request.Builder()
//                .url("https://securenet.photoai.pro/static/report_json?hash=${hash}")
//                .get()
//                .build()
//
//            client.newCall(scanRequest).execute().use { response ->
//                // Check if the request was successful (HTTP 200-299)
////                Log.i("api_resp", response.body?.string().toString())
////                Log.i("api_resp_message", response.message)
//
//                Log.i("api_resp_code", "Resp code: ${response.code}")
////
////                Log.i("api_resp", response.body?.string().toString())
//                val jsonObjString= response.body?.string().toString()
//                jsonObjString
//            }
////            Log.i("json_resp","$jsonObj")
////            jsonObjString
//        }
    }



//    suspend fun downloadReportPdf(hash: String): Response {
////        val client = OkHttpClient()
////
////        // Build the request body with the hash parameter
////        val requestBody = "{\"hash\": \"$hash\"}".toRequestBody("application/json".toMediaTypeOrNull())
////
////        // Build the request
////        val request = Request.Builder()
////            .url("http://129.154.45.152:8001/api/v1/download_pdf")
////            .post(requestBody)
////            .build()
//
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://129.154.45.152:8001/api/v1/download_pdf?hash=${hash}"))
//
//        // Start the activity with the intent
//        startActivity(intent)
//
//
//    }

    suspend fun setFCMToken(token: String){
        val client = OkHttpClient()

        // Build the request body with the hash parameter
        val requestBody = "{\"token\": \"$token\"}".toRequestBody("application/json".toMediaTypeOrNull())

        // Build the request
        val request = Request.Builder()
            .url("http://129.154.45.152:8000/fcm")
            .post(requestBody)
            .build()

        // Make the API call
        val resp = client.newCall(request).execute()
        resp.body?.let { Log.i("set_fcm", it.string()) }
    }

}