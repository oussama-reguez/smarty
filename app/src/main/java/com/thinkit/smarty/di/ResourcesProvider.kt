package com.thinkit.smarty.di


import android.content.Context

/**
 * a class that provides a methods to retrieve resources defined in strings.xml
 * @param context applicationContext
 */
class ResourcesProvider(private val context: Context) {

    /**
     * a method that returns a string from a given resource id
     * @param id resource id
     */
    fun getString(id: Int): String {
        return context.getString(id)
    }


}