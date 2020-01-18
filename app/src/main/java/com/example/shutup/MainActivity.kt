package com.example.shutup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.ArrayAdapter
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    private var button : Button? = null
private var testList = arrayOf("Linux1", "Linux2", "Linux3")
private var devicesList = arrayOf<Client>()
//private var server = MyServer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Disable strict mode on android
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // Initialize server
        val server = MyServer()

        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.refreshButton)
        btn.setOnClickListener {
//            topView.text = "Scanning..."
            var i = 0
            i = server.scanClients()
            //Change text
            setContentView(R.layout.activity_main)

            if (i == 1) topView.text = "found 1"
        }
    }

    fun updateDevicesList(server: MyServer) {
        devicesList = server.getConnectedClients()

        if (devicesList.isNotEmpty()) {
            setContentView(R.layout.activity_main)
            val adapter = ArrayAdapter(this, R.layout.listview_item, devicesList)
            devicesListView.adapter = adapter
        }
    }

//    companion object {
//        fun updateList() {(activity as MainActivity).updateDevicesList()}
//    }
}
