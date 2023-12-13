package com.example.daggermvvm.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggermvvm.data.UserModel
import com.example.daggermvvm.data.UtilitiModel

class UtilViewModel: ViewModel() {
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
    val utilLiveData =MutableLiveData(UtilitiModel("vinod","12345"))
}
