package com.example.flow_planet_layout.activity

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.flow_planet_layout.R
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flow_planet_layout.db.DBApplication
import com.example.flow_planet_layout.ui.BookShelfAdapter

class LibraryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        val bookDao = (application as DBApplication).bookDao

        findViewById<ImageButton>(R.id.btn_library_back).setOnClickListener{
            this.finish()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rv_bookshelf)

        val adapter = BookShelfAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 8)

        bookDao.getAll().asLiveData().observe(this) {
            adapter.submitList(it)
        }
    }
}