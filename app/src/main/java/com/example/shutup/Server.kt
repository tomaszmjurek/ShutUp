package com.example.shutup

import android.util.Log
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket
import java.nio.charset.Charset
import kotlin.concurrent.thread

class Server : Thread() {
    private var server = ServerSocket(7777)
    private var connectedClients = arrayOf<Client>()

    fun getConnectedClients(): Array<Client> {
        return this.connectedClients
    }

    /**
     *  Scanning clients is running always when app is open. Open port: 7777
     *  Running client in new thread
     */
    fun scanClients() {
        while(true) {
            val client = server.accept()
            println("Client connected: ${client.inetAddress.hostAddress}")
            thread{ addClient(client) }
        }
    }

    /**
     *  Adding new client to list of available clients
     */
    private fun addClient(client : Socket) {
        try {
            var c = Client(client)
            connectedClients += c
        } catch (e : java.lang.Exception) {
            println("Error adding new client")
        }
    }

    /**
     *  Using clients socket output stream function sends him shutdown commands
     *  resulting in closing the system immediately
     */
    fun sendCommand(clientsIp : InetAddress) {
        val command = "shutdown -h now\n"
        try {
            connectedClients.forEach {
                if (clientsIp == it.getIp()) {
                    it.socket.getOutputStream().write((command).toByteArray(Charset.defaultCharset()))
                    Log.d("Server", "Command sent")
                    it.socket.close()
                    removeClient(clientsIp)
                }
            }
        } catch (e : Exception) {
            println("Error sending command")
        }
    }

    /**
     *  Finds client on list, removes it and closes it's socket
     */
    private fun removeClient (clientIp : InetAddress) {
        connectedClients.forEach {
            if (clientIp == it.getIp()) {
                it.closeSocket()
                connectedClients.drop(connectedClients.indexOf(it))
            }
        }
    }

}