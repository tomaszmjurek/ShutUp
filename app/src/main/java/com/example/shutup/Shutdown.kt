package com.example.shutup

import java.net.Socket

class Shutdown (private val host: String, private val port: Int) : Thread() {
    private var connection: Socket? = null

    override fun run() {
        // Proba nawiazania polaczenia z serwerem
        try {
            connection = Socket(host, port) //host = ip
            println("nawiazano polacznie")
            //odczytanie ip (string>), dodanie do listy

        } catch (e : Exception) {
            println("Blad laczenia")
        } //close()
    }

}