package com.securenaut.securenet

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
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
}
