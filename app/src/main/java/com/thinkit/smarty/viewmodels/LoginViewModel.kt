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

class LoginViewModel(private val resourcesProvider: ResourcesProvider, private val sharedPreferencesProvider: SharedPreferencesProvider) : ViewModel(){
    /**
     * user name message
     */
  var name:String =""
    /**
     * error display message
     */
    var errorMessage: String = ""


    val loginState: MutableLiveData<ViewModelState> by lazy {
        MutableLiveData<ViewModelState>(ViewModelState.IDLE)
    }


    fun validateName(){

        if(name.isNullOrEmpty())
        {
            errorMessage=resourcesProvider.getString(R.string.name_empty)
            loginState.postValue(ViewModelState.ERROR)
        }
        else{
            sharedPreferencesProvider.insertString(SharedPreferencesProvider.NAME_KEY,name)
            loginState.postValue(ViewModelState.SUCCESS)
        }

    }

    /**
     * live data used to notify observers about the navigation that should be routed
     * if name is present in shared preferences : route to room display list
     * if name is not present in shared preferences route to main
     */
    val navigation = liveData<Navigation>(Dispatchers.IO) {

        val token = sharedPreferencesProvider.getString(SharedPreferencesProvider.NAME_KEY, null)
        if(token.isNullOrEmpty())
            emit(Navigation.MAIN)
        else
            emit(Navigation.HOME)

    }











}