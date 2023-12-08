package com.example.kompgraph2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.kompgraph2.PolygonView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button)
            .setOnClickListener(){
                val intent = Intent(this, First::class.java)
                startActivity(intent)
            }
        findViewById<Button>(R.id.button2)
            .setOnClickListener(){
                val intent = Intent(this, First2::class.java)
                startActivity(intent)
            }
        val button = findViewById<Button>(R.id.button3)
        button.setOnClickListener {
            val intent = Intent(this, PolygonActivity::class.java)
            startActivity(intent)
            }
    }
}