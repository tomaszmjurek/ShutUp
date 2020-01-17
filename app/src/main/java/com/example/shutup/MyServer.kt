package com.example.shutup

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.InetAddress
import java.net.Socket

class MyServer (/*private val host: String, private val port: Int*/) : Thread() {
    private var ssocket: Socket? = null
    private var dataInputStream: DataInputStream? = null
    private var dataOutputStream: DataOutputStream? = null

    private var received : String = ""


    fun read() : String {
        // Proba nawiazania polaczenia z serwerem
//        var rcvmsg : ByteArray? = null
        try {
            ssocket = Socket(InetAddress.getLocalHost(), 7777) //host = localhost?
            println("nawiazano polacznie")

            //odczytanie ip (string>), dodanie do listy
            dataInputStream = ssocket!!.getInputStream() as DataInputStream?

//            rcvmsg = ByteArray(5)
            while (!ssocket!!.isClosed) {
                if (dataInputStream!!.available() > 0) {
                    val tmp = ByteArray(1024)
                    dataInputStream!!.read(tmp)
                    received = String(tmp)
                }
            }
//            dataInputStream!!.read(rcvmsg, 0, 5)
//            print("Odczytano:" + re)

            ssocket!!.close()
        } catch (e : Exception) {
            println("Blad laczenia")
        } //close()
        return received
    }

}