package com.example.flow_planet_layout

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flow_planet_layout.db.entity.Book

class BookViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    fun bind(book: Book) {
        view.findViewById<TextView>(R.id.tv_book_label).text = book.name
        view.setBackgroundResource(R.drawable.ic_launcher_background)
    }
}