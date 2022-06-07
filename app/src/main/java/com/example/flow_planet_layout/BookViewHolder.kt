package com.example.flow_planet_layout

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flow_planet_layout.activity.PageViewerActivity
import com.example.flow_planet_layout.db.entity.Book

class BookViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    fun bind(book: Book) {
        view.findViewById<TextView>(R.id.tv_book_label).text = book.name
        view.setBackgroundResource(R.drawable.ic_launcher_background)
        view.setOnClickListener {
            val intent = Intent(view.context, PageViewerActivity::class.java)
            intent.putExtra("bookId", book.id)
            view.context.startActivity(intent)
        }
    }

    companion object {
        fun create(parent: ViewGroup): BookViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_book, parent, false)
            return BookViewHolder(view)
        }
    }
}