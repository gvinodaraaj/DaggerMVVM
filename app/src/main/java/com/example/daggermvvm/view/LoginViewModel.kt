package com.example.daggermvvm.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggermvvm.data.UserModel

class LoginViewModel: ViewModel() {
    interface Callback {
        fun onFunctionTriggered()
    }

    private var callback: Callback? = null

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    fun functionToBeCalled() {
        // Check if callback is not null before calling it
        callback?.onFunctionTriggered()
    }

    // This method will be called from the Activity to trigger the function
    val userLiveData =MutableLiveData(UserModel("vinod","12345","12345","9600104721",true,false))
}
