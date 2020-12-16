package com.thinkit.smarty.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.thinkit.smarty.db.dao.RoomDao
import com.thinkit.smarty.entities.Device
import com.thinkit.smarty.entities.Room
import com.thinkit.smarty.entities.User


@Database(
        entities = [Room::class, Device::class, User::class],
        version = 1,
        exportSchema = false)
abstract class SmartyRoomDatabase: RoomDatabase() {

    abstract fun roomDao(): RoomDao

    var rdc: Callback = object : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            // do something after database has been created
            roomDao()
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            // do something every time database is open
        }
    }

    override fun clearAllTables() {

    }

    companion object{
       const val DB_FILE_NAME= "smarty-db"
    }

}