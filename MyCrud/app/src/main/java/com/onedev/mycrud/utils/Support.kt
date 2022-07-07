package com.onedev.mycrud.utils

import android.content.Context
import android.widget.Toast

object Support {

    private lateinit var preferenceManager: PreferenceManager

    fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun putStringPreference(context: Context, key: String, value: String) {
        preferenceManager = PreferenceManager(context)
        preferenceManager.putString(key, value)
    }

    fun getStringPreference(context: Context, key: String): String {
        preferenceManager = PreferenceManager(context)
        return preferenceManager.getString(key)
    }

    fun putBooleanPreference(context: Context, key: String, value: Boolean) {
        preferenceManager = PreferenceManager(context)
        preferenceManager.putBoolean(key, value)
    }

    fun getBooleanPreference(context: Context, key: String): Boolean {
        preferenceManager = PreferenceManager(context)
        return preferenceManager.getBoolean(key)
    }

    fun clearPreference(context: Context) {
        preferenceManager = PreferenceManager(context)
        preferenceManager.clearPreference()
    }
}