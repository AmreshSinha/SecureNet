package com.securenaut.securenet.data.models.IPRequest


import com.google.gson.annotations.SerializedName

data class IPDataRequest(
    @SerializedName("domain")
    val domain: String,
    @SerializedName("ip")
    val ip: String,
    @SerializedName("package")
    val packageX: String,
    @SerializedName("port")
    val port: String,
    @SerializedName("protocol")
    val protocol: String,
    @SerializedName("string")
    val string: String
)