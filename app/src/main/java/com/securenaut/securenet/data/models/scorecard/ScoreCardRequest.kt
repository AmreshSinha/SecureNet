package com.securenaut.securenet.data.models.scorecard


import com.google.gson.annotations.SerializedName

data class ScoreCardRequest(
    @SerializedName("hash")
    val hash: String
)