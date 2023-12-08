package com.example.daggermvvm.repository

import androidx.lifecycle.LiveData
import com.example.daggermvvm.data.Event
import com.example.daggermvvm.data.EventDao

class EventRepository(private val eventDao: EventDao) {
    val AllEvents:LiveData<List<Event>> = eventDao.getAllNotes()

    suspend fun insertEvent(event: Event){
        eventDao.insert(event)
    }

    suspend fun deletEvent(event: Event){
        eventDao.delete(event)
    }

    suspend fun updateEvent(event: Event){
        eventDao.update(event)
    }
}