package com.example.flow_planet_layout.db.dao

import androidx.room.*
import com.example.flow_planet_layout.db.entity.Page
import kotlinx.coroutines.flow.Flow

@Dao
interface PageDao {
    @Query("SELECT * FROM pages WHERE bookId = :bookId")
    fun getAllInBook(bookId: Int): Flow<List<Page>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun put(page: Page)

    @Update
    suspend fun update(page: Page)

    @Delete
    suspend fun delete(page: Page)

    @Query("DELETE FROM pages")
    suspend fun deleteAll()

    @Query("DELETE FROM pages WHERE bookId=:bookId")
    suspend fun deleteAllFromBook(bookId: Int)
}

