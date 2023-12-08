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
data class Event(val name:String,val description:String,val startDate:String,val endDate:String,@PrimaryKey(autoGenerate = false) val id: Int? = null)

