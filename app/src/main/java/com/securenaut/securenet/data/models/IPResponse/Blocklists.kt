package com.securenaut.securenet.data.models.IPResponse


import com.google.gson.annotations.SerializedName

data class Blocklists(
    @SerializedName("name")
    val name: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("type")
    val type: String
)