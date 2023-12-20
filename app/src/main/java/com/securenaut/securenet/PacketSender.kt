package com.securenaut.securenet

import org.xbill.DNS.Message
import android.content.Context
import android.net.ConnectivityManager
import android.net.VpnService
import android.util.Log
import androidx.collection.LruCache
import com.securenaut.securenet.data.GlobalStaticClass
import com.securenaut.securenet.protocol.Packet
import java.net.InetSocketAddress
import java.nio.ByteBuffer
class PacketSender {
    private val httpWorker: HttpWorker = HttpWorker()
    private var vpnService: VpnService? = null
    private var globalContext: Context? = null
    private val apiURL: String = "https://securenet.photoai.pro"
    private val cacheSize = 4 * 1024 * 1024
    private val reqCache = LruCache<String, Boolean>(cacheSize)
    constructor(vpnService: VpnService?, globalContext: Context){
        this.vpnService = vpnService
        this.globalContext = globalContext
        httpWorker.setContext(globalContext)
    }

    fun isValidDnsPacket(payload: ByteBuffer): String {
        return try {
            // Convert ByteBuffer to ByteArray
            val bytes = ByteArray(payload.remaining())
            payload.get(bytes)

            // Try to parse the DNS message
            val msg = Message(bytes)
            return msg.question.name.toString()
            // If no exception is thrown, it is a valid DNS packet
        } catch (e: Exception) {
            // If any exception is thrown, it is not a valid DNS packet
            return "-"
        }
    }

    fun sendPacket(packet: Packet?): Boolean {
        if(packet != null){
            if(packet.ip4Header == null){
                return false
            }
            val connectivityManager = this.vpnService?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var sourcePort: Int? = null
            var destinationPort: Int? = null
            var vd = "-"
            var protocol: Int? = null
            if(packet.isUDP){
                sourcePort = packet.udpHeader?.sourcePort
                destinationPort = packet.udpHeader?.destinationPort
                protocol = 17
                vd = isValidDnsPacket(packet.backingBuffer!!.duplicate())
            }
            else if(packet.isTCP){
                sourcePort = packet.tcpHeader?.sourcePort
                destinationPort = packet.tcpHeader?.destinationPort
                protocol = 6
            }else{
                Log.d("Unknown Protocol", "PacketSender")
                return true;
            }
            val sourceSocketAddress = InetSocketAddress(packet.ip4Header?.sourceAddress, sourcePort!!)
            val destinationSocketAddress = InetSocketAddress(packet.ip4Header?.destinationAddress, destinationPort!!)
            val uid = connectivityManager.getConnectionOwnerUid(protocol, sourceSocketAddress, destinationSocketAddress )
            if(uid == -1 || uid == 0){
                return false;
            }
            val packageManager = globalContext?.packageManager
            val packageName = packageManager?.getPackagesForUid(uid)?.firstOrNull()
            Log.w("UID","${packet.ip4Header?.destinationAddress?.hostAddress} -> ${uid} -> ${packageName}")
            if(packageName != this.vpnService?.packageName && packageName != "com.google.android.gsm"){
                var body = "ip=${packet.ip4Header?.destinationAddress?.hostAddress}&port=${destinationPort}&package=$packageName&protocol=${protocol}"
                if(vd != "-"){
                    Log.d("Domain", vd)
                    if (vd.endsWith('.')) {
                        vd = vd.dropLast(1)
                        val x = GlobalStaticClass.blackList.find { it.first == vd }
                        if(x != null){
                            return false
                        }
                    }
                    body = "domain=$vd&package=$packageName"
                }
                if (reqCache.get(body) == null){
                    reqCache.put(body, true)
                    httpWorker.get("$apiURL/dynamic/ipdom?", body)
                }
            }
        }
        return true
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
