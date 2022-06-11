package com.example.flow_planet_layout.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.flow_planet_layout.R

class StudianActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studian)

        val Btn_Studian_Back = findViewById<Button>(R.id.Btn_Studian_Back)

        Btn_Studian_Back.setOnClickListener {
            val intent = Intent(this, Space_Station_Activity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}