package com.securenaut.securenet.data.models.scorecard


import com.google.gson.annotations.SerializedName

data class ScoreCard(
    @SerializedName("app_name")
    val appName: String,
    @SerializedName("efr01")
    val efr01: Boolean,
    @SerializedName("file_name")
    val fileName: String,
    @SerializedName("hash")
    val hash: String,
    @SerializedName("high")
    val high: List<High>,
    @SerializedName("hotspot")
    val hotspot: List<Hotspot>,
    @SerializedName("info")
    val info: List<Info>,
    @SerializedName("secure")
    val secure: List<Any>,
    @SerializedName("security_score")
    val securityScore: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("total_trackers")
    val totalTrackers: Int,
    @SerializedName("trackers")
    val trackers: Int,
    @SerializedName("version")
    val version: String,
    @SerializedName("version_name")
    val versionName: String,
    @SerializedName("warning")
    val warning: List<Warning>
)