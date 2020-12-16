package com.thinkit.smarty.di

import androidx.lifecycle.ViewModelProvider
import com.thinkit.smarty.utils.bindViewModel
import com.thinkit.smarty.viewmodels.HomeViewModel

import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * object of Kodein Module that defines a singleton of each view model of present in the application
 */
val viewModelModule = Kodein.Module(name = "viewModelModule") {

    /**
     * Starts the binding of a ViewModelProvider.Factory
     */
    bind<ViewModelProvider.Factory>() with singleton {
        ViewModelFactory(kodein.direct)

    }

    /**
     * Starts the binding of a MainActivityViewModel
     */
    bindViewModel<HomeViewModel>() with provider {
        HomeViewModel(instance())
    }

}