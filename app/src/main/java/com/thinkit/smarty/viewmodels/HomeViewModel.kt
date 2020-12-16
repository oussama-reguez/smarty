package com.thinkit.smarty.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.thinkit.smarty.repositories.RoomRepository
import kotlinx.coroutines.Dispatchers

class HomeViewModel(private val roomRepository: RoomRepository) : ViewModel(){


    val rooms = liveData(Dispatchers.IO) {
      emit(roomRepository.getAllRooms())
    }


}