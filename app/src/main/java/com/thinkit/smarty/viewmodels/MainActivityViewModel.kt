package com.thinkit.smarty.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.thinkit.smarty.R
import com.thinkit.smarty.di.ResourcesProvider
import com.thinkit.smarty.di.SharedPreferencesProvider
import com.thinkit.smarty.enums.Navigation
import com.thinkit.smarty.enums.ViewModelState
import com.thinkit.smarty.repositories.RoomRepository
import kotlinx.coroutines.Dispatchers

class MainActivityViewModel(private val sharedPreferencesProvider: SharedPreferencesProvider) : ViewModel(){

    /**
     * live data used to notify observers about the navigation that should be routed
     * if name is present in shared preferences : route to room display list
     * if name is not present in shared preferences route to main
     */
    val navigation = liveData<Navigation>(Dispatchers.IO) {

        val isUserLoggedIn = sharedPreferencesProvider.getBool(SharedPreferencesProvider.USER_LOGGED_IN, false)
        if(isUserLoggedIn)
            emit(Navigation.HOME)
        else
            emit(Navigation.MAIN)

    }











}