package com.example.shutup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //startConnection()
    }

    private fun startConnection() {
        val testCl1 = TestClient()
        var testSer = MyServer()
        testSer.run()
        testCl1.connect()
    }
}
