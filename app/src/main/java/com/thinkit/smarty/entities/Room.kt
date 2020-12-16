package com.thinkit.smarty.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Room(@PrimaryKey val id :Int, val name:String)