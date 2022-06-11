package com.example.flow_planet_layout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flow_planet_layout.db.entity.Page

class PageViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    fun bind(page: Page) {
        view.findViewById<TextView>(R.id.tv_page_pagename).text = page.title
        view.findViewById<TextView>(R.id.tv_page_body).text = page.data
        view.findViewById<TextView>(R.id.tv_page_date).text = page.time.toString()
    }
    companion object {
        fun create(parent: ViewGroup): PageViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_page, parent, false)
            return PageViewHolder(view)
        }
    }
}