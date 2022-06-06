package com.kodev.mynavigation.pref

import android.content.Context

private lateinit var preferenceManager: PreferenceManager

object Utils {

    fun putPreference(context: Context, key: String, value: String) {
        preferenceManager = PreferenceManager(context)
        preferenceManager.putString(key, value)
    }

    fun getPreference(context: Context, key: String): String {
        preferenceManager = PreferenceManager(context)
        return preferenceManager.getString(key)
    }

    fun clearSinglePreference(context: Context, key: String) {
        preferenceManager = PreferenceManager(context)
        return preferenceManager.removeSinglePreference(key)
    }

    fun removePreference(context: Context) {
        preferenceManager = PreferenceManager(context)
        return preferenceManager.clearPreference()
    }
}