package com.example.flow_planet_layout.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.flow_planet_layout.R

class Exprore_EndActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exprore_end)

        val Btn_ExproreEnd_save = findViewById<Button>(R.id.Btn_exproreEnd_save)
        val Btn_ExproreEnd_cancle = findViewById<Button>(R.id.Btn_exproreEnd_cancle)

        Btn_ExproreEnd_save.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        Btn_ExproreEnd_cancle.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}