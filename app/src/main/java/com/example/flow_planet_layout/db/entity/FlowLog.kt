package com.example.flow_planet_layout.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "flowlogs")
data class FlowLog(
    @PrimaryKey
    val start: LocalDateTime,
    val duration: Int,
)