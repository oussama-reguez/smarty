package com.thinkit.smarty

import android.app.Application
import com.thinkit.smarty.db.dao.RoomDao
import com.thinkit.smarty.di.*
import com.thinkit.smarty.entities.Device
import com.thinkit.smarty.entities.Room
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class SmartyApplication : Application(), KodeinAware  {
    /**
     * kodein instance for binding dependencies and declared modules
     */
    override val kodein: Kodein = Kodein.lazy{
        import(androidXModule(this@SmartyApplication))
        bind() from singleton { SharedPreferencesProvider(instance()) }
        bind() from singleton { ResourcesProvider(instance()) }
        import(viewModelModule)
        import(repositoriesModule)
        import(roomModule)
    }

    override fun onCreate() {
        super.onCreate()
        val dao :RoomDao by instance()
        dao.getAll()

        val room = Room(1,"living room","living_room" )
        val room2 = Room(2,"Media room" ,"media_room")
        val room3 = Room(3,"Bathroom" ,"bathroom")
        val room4 = Room(4,"Bedroom" ,"bedroom")

        val rooms =listOf(room,room2,room3,room4)
        val device1 =Device(1,"Apple",1)
        val device2 =Device(2,"sumsung",1)
        val device3 =Device(3,"Nokia",1)
        val device4 =Device(4,"Lamp",1)

        val device5 =Device(5,"TV",2)
        val device6 =Device(6,"Ipad",2)
        val device7 =Device(7,"Machine",2)
        val device8 =Device(8,"playstation",2)
        val device9 =Device(9,"xbox",2)
        val device10 =Device(10,"nintendo",2)

        val device11 =Device(11,"home pod",3)

        val device12 =Device(12,"hair dryer",4)
        val device13 =Device(13,"refrigerator",4)
        val device14 =Device(14,"smart shower",4)

        val devices =listOf(device1,device2, device3,device4,device5,device6,device7,device8,
            device9,device10,device11,device12,device13,device14
        )

        dao.insertDevices(devices)
        dao.insertRooms(rooms)
    }

}