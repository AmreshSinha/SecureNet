package com.securenaut.securenet.data.models.scorecard


import com.google.gson.annotations.SerializedName

data class Warning(
    @SerializedName("description")
    val description: String,
    @SerializedName("section")
    val section: String,
    @SerializedName("title")
    val title: String
)