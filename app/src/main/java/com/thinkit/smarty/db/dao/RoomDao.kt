package com.thinkit.smarty.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import com.thinkit.smarty.entities.Device
import com.thinkit.smarty.entities.Room
import com.thinkit.smarty.entities.RoomWithDevices

@Dao
interface RoomDao {
    @Query("SELECT * FROM room")
    fun getAll(): LiveData<List<RoomWithDevices>>


   @Insert
   fun insertRooms(rooms:List<Room>)

   @Insert
    fun insertDevices(devices:List<Device>)
}