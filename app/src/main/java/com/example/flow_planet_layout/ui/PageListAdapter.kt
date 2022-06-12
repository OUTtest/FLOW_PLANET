package com.example.flow_planet_layout.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.flow_planet_layout.db.entity.Page

class PageListAdapter: ListAdapter<Page, PageViewHolder>(diffUtil){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return PageViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    companion object{
        @JvmStatic
        val diffUtil = object: DiffUtil.ItemCallback<Page>() {
            override fun areItemsTheSame(oldItem: Page, newItem: Page): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Page, newItem: Page): Boolean {
                return oldItem == newItem
            }
        }
    }
}