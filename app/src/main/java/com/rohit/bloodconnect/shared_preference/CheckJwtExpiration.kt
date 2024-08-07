package com.rohit.bloodconnect.shared_preference


import android.content.Context
object SharedPreferencesHelper {
    private const val PREFS_NAME = "bloodconnect_prefs"
    private const val KEY_JWT_TOKEN = "jwt_token"
    private const val KEY_ONBOARDING_COMPLETE = "onboarding_complete"

    fun saveJwtToken(context: Context, token: String) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString(KEY_JWT_TOKEN, token)
            apply()
        }
    }

    fun getJwtToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_JWT_TOKEN, null)
    }

    fun clearJwtToken(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            remove(KEY_JWT_TOKEN)
            apply()
        }
    }

    fun setOnboardingComplete(context: Context, complete: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putBoolean(KEY_ONBOARDING_COMPLETE, complete)
            apply()
        }
    }

    fun isOnboardingComplete(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_ONBOARDING_COMPLETE, false)
    }
}
