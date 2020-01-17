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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Disable strict mode on android
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        setContentView(R.layout.activity_main)
        val adapter = ArrayAdapter(this, R.layout.listview_item, testList)
//        val testList = arrayListOf<String>()
//        testList.addAll(listOf("Linux1", "Linux2", "Linux3"))
//        val devicesListView: ListView = findViewById(R.id.devicesListView)
        devicesListView.adapter = adapter

        val btn = findViewById<Button>(R.id.refreshBtn)
        btn.setOnClickListener {
//            var textR = byteArrayOf()
            var text : String = startConnection()

            //Change text
            setContentView(R.layout.activity_main)
//            topView.text = textR.toString()
            topView.text = text
        }
    }


    private fun startConnection() : String {
//        val testCl1 = TestClient()

        var testSer = MyServer()
//        testCl1.connect()
//        var textR = byteArrayOf()
        return testSer.read()
    }
}
