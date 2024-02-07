package com.example.daggermvvm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskTable")
data class Task(val name:String, val description:String, val status:Boolean, @PrimaryKey(autoGenerate = false) val id: Int? = null)

