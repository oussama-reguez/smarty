package com.thinkit.smarty.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thinkit.smarty.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertUser(user:User)

    @Query("SELECT * FROM User limit 1")
    suspend fun getUser():User
}