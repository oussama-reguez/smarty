package com.thinkit.smarty.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.thinkit.smarty.R
import com.thinkit.smarty.di.ResourcesProvider
import com.thinkit.smarty.di.SharedPreferencesProvider
import com.thinkit.smarty.entities.User
import com.thinkit.smarty.enums.Navigation
import com.thinkit.smarty.enums.ViewModelState
import com.thinkit.smarty.repositories.RoomRepository
import com.thinkit.smarty.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val resourcesProvider: ResourcesProvider, private val sharedPreferencesProvider: SharedPreferencesProvider,private val userRepository: UserRepository) : ViewModel(){
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
            viewModelScope.launch {
                saveLoginData()
            }

            loginState.postValue(ViewModelState.SUCCESS)
        }

    }

    private suspend fun saveLoginData(){
        sharedPreferencesProvider.insertBoolean(SharedPreferencesProvider.USER_LOGGED_IN,true)
        userRepository.insertUser(User(id = 1, name = name))
    }













}