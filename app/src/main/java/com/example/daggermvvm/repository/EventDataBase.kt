package com.example.daggermvvm.repository

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.daggermvvm.data.Event
import com.example.daggermvvm.data.EventDao
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Database(entities = arrayOf(Event::class), version = 1, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getDatabase(context: Context): EventDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventDatabase::class.java,
                    "event_database"
                ).build()
                INSTANCE = instance
                instance

            }

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun populateDatabase(db: EventDatabase) {
        val eventDao = db.eventDao()
        for (i in 1..15) {
            val today = LocalDate.now()
            val date1Day = today.plus(i.toLong(), ChronoUnit.DAYS)
            val endDay = date1Day.plus(3, ChronoUnit.DAYS)
            eventDao.insert(Event("vinod", "Item " + i, date1Day.toString(), endDay.toString()))
        }
    }
}

