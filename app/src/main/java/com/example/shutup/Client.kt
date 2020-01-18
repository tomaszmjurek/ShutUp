package com.example.shutup

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket

class Client () {
    private var ip : String? = null

    fun setIp(ip : String) {
        this.ip = ip
    }

//    private var csocket: Socket? = null

//    public fun connect() {
//        csocket = Socket("localhost", 7777)
//        val dout = DataOutputStream(csocket!!.getOutputStream())
//        val sndmsg = byteArrayOf(1, 2, 3, 4, 5)
//        dout.write(sndmsg)
//    }
}