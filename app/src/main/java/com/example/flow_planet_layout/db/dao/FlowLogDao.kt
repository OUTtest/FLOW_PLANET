package com.example.flow_planet_layout.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.flow_planet_layout.db.entity.FlowLog
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface FlowLogDao {
    @Query("SELECT * from flowlogs WHERE :from <= start AND start < :to")
    fun getFromTo(from: LocalDateTime, to: LocalDateTime): Flow<List<FlowLog>>

    @Insert
    suspend fun put(log: FlowLog)
}