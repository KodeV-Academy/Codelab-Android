package com.kodev.mynavigation.pref

import android.content.Context

class PreferenceManager(context: Context) {

    private val sharedPreference =
        context.getSharedPreferences(Constant.KEY_PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun putBoolean(key: String, value: Boolean) {
        val editor = sharedPreference.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreference.getBoolean(key, false)
    }

    fun putString(key: String, value: String) {
        val editor = sharedPreference.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String {
        return sharedPreference.getString(key, "").toString()
    }

    fun removeSinglePreference(key: String) {
        val editor = sharedPreference.edit()
        editor.remove(key)
        editor.apply()
    }

    fun clearPreference() {
        val  editor = sharedPreference.edit()
        editor.clear()
        editor.apply()
    }
}

