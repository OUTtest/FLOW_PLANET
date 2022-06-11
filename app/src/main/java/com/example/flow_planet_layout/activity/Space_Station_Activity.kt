package com.example.flow_planet_layout.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.flow_planet_layout.R

class Space_Station_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_station)

        val Btn_Station_Back = findViewById<Button>(R.id.Btn_Station_Back)
        val Btn_Station_Studian = findViewById<Button>(R.id.Btn_Station_Studian)
        val Btn_Station_Undefined = findViewById<Button>(R.id.Btn_Station_Undefined)
        val Btn_Station_Start = findViewById<Button>(R.id.Btn_Station_Start)

        Btn_Station_Back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        Btn_Station_Studian.setOnClickListener {
            val intent = Intent(this, StudianActivity::class.java)
            startActivity(intent)
        }

        Btn_Station_Start.setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}