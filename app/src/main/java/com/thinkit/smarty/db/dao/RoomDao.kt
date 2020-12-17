package com.thinkit.smarty.db.dao

import androidx.room.*
import com.thinkit.smarty.entities.Device
import com.thinkit.smarty.entities.Room
import com.thinkit.smarty.entities.RoomWithDevices

@Dao
interface RoomDao {
    @Transaction
    @Query("SELECT * FROM room")
    fun getAll(): List<RoomWithDevices>


   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun insertRooms(rooms:List<Room>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDevices(devices:List<Device>)
}