package com.example.kompgraph2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kompgraph2.PolygonView

class PolygonActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_polygon)

        val polygonView = findViewById<PolygonView>(R.id.polygonView)
        // Здесь вы можете настроить PolygonView или передать данные, если это необходимо
    }
}
