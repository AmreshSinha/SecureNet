package com.securenaut.securenet

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.securenaut.securenet.data.GlobalStaticClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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
    private val client = OkHttpClient.Builder()
        .readTimeout(600, TimeUnit.SECONDS) // Set read timeout to 10 seconds
        .writeTimeout(600, TimeUnit.SECONDS) // Set write timeout to 10 seconds
        .connectTimeout(600, TimeUnit.SECONDS).build()
    fun post(url: String, body: String, ) {
//              callback: HttpCallback) {
        try {
            val request: Request = Request.Builder().url(url).post(body.toRequestBody()).build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    callback.onFailure(e)
                }

                override fun onResponse(call: Call, response: Response) {
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