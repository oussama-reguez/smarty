package com.thinkit.smarty.di


import com.thinkit.smarty.repositories.RoomRepository
import com.thinkit.smarty.repositories.UserRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
/**
 * object of Kodein Module that defines a singleton of each repository of an entity
 */
val repositoriesModule = Kodein.Module("repositoriesModule") {

    /**
     * Starts the binding of a UserRepository
     */
    bind<RoomRepository>() with singleton {
        RoomRepository(instance())
    }

}