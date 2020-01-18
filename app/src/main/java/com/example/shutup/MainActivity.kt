package com.example.shutup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

//    private var button : Button? = null
private var testList = arrayOf("Linux1", "Linux2", "Linux3")
private var devicesList = arrayOf<Client>()
private val server : Server = Server()
//private var server = MyServer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Disable strict mode on android
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // Initialize server
//        val server = Server()

        setContentView(R.layout.activity_main)
        val empty = listOf<String>("List is empty")
        val adapter = ArrayAdapter(this, R.layout.listview_item, empty)
        devicesListView.adapter = adapter

        refreshButton.setOnClickListener(this)
//        {
////            topView.text = "Scanning..."
//            var i = 0
////            i = server.scanClients()
//            //test
//            i = server.testListView()
//            //Change text
////            setContentView(R.layout.activity_main)
//            if (i == 1) {
//                topView.text = "Scan completed"
//                //updateList
//            }
//        }

        shutdownButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
//        setContentView(R.layout.activity_main)
        when (v.id) {
            refreshButton.id -> updateDevicesList()
            shutdownButton.id -> server.testListView()
            else -> topView.text = "Error"
        }
    }

    private fun updateDevicesList() {
        topView.text = "error2"
        devicesList = server.getConnectedClients()
        var empty = listOf("List is empty")
//        setContentView(R.layout.activity_main)
        var adapter = ArrayAdapter(this, R.layout.listview_item, empty)

        if (devicesList.isNotEmpty()) {
//                var ips = arrayOf<String>()
//                devicesList.forEach {
//                ips += it.getIp().toString();
//                adapter = ArrayAdapter(this, R.layout.listview_item, devicesList)
//                devicesListView.adapter = adapter
//                topView.text = "ip not null"
                var empty1 = listOf(devicesList.get(0).getIp())
              adapter = ArrayAdapter(this, R.layout.listview_item, empty1)
//            }
        }
        devicesListView.adapter = adapter
    }



//    companion object {
//        fun updateList() {(activity as MainActivity).updateDevicesList()}
//    }
}
