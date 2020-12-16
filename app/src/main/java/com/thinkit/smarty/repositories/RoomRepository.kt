package com.thinkit.smarty.repositories

import androidx.lifecycle.LiveData
import com.thinkit.smarty.db.dao.RoomDao
import com.thinkit.smarty.entities.RoomWithDevices

class RoomRepository(val roomDao: RoomDao) {

fun getAllRooms():LiveData<List<RoomWithDevices>>{
       return  roomDao.getAll()
   }

}