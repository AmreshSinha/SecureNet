package com.securenaut.securenet.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecentScannedAppsDetails(
    @Json(name = "content")
    val content: List<Content>,
    @Json(name = "count")
    val count: Int,
    @Json(name = "num_pages")
    val numPages: Int
)