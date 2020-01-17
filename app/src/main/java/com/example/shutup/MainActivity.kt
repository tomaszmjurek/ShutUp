package com.example.shutup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*
//import res.layout.activity_main.xml

class MainActivity : AppCompatActivity() {
//    private var button : Button? = null
    var testList = arrayOf("Linux1", "Linux2", "Linux3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Disable strict mode on android
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)


//        setContentView(R.layout.activity_main)
        val adapter = ArrayAdapter(this, R.layout.activity_main, testList)
//        val testList = arrayListOf<String>()
//        testList.addAll(listOf("Linux1", "Linux2", "Linux3"))
        val devicesListView: ListView = findViewById(R.id.devicesListView)
        devicesListView.adapter = adapter

        val btn = findViewById<Button>(R.id.refreshBtn)
        btn.setOnClickListener {
//                openActivity2()
            var textR = byteArrayOf()
            textR = startConnection()

            //Change text
            setContentView(R.layout.activity_main)
//            topView.text = textR.toString()
            topView.text = "Hallo"
        }
    }


    private fun startConnection() : ByteArray {
        val testCl1 = TestClient()
        var testSer = MyServer()
        testCl1.connect()
        var textR = byteArrayOf()
        testSer.read(textR)
        return textR

//        val view = findViewById<View>(R.id.topView)
//        view.text = textR.toString()

    }
}
