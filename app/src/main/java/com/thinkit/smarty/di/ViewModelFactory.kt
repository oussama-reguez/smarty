package com.thinkit.smarty.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.DKodein
import org.kodein.di.generic.instanceOrNull

/**
 * ViewModel provider factory which will be responsible for ViewModels retrieval
 * @param injector a direct [Kodein] instance
 */
class ViewModelFactory(private val injector: DKodein) : ViewModelProvider.Factory {

    /**
     * a method that return an instance of of a view model by a given modelClass
     * @param modelClass the name of the view model class
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return injector.instanceOrNull<ViewModel>(tag = modelClass.simpleName) as T?
            ?: modelClass.newInstance()
    }
}