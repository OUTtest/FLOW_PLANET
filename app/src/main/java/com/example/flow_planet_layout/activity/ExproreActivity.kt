package com.example.flow_planet_layout.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.flow_planet_layout.R

class ExproreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exprore)

        val Btn_Exprore_Back = findViewById<Button>(R.id.Btn_Exprore_Back)
        val Btn_Exprore_End = findViewById<Button>(R.id.Btn_Exprore_End)

        Btn_Exprore_Back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        Btn_Exprore_End.setOnClickListener {
            val intent = Intent(this, Exprore_EndActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}