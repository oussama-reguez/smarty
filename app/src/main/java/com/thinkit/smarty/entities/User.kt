package com.thinkit.smarty.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(@PrimaryKey val id:Int, val name:String)