package com.example.daggermvvm.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggermvvm.data.Event
import com.example.daggermvvm.repository.EventDatabase
import com.example.daggermvvm.repository.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewViewModel(application: Application) : AndroidViewModel(application){
    val reopsitory: EventRepository
    init {
        val dao= EventDatabase.getDatabase(application).eventDao()
        reopsitory= EventRepository(dao)
    }
    fun deletEvent(event: Event)= viewModelScope.launch(Dispatchers.IO) {
        reopsitory.deletEvent(event)
    }
    fun updateEvent(event: Event)= viewModelScope.launch(Dispatchers.IO) {
        reopsitory.updateEvent(event)
    }
    fun insertEvent(event: Event)= viewModelScope.launch(Dispatchers.IO) {
        reopsitory.insertEvent(event)
    }
    fun addEvent(event: Event)= viewModelScope.launch(Dispatchers.IO) {
        reopsitory.insertEvent(event)
    }
}