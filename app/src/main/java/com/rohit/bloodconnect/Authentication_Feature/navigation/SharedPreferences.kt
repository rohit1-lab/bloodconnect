package com.rohit.bloodconnect.Authentication_Feature.navigation

import android.content.Context

private const val PREFS_NAME = "BloodConnectPrefs"
private const val FIRST_TIME_KEY = "first_time_user"

fun isFirstTimeUser(context: Context): Boolean {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    return prefs.getBoolean(FIRST_TIME_KEY, true)
}

fun setFirstTimeUser(isFirstTime: Boolean, context: Context) {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    with(prefs.edit()) {
        putBoolean(FIRST_TIME_KEY, isFirstTime)
        apply()
    }
}
