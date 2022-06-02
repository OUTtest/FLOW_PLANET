package com.example.flow_planet_layout.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Fts4
@Entity(tableName = "pages")
data class Page (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="rowid") val id: Int = 0,
    val time: LocalDateTime,
    val bookId: Int,
    var title: String,
    var data: String
)