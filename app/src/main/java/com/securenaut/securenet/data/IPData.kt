package com.securenaut.securenet.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ip_table")
data class IPData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val packageName: String,
    val ip: String?,
    val domain: String?,
    val port: Int?,
    val proto: Int?,
    val timestamp: Long,
    val score: Double?,
    val latitude: Double?,
    val longitude: Double?,
    val asnId: String?,
    val asnName: String?
)