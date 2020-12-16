package com.thinkit.smarty.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.thinkit.smarty.entities.RoomWithDevices

@Dao
interface RoomDao {
    @Query("SELECT * FROM room")
    fun getAll(): LiveData<List<RoomWithDevices>>
}