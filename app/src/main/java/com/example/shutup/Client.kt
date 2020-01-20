package com.example.shutup

import java.net.InetAddress
import java.net.Socket

class Client (sock : Socket) {
    private val ip : InetAddress = sock.inetAddress
    val socket : Socket = sock

    fun getIp() : InetAddress? {
        return this.ip
    }

    fun closeSocket() {
        this.socket.close()
    }

}