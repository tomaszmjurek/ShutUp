package com.example.shutup

import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemClickListener {

    private var devicesList = arrayOf<Client>()
    private val server : Server = Server()
    private var selectedItem : Int = -1
    val emptyList = listOf("List is empty")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Disable strict mode on android
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // Let's me use content variables
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter(this, R.layout.listview_item, emptyList)
       // adapter.setNotifyOnChange(true) //TODO
        devicesListView.adapter = adapter

        // Constant scanning of clients
        thread { server.scanClients() }

        // Buttons click listening
        refreshButton.setOnClickListener(this)
        shutdownButton.setOnClickListener(this)
//        devicesListView.choiceMode = ListView.CHOICE_MODE_MULTIPLE //TODO
        devicesListView.onItemClickListener = this
    }


    override fun onClick(v: View) {
        when (v.id) {
            refreshButton.id -> updateDevicesList()
            shutdownButton.id -> if (selectedItem != -1) { shutDown() }
        }
    }

    /**
     * Finds selected
     */
    private fun shutDown() {
        devicesList[selectedItem].getIp()?.let { server.sendCommand(it) }
        updateDevicesList()
    }

    /**
     *  Checks the list of available clients and shows them in ListView
     */
    private fun updateDevicesList() {
        selectedItem = -1
        devicesList = emptyArray()
        devicesList = server.getConnectedClients()
        var adapter = ArrayAdapter(this, R.layout.listview_item, emptyList)

        if (devicesList.isNotEmpty()) {
            val ips = mutableListOf<String>()
            devicesList.forEach {
                ips += it.getIp().toString()
            }
            adapter = ArrayAdapter(this, R.layout.listview_item, ips)
        }
        devicesListView.adapter = adapter
    }

    /**
     *  Handling list element selection
     */
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (devicesList.isNotEmpty()) {
            selectedItem = position
        }
    }

}
