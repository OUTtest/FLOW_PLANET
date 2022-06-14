package com.example.flow_planet_layout.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.asLiveData
import com.example.flow_planet_layout.R
import com.example.flow_planet_layout.db.DBApplication

class Space_Station_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_station)

        val bookDao = (application as DBApplication).bookDao
        var books: Map<Int, String>? = null

        bookDao.getAll().asLiveData().observe(this) { list ->
            books = list.associate { it.id to it.name }
        }

        val Btn_Station_Back = findViewById<Button>(R.id.Btn_Station_Back)
        val Btn_Station_Studian = findViewById<Button>(R.id.Btn_Station_Studian)
        val Btn_Station_Category = findViewById<Button>(R.id.Btn_Station_Category)
        val Btn_Station_Start = findViewById<Button>(R.id.Btn_Station_Start)
        val Btn_Station_Plus = findViewById<Button>(R.id.Btn_Station_Plus)
        val Btn_Station_min = findViewById<Button>(R.id.Btn_Station_Min)
        val Text_Station_Timer = findViewById<TextView>(R.id.Text_Station_Timer)
        var counter = 0
        var bookId = -1

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
            if (bookId == -1 ) {
                Toast.makeText(this@Space_Station_Activity,"카테고리를 선택해주세요",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, ExproreActivity::class.java)
            intent.putExtra("count", counter)
            intent.putExtra("bookId", bookId)
            startActivity(intent)
            this.finish()
        }

        Btn_Station_Category.setOnClickListener {

            if (books != null) {
                AlertDialog.Builder(this@Space_Station_Activity).apply {
                    if (bookId == -1)
                        setTitle("카테고리를 선택하세요")
                    else {
                        setTitle("카테고리를 선택하세요\n(${books!![bookId]} 선택됨)")
                    }
                    val arr = books!!.toList()
                    setItems(arr.map { it.second }.toTypedArray()) { dialog, which ->
                        bookId = arr[which].first
                    }
                }.show()
            }

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