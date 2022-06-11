package com.example.flow_planet_layout.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "pages")
data class Page (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val time: LocalDateTime,
    val bookId: Int,
    var title: String,
    var data: String
)