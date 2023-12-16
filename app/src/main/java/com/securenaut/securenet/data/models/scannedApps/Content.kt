package com.securenaut.securenet.data.models.scannedApps


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Content(
    @Json(name = "ANALYZER")
    val analyzer: String,
    @Json(name = "APP_NAME")
    val appName: String,
    @Json(name = "FILE_NAME")
    val fileName: String,
    @Json(name = "MD5")
    val md5: String,
    @Json(name = "PACKAGE_NAME")
    val packageName: String,
    @Json(name = "SCAN_TYPE")
    val scanType: String,
    @Json(name = "TIMESTAMP")
    val timestamp: String,
    @Json(name = "VERSION_NAME")
    val versionName: String
)