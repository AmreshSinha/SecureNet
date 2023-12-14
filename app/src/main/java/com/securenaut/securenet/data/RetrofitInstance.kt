package com.securenaut.securenet.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://129.154.45.152:8001"

    // Create an OkHttpClient with an interceptor
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor()) // Add your custom interceptor
        .build()

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        // Add Authorization header to the request
        val requestBuilder: Request.Builder = chain.request().newBuilder()
            .header(
                "Authorization",
                "c52cd00b9850b05fbc906ef9205afae6dcec6f4cca5b4c8ec7fced5c9d46864f"
            )

        // Proceed with the modified request
        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}
