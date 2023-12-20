package com.securenaut.securenet

import android.content.Context
import android.util.Log
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
    private val client = OkHttpClient.Builder()
        .connectTimeout(600, TimeUnit.SECONDS) // 30 seconds connect timeout
        .readTimeout(600, TimeUnit.SECONDS)    // 30 seconds read timeout
        .writeTimeout(600, TimeUnit.SECONDS)   // 30 seconds write timeout
        .callTimeout(600, TimeUnit.MINUTES) .build()
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
                        var latitude: Double? = null
                        var longitude: Double? = null
                        var asnId: String? = null
                        var asnName: String? = null
                        var domainScore: Double? = null
                        try {
                            jsonObj = JSONObject(respStr)
                            Log.d("Response", "Parsed to JSON")
                            var isMal: Boolean = false
                            Log.d("Response", "Before Checking")
                            if (jsonObj["type"] == "ip" && jsonObj.optJSONObject("threat") != null) {
                                Log.d("Response", "Inside IP")
                                val threat = jsonObj["threat"] as JSONObject
                                if (threat["is_known_attacker"] == true || threat["is_known_abuser"] == true || threat["is_threat"] == true) {
                                    isMal = true
                                }
                                if(!jsonObj.optDouble("latitude").isNaN()){
                                    latitude = jsonObj["latitude"] as Double
                                }
                                if(!jsonObj.optDouble("longitude").isNaN()){
                                    longitude = jsonObj["longitude"] as Double
                                }
                                if(jsonObj.optJSONObject("asn") != null){
                                    val asn = jsonObj["asn"] as JSONObject
                                    asnId = asn.optString("asn")
                                    asnName = asn.optString("name")
                                }
                                Log.d("Response", "IP Finishing")
                            } else if (jsonObj["type"] == "domain") {
                                Log.d("Response", "Inside Domain")
                                val score = jsonObj["score"]
                                when (score) {
                                    is Int -> {
                                        if (score < 0) {
                                            isMal = true
                                            domainScore = score.toDouble()
                                        }
                                    }
                                    is Double -> {
                                        if (score < 0.0){
                                            isMal = true
                                            domainScore = score
                                        }
                                    }
                                }
                                Log.d("Response", "Domain Finishing")
                            }
                            Log.d("Response", "Here...")
                            if (isMal) {
                                Log.d("Response", "Threat detected :: ${respStr}!!")
                                val obj = jsonObj["request"] as org.json.JSONObject
                                val pkgName = obj.optString("package")
                                if(pkgName == "" || pkgName == "null"){
                                    return
                                }
                                val ipData = IPData(
                                    id = 0,
                                    packageName = pkgName,
                                    ip = obj.optString("ip", null),
                                    domain = obj.optString("domain", null),
                                    port = obj.optInt("port", -1),
                                    proto = obj.optInt("protocol", -1),
                                    timestamp = System.currentTimeMillis(),
                                    latitude = latitude,
                                    longitude = longitude,
                                    score = domainScore,
                                    asnId = asnId,
                                    asnName = asnName
                                )
                                Log.d("pkgadd", pkgName)
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
                .addFormDataPart(
                    "file", "base.apk",
                    okhttp3.RequestBody.create("multipart/form-data".toMediaTypeOrNull(), apkFile)
                )
                .build()

            // Create a Request object with the necessary headers and the multipart form data
            val uploadRequest = Request.Builder()
                .url("https://securenet.photoai.pro/static/upload")
                .post(uploadRequestBody)
                .build()

            // Use the OkHttpClient to send the request

            Log.i("upload_response","MAKING REQUEST FOR APK")

            var jsonObj : JSONObject

            client.newCall(uploadRequest).execute().use { response ->

                Log.i("sa_resp",response.toString())

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

            Log.i("making_scan_api_call", "https://securenet.photoai.pro/static/report_json?hash=${jsonObj["md5"] as String}")

            // Create a request with the API endpoint and the form body
            val scanRequest: Request = Request.Builder()
                .url("https://securenet.photoai.pro/static/report_json?hash=${jsonObj["md5"] as String}")
                .get()
                .build()

            client.newCall(scanRequest).execute().use { response ->
                // Check if the request was successful (HTTP 200-299)
//                Log.i("api_resp", response.body?.string().toString())
//                Log.i("api_resp_message", response.message)
                Log.i("api_resp_code", "Resp code: ${response.code}")
//
//                Log.i("api_resp", response.body?.string().toString())

                jsonObjString=response.body?.string()!!

                Log.i("resp_body",jsonObjString)

//                    if(response.body?.string()!=null) jsonObjString= response.body?.string()!!
                jsonObjString
            }
//            Log.i("json_resp","$jsonObj")
//            jsonObjString
        }
    }

    suspend fun getReport(hash: String): String {

        // Define your form data (key-value pairs)
//            val formData = mapOf(
//                "file" to appData["apkFile"]
//            )

        Log.i("apk_hash",hash)

        return withContext(Dispatchers.IO) {

            // Create a request with the API endpoint and the form body
            val scanRequest: Request = Request.Builder()
                .url("https://securenet.photoai.pro/static/report_json?hash=${hash}")
                .get()
                .build()

            client.newCall(scanRequest).execute().use { response ->
                // Check if the request was successful (HTTP 200-299)
//                Log.i("api_resp", response.body?.string().toString())
//                Log.i("api_resp_message", response.message)
                Log.i("api_resp_code", "Resp code: ${response.code}")
//
//                Log.i("api_resp", response.body?.string().toString())
                val jsonObjString= response.body?.string().toString()
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
        withContext(Dispatchers.IO){

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

    suspend fun summarizeReport(hash: String): String{
        return withContext(Dispatchers.IO){

            // Build the request
            val request = Request.Builder()
                .url("https://securenet.photoai.pro/gemini/summary?hash=${hash}")
                .get()
                .build()

            // Make the API call
            val resp = client.newCall(request).execute()

            resp.body?.string()!!
        }
    }

    suspend fun actionReport(hash: String): String{
        return withContext(Dispatchers.IO){

            // Build the request
            val request = Request.Builder()
                .url("https://securenet.photoai.pro/gemini/action?hash=${hash}")
                .get()
                .build()

            // Make the API call
            val resp = client.newCall(request).execute()

            resp.body?.string()!!
        }
    }

}