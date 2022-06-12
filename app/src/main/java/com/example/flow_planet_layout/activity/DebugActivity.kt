package com.example.flow_planet_layout.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.example.flow_planet_layout.R
import com.example.flow_planet_layout.db.DBApplication
import com.example.flow_planet_layout.db.entity.Book
import kotlinx.coroutines.launch

class DebugActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)

        val pageDao = (application as DBApplication).pageDao
        val bookDao = (application as DBApplication).bookDao
        val flowLogDao = (application as DBApplication).flowLogDao

        findViewById<Button>(R.id.btn_reset_db).setOnClickListener {
            lifecycleScope.launch {
                pageDao.deleteAll()
                bookDao.deleteAll()
                flowLogDao.deleteAll()
            }
        }

        findViewById<Button>(R.id.btn_launch_end).setOnClickListener {
            val intent = Intent(this, Exprore_EndActivity::class.java).apply {
                putExtra("bookId", 1)
                putExtra("score", 6)
            }
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_add_book_1).setOnClickListener {
            lifecycleScope.launch {
                bookDao.put(Book(1, "Book 1"))
            }
        }
    }
}