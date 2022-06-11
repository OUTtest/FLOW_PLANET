package com.example.flow_planet_layout.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.flow_planet_layout.R

class MenuActivity_ : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val Btn_Statistics = findViewById<Button>(R.id.Btn_Statistics)
        val Btn_Library = findViewById<Button>(R.id.Btn_Library)
        val Btn_MENU_Reward = findViewById<Button>(R.id.Btn_MENU_Reward)
        val Btn_Config = findViewById<Button>(R.id.Btn_MENU_Config)
        val Btn_Back1 = findViewById<Button>(R.id.Btn_MENU_Back1)
        val Btn_Back2 = findViewById<Button>(R.id.Btn_MENU_Back2)

        Btn_Statistics.setOnClickListener{
            val intent = Intent(this, Statistics_Day_Activity::class.java)
            startActivity(intent)
            this.finish()
        }

        Btn_Library.setOnClickListener{
            val intent = Intent(this, LibraryActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        Btn_MENU_Reward.setOnClickListener{
            val intent = Intent(this, Reward_Activity::class.java)
            startActivity(intent)
            this.finish()
        }
        /*
        Btn_Config.setOnClickListener{
            val intent = Intent(this, MenuActivity_::class.java)
            startActivity(intent)
            this.finish()
        }
        */
        Btn_Back1.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        Btn_Back2.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }
}