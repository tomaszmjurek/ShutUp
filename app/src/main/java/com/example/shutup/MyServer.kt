package com.example.shutup

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket

class MyServer (/*private val host: String, private val port: Int*/) : Thread() {
    private var ssocket: Socket? = null
    private var dataInputStream: DataInputStream? = null
    private var dataOutputStream: DataOutputStream? = null

    fun read(rcvmsg : ByteArray) {
        // Proba nawiazania polaczenia z serwerem
        var rcvmsg : ByteArray? = null
        try {
            ssocket = Socket("localhost", 7777) //host = ip
            println("nawiazano polacznie")
            //odczytanie ip (string>), dodanie do listy
            dataInputStream = ssocket!!.getInputStream() as DataInputStream?
            rcvmsg = ByteArray(5)
            dataInputStream!!.read(rcvmsg, 0, 5)
            print("Odczytano:" + rcvmsg)
            ssocket!!.close()
        } catch (e : Exception) {
            println("Blad laczenia")
        } //close()
//        return rcvmsg
    }

}