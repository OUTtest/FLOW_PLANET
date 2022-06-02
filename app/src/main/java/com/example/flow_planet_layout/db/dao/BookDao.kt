package com.example.flow_planet_layout.db.dao

import androidx.room.*
import com.example.flow_planet_layout.db.entity.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    fun getAll(): Flow<List<Book>>

    @Query("SELECT * FROM books WHERE id=:id")
    fun get(id: Int): Flow<Book>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun put(book: Book)

    @Update
    suspend fun update(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Query("DELETE FROM books")
    suspend fun deleteAll()
}