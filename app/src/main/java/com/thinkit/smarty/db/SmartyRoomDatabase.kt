package com.thinkit.smarty.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thinkit.smarty.db.dao.RoomDao
import com.thinkit.smarty.db.dao.UserDao
import com.thinkit.smarty.entities.Device
import com.thinkit.smarty.entities.Room
import com.thinkit.smarty.entities.User


@Database(
        entities = [Room::class, Device::class, User::class],
        version = 1,
        exportSchema = false)
abstract class SmartyRoomDatabase: RoomDatabase() {

    abstract fun roomDao(): RoomDao
    abstract fun userDao(): UserDao



    override fun clearAllTables() {

    }

    companion object{
       const val DB_FILE_NAME= "smarty-db"
    }

}