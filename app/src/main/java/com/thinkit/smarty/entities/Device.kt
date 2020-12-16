package com.thinkit.smarty.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Device(@PrimaryKey val idDevice:Int, val deviceName:String, val roomId:Int)