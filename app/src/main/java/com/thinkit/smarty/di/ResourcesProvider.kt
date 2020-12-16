package com.thinkit.smarty.di


import android.content.Context
import android.content.res.Resources

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
    /**
     * a method that returns a string from a given resource name
     * @param resourceName resource name
     */
    fun getString(resourceName: String): String {
        return getString(context.resources.getIdentifier(resourceName, "string", context.packageName))
        throw Resources.NotFoundException()
    }

}