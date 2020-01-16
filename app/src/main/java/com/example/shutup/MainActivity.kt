package com.example.shutup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
//import res.layout.activity_main.xml

class MainActivity : AppCompatActivity() {
//    private var button : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //startConnection()

        val btn = findViewById<Button>(R.id.refreshBtn)
        btn.setOnClickListener {
//                openActivity2()
            startConnection(savedInstanceState)
        }
    }


    private fun startConnection(savedInstanceState: Bundle?) {
        val testCl1 = TestClient()
        var testSer = MyServer()
        testCl1.connect()
        val textR = byteArrayOf()
        testSer.read(textR)

//        val view = findViewById<View>(R.id.topView)
//        view.text = textR.toString()
        setContentView(R.layout.activity_main)
        topView.text = textR.toString()
    }
}
