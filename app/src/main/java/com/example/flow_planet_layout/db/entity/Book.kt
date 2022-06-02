package com.example.flow_planet_layout.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
) {
    override fun toString(): String {
        return name
    }
}

