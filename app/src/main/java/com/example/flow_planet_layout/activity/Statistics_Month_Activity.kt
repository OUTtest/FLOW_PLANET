package com.example.flow_planet_layout.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.flow_planet_layout.R

class Statistics_Month_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics_month)

        val Btn_STAM_Home = findViewById<Button>(R.id.Btn_STAM_Home)
        val Btn_STAM_Back = findViewById<Button>(R.id.Btn_STAM_Back)
        val Btn_STAM_Statistics_Day = findViewById<Button>(R.id.Btn_STAM_Statistics_Day)
        val Btn_STAM_Statistics_Week = findViewById<Button>(R.id.Btn_STAM_Statistics_Week)
        val Btn_STAM_Statistics_Month = findViewById<Button>(R.id.Btn_STAM_Statistics_Month)

        Btn_STAM_Home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        Btn_STAM_Statistics_Day.setOnClickListener {
            val intent = Intent(this, Statistics_Day_Activity::class.java)
            startActivity(intent)
            this.finish()
        }

        Btn_STAM_Statistics_Week.setOnClickListener {
            val intent = Intent(this, Statistics_Week_Activity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}