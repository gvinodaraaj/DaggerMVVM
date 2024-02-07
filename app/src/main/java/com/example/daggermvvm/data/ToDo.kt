package com.example.daggermvvm.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Date
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Entity(tableName = "eventTable")
data class ToDo(val name:String,val heading:String, val description:String,val isCompleted:Boolean, @PrimaryKey(autoGenerate = false) val id: Int? = null)

