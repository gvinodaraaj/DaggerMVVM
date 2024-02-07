package com.example.daggermvvm.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggermvvm.data.Note
import com.example.daggermvvm.data.ToDo
import com.example.daggermvvm.data.UserModel
import com.example.daggermvvm.data.UtilitiModel
import com.example.daggermvvm.repository.NoteDatabase
import com.example.daggermvvm.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    fun addNew() {
        addItem(
            ToDo("Task 1", "check Vinod", "hi Vinod", true)
        )
    }

    // Declare a mutable list
    private val myMutableList: MutableList<ToDo> = mutableListOf()

    // Expose a read-only list to the outside
    fun getMyList(): List<ToDo> {
        val myMutableList = listOf(
            ToDo("Task 1", "check Vinod", "hi Vinod", true),
            ToDo("Task 2", "check Rudra", "hi Rudra", false),
            ToDo("Task 3", "check Vanitha", "hi Vanitha", false),
            ToDo("Task 4", "check Vinod", "hi Vinod", true),
            ToDo("Task 5", "check Rudra", "hi Rudra", false),
            ToDo("Task 6", "check Vanitha", "hi Vanitha", false),
            ToDo("Task 7", "check Vinod", "hi Vinod", true),
            ToDo("Task 8", "check Rudra", "hi Rudra", false),
            ToDo("Task 9", "check Vanitha", "hi Vanitha", false),
            ToDo("Task 10", "check Vinod", "hi Vinod", true),
            ToDo("Task 11", "check Vinod", "hi Vinod", true),
            ToDo("Task 12", "check Rudra", "hi Rudra", false),
            ToDo("Task 13", "check Vanitha", "hi Vanitha", false)
        )
        return myMutableList
    }

    // Add an item to the list
    fun addItem(item: ToDo) {
        myMutableList.add(item)
    }

    // Remove an item from the list
    fun removeItem(item: ToDo) {
        myMutableList.remove(item)
    }

    // Clear the entire list
    fun clearList() {
        myMutableList.clear()
    }

    // val utilLiveData =MutableLiveData(UtilitiModel("vinod","12345"))
    val utilLiveData = MutableLiveData(
        ToDo("Task 1", "check Vinod", "hi Vinod", true)
    )
}
