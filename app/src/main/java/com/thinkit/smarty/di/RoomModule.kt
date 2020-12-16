package com.thinkit.smarty.di



import androidx.room.Room
import com.thinkit.smarty.db.SmartyRoomDatabase
import com.thinkit.smarty.db.dao.RoomDao
import com.thinkit.smarty.db.dao.UserDao
import com.thinkit.smarty.entities.User
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.eagerSingleton
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val roomModule = Kodein.Module("roomModule") {
    bind<SmartyRoomDatabase>() with eagerSingleton {
        Room.databaseBuilder(instance(), SmartyRoomDatabase::class.java, SmartyRoomDatabase.DB_FILE_NAME).allowMainThreadQueries()
        //    .createFromAsset("database/smarty.db")
            .build()

    }


    bind<RoomDao>() with  singleton { (instance() as SmartyRoomDatabase).roomDao()  }
    bind<UserDao>() with  singleton { (instance() as SmartyRoomDatabase).userDao()  }


}