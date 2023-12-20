package com.securenaut.securenet.data.models.IPResponse


import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("ip")
    val ip: String,
    @SerializedName("package")
    val packageX: String,
    @SerializedName("port")
    val port: Int,
    @SerializedName("protocol")
    val protocol: Int
)