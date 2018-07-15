package com.bts.flickr.data.repository

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {

    private val STORAGE_NAME = "com.bts.prefs"

    private val preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
    }


}