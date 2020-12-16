package com.thinkit.smarty

import android.app.Application
import com.thinkit.smarty.di.*
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
}