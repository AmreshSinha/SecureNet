package com.securenaut.securenet

import android.util.Log
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
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File
import java.io.IOException

interface HttpCallback {
    fun onSuccess(response: String?)
    fun onFailure(error: Exception?)
}

class HttpWorker {
    private val client = OkHttpClient.Builder().build()
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

    suspend fun postApk(apkFile: File): String {

        // Define your form data (key-value pairs)
//            val formData = mapOf(
//                "file" to appData["apkFile"]
//            )

        return withContext(Dispatchers.IO) {
            Log.i("POST_APK_REQ","MAKING REQUEST FOR APK")
            var jsonObjString : String=""
            // Build the multipart request body
            val uploadRequestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM) // Set the media type to multipart form data
                .addFormDataPart("file", "base.apk",
                    apkFile.asRequestBody("application/octet-stream".toMediaType()))
                .build()

            // Create a Request object with the necessary headers and the multipart form data
            val uploadRequest = Request.Builder()
                .url("http://129.154.45.152:8001/api/v1/upload")
                .header("Authorization", "c52cd00b9850b05fbc906ef9205afae6dcec6f4cca5b4c8ec7fced5c9d46864f") // Add your headers here
                .post(uploadRequestBody)
                .build()

            // Use the OkHttpClient to send the request

            var jsonObj : JSONObject

            client.newCall(uploadRequest).execute().use { response ->
                // Check if the request was successful (HTTP 200-299)
                Log.i("api_resp", response.body.toString())
                Log.i("api_resp_message", response.message)
                Log.i("api_resp_code", "Resp code: ${response.code}")
                jsonObj = JSONObject(response.body?.string())
            }

            GlobalStaticClass.apkHash = jsonObj["hash"] as String


            val scanFormBody: RequestBody = FormBody.Builder()
                .add("hash",jsonObj["hash"] as String)
                .build()

            Log.i("making_scan_api_call", "Trying")

            // Create a request with the API endpoint and the form body
            val scanRequest: Request = Request.Builder()
                .url("http://129.154.45.152:8001/api/v1/scan")
                .header("Authorization", "c52cd00b9850b05fbc906ef9205afae6dcec6f4cca5b4c8ec7fced5c9d46864f") // Add your headers here
                .post(scanFormBody)
                .build()

            client.newCall(scanRequest).execute().use { response ->
                // Check if the request was successful (HTTP 200-299)
//                Log.i("api_resp", response.body?.string().toString())
//                Log.i("api_resp_message", response.message)
                Log.i("api_resp_code", "Resp code: ${response.code}")
//
//                Log.i("api_resp", response.body?.string().toString())
                    jsonObjString= response.body?.string().toString()
                jsonObjString
            }
//            Log.i("json_resp","$jsonObj")
//            jsonObjString
        }
    }

    suspend fun downloadReportPdf(hash: String): Response {
        val client = OkHttpClient()

        // Build the request body with the hash parameter
        val requestBody = "{\"hash\": \"$hash\"}".toRequestBody("application/json".toMediaTypeOrNull())

        // Build the request
        val request = Request.Builder()
            .url("http://129.154.45.152:8001/api/v1/download_pdf")
            .post(requestBody)
            .build()

        // Make the API call
        return client.newCall(request).execute()
    }

    suspend fun setFCMToken(token: String){
        val client = OkHttpClient()

        // Build the request body with the hash parameter
        val requestBody = "{\"token\": \"$token\"}".toRequestBody("application/json".toMediaTypeOrNull())

        // Build the request
        val request = Request.Builder()
            .url("https://securenet.photoai.pro/fcm")
            .post(requestBody)
            .build()

        // Make the API call
        val resp = client.newCall(request).execute()
        resp.body?.let { Log.i("set_fcm", it.string()) }
    }

}