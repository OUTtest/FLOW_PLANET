package com.example.flow_planet_layout.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.flow_planet_layout.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Btn_MAIN_Menu = findViewById<Button>(R.id.Btn_MAIN_Menu)
        val Btn_MAIN_Start = findViewById<Button>(R.id.Btn_MAIN_Start)

        Btn_MAIN_Menu.setOnClickListener{
            val intent = Intent(this, MenuActivity_::class.java)
            startActivity(intent)
        }

        Btn_MAIN_Start.setOnClickListener{
            val intent = Intent(this, Space_Station_Activity::class.java)
            startActivity(intent)
        }

    }
}