package com.thinkit.smarty.di

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.thinkit.smarty.BuildConfig


/** helper class that provides methods for accessing and modifying preference data from SharedPreferences
 * @constructor constructs an instance of SharedPreferencesProvider with given context
 * @param context - callers context to retrieve SharedPreferences
 */
class SharedPreferencesProvider(private val context: Context) {

    private val preference: SharedPreferences
        get() = getSharedPref()

    /** method that return an instance of either an encrypted SharedPreferences if android version >= Android 6.0 Marshmallow
     *  or regular SharedPreferences if android version below Android 6.0 or in debug
     */
    private fun getSharedPref(): SharedPreferences {
        var sharedPref = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        if (!BuildConfig.DEBUG) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                sharedPref = getEncryptedPrefs()
            }
        }
        return sharedPref!!
    }

    /** method that return an instance of an encrypted SharedPreferences if android version >= Android 6.0 Marshmallow
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun getEncryptedPrefs(): SharedPreferences {
        val masterKey: MasterKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
        return EncryptedSharedPreferences.create(
                context,
                SHARED_PREFERENCE_NAME,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, // prefKeyEncryptionScheme
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // prefValueEncryptionScheme
        )
    }

    /**
     * method that insert a string to SharedPreference
     * @param key unique key
     * @param value value of string
     */
    fun insertString(key: String, value: String) {
        preference.edit().putString(key, value).apply()
    }

    /**
     * method that insert an int to SharedPreference
     * @param key unique key
     * @param value value of int
     */
    fun insertInt(key: String, value: Int) {
        preference.edit().putInt(key, value).apply()
    }

    /**
     * method that insert a long to SharedPreference
     * @param key The name of the preference to retrieve.
     * @param value value of long
     */
    fun insertLong(key: String, value: Long) {
        preference.edit().putLong(key, value).apply()
    }

    /**
     * method that insert a boolean to SharedPreference
     * @param key The name of the preference to modify.
     * @param value value of boolean
     */
    fun insertBoolean(key: String, value: Boolean) {
        preference.edit().putBoolean(key, value).apply()
    }

    /**
     * method that returns a string to SharedPreference
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     */
    fun getString(key: String, defaultValue: String? = null): String? {
        return preference.getString(key, defaultValue)

    }

    /**
     * method that returns an int to SharedPreference
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     */
    fun getInt(key: String, defaultValue: Int): Int? {
        return preference.getInt(key, defaultValue)
    }

    /**
     * method that returns a boolean to SharedPreference
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     */
    fun getBool(key: String, defaultValue: Boolean): Boolean {
        return preference.getBoolean(key, defaultValue)
    }

    companion object {
         const val USER_LOGGED_IN="USER_LOGGED_IN"
         const val SHARED_PREFERENCE_NAME = "SHARED_PREFERENCE_NAME"

    }

}