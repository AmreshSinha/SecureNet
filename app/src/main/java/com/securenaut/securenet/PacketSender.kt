package com.securenaut.securenet

import android.content.Context
import android.net.ConnectivityManager
import android.net.VpnService
import android.util.Log
import androidx.collection.LruCache
import com.securenaut.securenet.protocol.Packet
import java.net.InetSocketAddress
import java.nio.ByteBuffer
class PacketSender {
    private val httpWorker: HttpWorker = HttpWorker()
    private var vpnService: VpnService? = null
    private var globalContext: Context? = null
    private val apiURL: String = "https://q4vf1dkal164ppf7sgey3sr40v6muci1.oastify.com"
    private val cacheSize = 4 * 1024 * 1024
    private val reqCache = LruCache<String, Boolean>(cacheSize)
    constructor(vpnService: VpnService?, globalContext: Context){
        this.vpnService = vpnService
        this.globalContext = globalContext
    }


    fun sendPacket(packet: Packet?){
        if(packet != null){
            if(packet.ip4Header == null){
                return
            }
            val connectivityManager = this.vpnService?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var sourcePort: Int? = null
            var destinationPort: Int? = null
            var protocol: Int? = null
            if(packet.isUDP){
                sourcePort = packet.udpHeader?.sourcePort
                destinationPort = packet.udpHeader?.destinationPort
                protocol = 17
            }
            else if(packet.isTCP){
                sourcePort = packet.tcpHeader?.sourcePort
                destinationPort = packet.tcpHeader?.destinationPort
                protocol = 6
            }else{
                Log.d("Unknown Protocol", "PacketSender")
                return;
            }
            val sourceSocketAddress = InetSocketAddress(packet.ip4Header?.sourceAddress, sourcePort!!)
            val destinationSocketAddress = InetSocketAddress(packet.ip4Header?.destinationAddress, destinationPort!!)
            val uid = connectivityManager.getConnectionOwnerUid(protocol, sourceSocketAddress, destinationSocketAddress )
            if(uid == -1 || uid == 0){
                return;
            }
            val packageManager = globalContext?.packageManager
            val packageName = packageManager?.getPackagesForUid(uid)?.firstOrNull()
            Log.w("UID","${packet.ip4Header?.destinationAddress?.hostAddress} -> ${uid} -> ${packageName}")
            Log.d("Packet","${packet.backingBuffer!!.toHex()}" )

            if(packageName != this.vpnService?.packageName && packageName != "com.google.android.gsm"){
                val body = "ip=${packet.ip4Header?.destinationAddress?.hostAddress}&port=${destinationPort}&package=$packageName"
                if (reqCache.get(body) == null){
                    reqCache.put(body, true)
                    httpWorker.post("$apiURL/ip", body)
                }
            }
        }
    }
}
fun ByteBuffer.toHex(): String {
    val sb = StringBuilder()
    var pos = position()
    while (pos < limit()) {
        val byte = get(pos)
        sb.append("%02X".format(byte.toInt() and 0xFF))
        pos++
    }
    return sb.toString()
}
