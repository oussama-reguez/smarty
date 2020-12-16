package com.thinkit.smarty.repositories

import androidx.lifecycle.LiveData
import com.thinkit.smarty.db.dao.RoomDao
import com.thinkit.smarty.entities.RoomWithDevices

class RoomRepository(private val roomDao: RoomDao) {

fun getAllRooms():List<RoomWithDevices>{
       return  roomDao.getAll()
   }

}