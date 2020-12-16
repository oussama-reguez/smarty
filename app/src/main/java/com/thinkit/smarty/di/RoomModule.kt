package com.thinkit.smarty.di



import androidx.room.Room
import com.thinkit.smarty.db.SmartyRoomDatabase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.eagerSingleton
import org.kodein.di.generic.instance

val roomModule = Kodein.Module("roomModule") {
    bind<SmartyRoomDatabase>() with eagerSingleton {
        Room.databaseBuilder(instance(), SmartyRoomDatabase::class.java, SmartyRoomDatabase.DB_FILE_NAME)
            .build() }
}