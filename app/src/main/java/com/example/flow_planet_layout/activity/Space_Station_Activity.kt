package com.example.flow_planet_layout.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.flow_planet_layout.R
import org.w3c.dom.Text

class Space_Station_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_station)

        val Btn_Station_Back = findViewById<ImageButton>(R.id.Btn_Station_Back)
        val Btn_Station_Studian = findViewById<Button>(R.id.Btn_Station_Studian)
        val Btn_Station_Category = findViewById<Button>(R.id.Btn_Station_Category)
        val Btn_Station_Start = findViewById<Button>(R.id.Btn_Station_Start)
        val Btn_Station_Plus = findViewById<Button>(R.id.Btn_Station_Plus)
        val Btn_Station_min = findViewById<Button>(R.id.Btn_Station_Min)
        val Text_Station_Timer = findViewById<TextView>(R.id.Text_Station_Timer)
        var counter = 0

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
            val intent = Intent(this, ExproreActivity::class.java)
            intent.putExtra("count", counter)
            startActivity(intent)
            this.finish()
        }

        Btn_Station_Category.setOnClickListener {

        }

        Btn_Station_Plus.setOnClickListener {
            if(counter < 180){
                counter += 5
                Text_Station_Timer.text = counter.toString()+":00"
            }
        }

        Btn_Station_min.setOnClickListener {
            if(counter > 0){
                counter -= 5
                Text_Station_Timer.text = counter.toString()+":00"
            }
        }
    }
}