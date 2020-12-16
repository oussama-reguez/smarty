package com.thinkit.smarty.repositories

import com.thinkit.smarty.db.dao.UserDao
import com.thinkit.smarty.entities.User

class UserRepository(private val userDao: UserDao){

   suspend fun insertUser(user: User){
       userDao.insertUser(user = user)
   }

    suspend fun  getActiveUser()= userDao.getUser()

}