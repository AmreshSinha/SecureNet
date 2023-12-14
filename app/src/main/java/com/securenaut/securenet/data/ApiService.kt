package com.securenaut.securenet.data

import retrofit2.http.GET

interface ApiService {
    @GET("/api/v1/scans")
    suspend fun getRecentScannedAppsDetails():  RecentScannedAppsDetails
}