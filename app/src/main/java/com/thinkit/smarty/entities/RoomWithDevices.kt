package com.thinkit.smarty.entities

import androidx.room.Embedded
import androidx.room.Relation

class RoomWithDevices (@Embedded val room: Room,
                       @Relation(
                           parentColumn = "id",
                           entityColumn = "roomId"
                       )
                       val devices: List<Device>)