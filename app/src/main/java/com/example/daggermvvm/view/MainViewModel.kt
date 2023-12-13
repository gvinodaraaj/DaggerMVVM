package com.example.daggermvvm.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggermvvm.data.UserModel

class MainViewModel: ViewModel() {

    private val _currentNavigationEvent = MutableLiveData<NavigationEvents>()
    val currentNavigationEvent: LiveData<NavigationEvents> = _currentNavigationEvent

    fun callAddFunction(){
        setCurrentNavigationEvent(NavigationEvents.Add)
    }
    fun callUtilFunction(){
        setCurrentNavigationEvent(NavigationEvents.Util)
    }
    fun callProfileFunction(){
        setCurrentNavigationEvent(NavigationEvents.Profile)
    }
    fun setCurrentNavigationEvent(event: NavigationEvents) {
        _currentNavigationEvent.value = event
    }
    // This method will be called from the Activity to trigger the function
    val userLiveData =MutableLiveData(UserModel("vinod","12345","12345","9600104721",true,false))
}
