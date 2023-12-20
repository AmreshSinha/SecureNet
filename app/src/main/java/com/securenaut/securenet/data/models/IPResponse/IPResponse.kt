package com.securenaut.securenet.data.models.IPResponse


import com.google.gson.annotations.SerializedName

data class IPResponse(
    @SerializedName("blocklists")
    val blocklists: List<Blocklists>,
    @SerializedName("is_anonymous")
    val isAnonymous: Boolean,
    @SerializedName("is_bogon")
    val isBogon: Boolean,
    @SerializedName("is_datacenter")
    val isDatacenter: Boolean,
    @SerializedName("is_icloud_relay")
    val isIcloudRelay: Boolean,
    @SerializedName("is_known_abuser")
    val isKnownAbuser: Boolean,
    @SerializedName("is_known_attacker")
    val isKnownAttacker: Boolean,
    @SerializedName("is_proxy")
    val isProxy: Boolean,
    @SerializedName("is_threat")
    val isThreat: Boolean,
    @SerializedName("is_tor")
    val isTor: Boolean,
    @SerializedName("request")
    val request: Request,
    @SerializedName("type")
    val type: String
)