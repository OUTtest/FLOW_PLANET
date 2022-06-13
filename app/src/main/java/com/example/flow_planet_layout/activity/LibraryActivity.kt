package com.example.flow_planet_layout.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.flow_planet_layout.R
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flow_planet_layout.db.DBApplication
import com.example.flow_planet_layout.db.entity.Book
import com.example.flow_planet_layout.ui.BookShelfAdapter
import kotlinx.coroutines.launch

class LibraryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        val bookDao = (application as DBApplication).bookDao

        val Btn_Library_category = findViewById<Button>(R.id.Btn_library_category)


        findViewById<ImageButton>(R.id.btn_library_back).setOnClickListener{
            this.finish()
        }

        Btn_Library_category.setOnClickListener {
            AlertDialog.Builder(this@LibraryActivity).apply{
                val editText = EditText(this@LibraryActivity).apply {
                    isSingleLine = true
                }
                setTitle("카테고리 입력")
                setView(editText)
                setPositiveButton("확인") { dialog, which ->
                    lifecycleScope.launch {
                        bookDao.put(Book(name = editText.text.toString()))
                    }
                }
                setNegativeButton("취소") { _, _ ->}
            }.show()
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