package com.example.shutup

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import kotlin.concurrent.thread

class MyServer (/*private val host: String, private val port: Int*/) : Thread() {
    private val server = ServerSocket(7777)
    private var ssocket: Socket? = null
    private var dataInputStream: DataInputStream? = null
    private var dataOutputStream: DataOutputStream? = null
    private var connectedClients = arrayOf<Client>()
    private var received : String = ""

    fun getConnectedClients(): Array<Client> {
        return this.connectedClients
    }

    fun scanClients() : Int{
//        while(true) {
            val client = server.accept()
            println("Client connected: ${client.inetAddress.hostAddress}")

            // Client in new thread
            thread{ readClient(client) }
            return 1
//        }
    }

    private fun readClient(client : Socket) {
        val reader = Scanner(client.getInputStream())
        var newClient = Client()

        try {
            val str = reader.nextLine() //ip
            if(str.isNotEmpty()) {
                newClient.setIp(str)
                connectedClients += newClient
//                MainActivity.getI
                val activity = null
                (activity as MainActivity).updateDevicesList(this)
            }
        } catch (e : java.lang.Exception) {
            println("Error reading message")
        }
    }

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