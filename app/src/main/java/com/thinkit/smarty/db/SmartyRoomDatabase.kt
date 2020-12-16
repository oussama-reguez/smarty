package com.thinkit.smarty.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thinkit.smarty.entities.DummyEntity


@Database(
    entities = [DummyEntity::class],
    version = 1,
    exportSchema = false)
abstract class SmartyRoomDatabase: RoomDatabase() {



    override fun clearAllTables() {

    }

    companion object{
       const val DB_FILE_NAME= "smarty-db"
    }

}