package com.securenaut.securenet.data

import com.securenaut.securenet.data.models.scannedApps.RecentScannedAppsDetails
import com.securenaut.securenet.data.models.scorecard.ScoreCard
import com.securenaut.securenet.data.models.scorecard.ScoreCardRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @GET("/api/v1/scans")
    suspend fun getRecentScannedAppsDetails(): RecentScannedAppsDetails

    @POST("/api/v1/scorecard")
    suspend fun getScorecard(
        @Body request: ScoreCardRequest
    ): ScoreCard
}