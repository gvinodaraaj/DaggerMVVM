package com.example.m2p.data.repository

import android.content.Context
import android.util.DisplayMetrics

class sharedPreferenceRepo (context: Context){

    val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    // Writing to Shared Preferences

    val userName = sharedPreferences.edit().putString("username", "JohnDoe")
    val userAge = sharedPreferences.edit().putInt("username", 25)
    val isLogIn = sharedPreferences.edit().putBoolean("isLoggedIn", true)


    // Reading from Shared Preferences
    val username = sharedPreferences.getString("username", null)
    val user_age = sharedPreferences.getInt("userAge", 0)
    val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

    // Removing specific data
    val isLogged = sharedPreferences.edit().remove("username").apply()

    // Clearing all data
    val clear = sharedPreferences.edit().clear().apply()
    fun setDeviceWidth(context: Context) {
        val displayMetrics = DisplayMetrics()
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as android.view.WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        sharedPreferences.edit().putLong("BANKCARD", (displayMetrics.widthPixels*0.65).toLong())
    }
}