package com.thinkit.smarty.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.thinkit.smarty.R
import com.thinkit.smarty.di.ResourcesProvider
import com.thinkit.smarty.di.SharedPreferencesProvider
import com.thinkit.smarty.repositories.RoomRepository
import com.thinkit.smarty.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(private val roomRepository: RoomRepository,
                    private val userRepository: UserRepository, private val resourcesProvider: ResourcesProvider) : ViewModel(){


    val welcomeMessage = ObservableField<String>()
    var currentFormattedDate:String
    val rooms = liveData(Dispatchers.IO) {
      emit(roomRepository.getAllRooms())
    }

    init {
        viewModelScope.launch {
            val name = userRepository.getActiveUser().name
            welcomeMessage.set( "${resourcesProvider.getString(R.string.welcome)}, $name!")
        }

        currentFormattedDate= SimpleDateFormat(DATE_PATTERN).format(Date())
    }


}

const val DATE_PATTERN="dd MMM yyyy"