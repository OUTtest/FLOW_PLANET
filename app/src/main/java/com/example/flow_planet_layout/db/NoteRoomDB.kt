package com.example.flow_planet_layout.db

import android.content.Context
import androidx.room.*
import com.example.flow_planet_layout.db.dao.BookDao
import com.example.flow_planet_layout.db.dao.PageDao
import com.example.flow_planet_layout.db.entity.Book
import com.example.flow_planet_layout.db.entity.Page
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Page::class, Book::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class)
abstract class NoteRoomDB: RoomDatabase() {
    abstract fun pageDao(): PageDao
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var instance: NoteRoomDB? = null
        fun getDB(context: Context, scope: CoroutineScope): NoteRoomDB {
            return instance ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context,
                    NoteRoomDB::class.java, "note-db"
                ).build()
                instance = db
                db
            }
        }
    }
}

