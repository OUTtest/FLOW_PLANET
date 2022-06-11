package com.example.flow_planet_layout.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import androidx.viewpager2.widget.ViewPager2
import com.example.flow_planet_layout.PageListAdapter
import com.example.flow_planet_layout.R
import com.example.flow_planet_layout.db.DBApplication

class PageViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_viewer)
        val bookId = intent.getIntExtra("bookId", -1)
        if (bookId == -1) finish()
        val pageDao = (application as DBApplication).pageDao

        val pager = findViewById<ViewPager2>(R.id.vp_book_viewer)
        val adapter = PageListAdapter()
        pager.adapter = adapter

        pageDao.getAllInBook(bookId).asLiveData().observe(this) {
            adapter.submitList(it)
        }
    }
}



