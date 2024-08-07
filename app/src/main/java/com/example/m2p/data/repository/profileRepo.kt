package com.example.m2p.data.repository

import android.content.Context
import com.example.m2p.data.model.Transactions
import com.google.gson.Gson
import java.io.InputStream
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class profileRepo(private val context: Context) {
    fun getUsersFromAssets(): List<Transactions>? {
        return try {
            val inputStream: InputStream = context.assets.open("trans.json")
            val json = inputStream.bufferedReader().use { it.readText() }
            val gson = Gson()
            val userListType: Type = object : TypeToken<List<Transactions>>() {}.type
            gson.fromJson(json, userListType)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}