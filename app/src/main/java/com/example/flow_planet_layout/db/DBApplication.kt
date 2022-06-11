package com.example.flow_planet_layout.db

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class DBApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { NoteRoomDB.getDB(this, applicationScope) }
    val pageDao by lazy { database.pageDao() }
    val bookDao by lazy { database.bookDao() }
}