package com.securenaut.securenet.data.models.scannedApps

import com.google.gson.annotations.SerializedName
//@JsonClass(generateAdapter = true)
//data class RecentScannedAppsDetails(
//    @Json(name = "content")
//    val content: List<Content>,
//    @Json(name = "count")
//    val count: Int,
//    @Json(name = "num_pages")
//    val numPages: Int
//)
data class RecentScannedAppsDetails(
    @SerializedName("content") val content: List<ContentItem>,
    @SerializedName("count") val count: Int,
    @SerializedName("num_pages") val numPages: Int
)
data class ContentItem(
    @SerializedName("ANALYZER") val analyzer: String,
    @SerializedName("SCAN_TYPE") val scanType: String,
    @SerializedName("FILE_NAME") val fileName: String,
    @SerializedName("APP_NAME") val appName: String,
    @SerializedName("PACKAGE_NAME") val packageName: String,
    @SerializedName("VERSION_NAME") val versionName: String,
    @SerializedName("MD5") val md5: String,
    @SerializedName("TIMESTAMP") val timestamp: String
)