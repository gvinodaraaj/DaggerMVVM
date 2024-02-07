package com.example.daggermvvm.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggermvvm.data.taskModel

class TaskViewModel: ViewModel() {
    interface Callback {
        fun onFunctionTriggered(inPut: Double, inPut2: Double)
    }
    var utilLiveData =MutableLiveData(taskModel(0.0,0.0,0.0))
    private var callback: Callback? = null

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    fun functionToBeCalled() {
        // Check if callback is not null before calling it
        utilLiveData.value?.let { callback?.onFunctionTriggered(it.inPut, utilLiveData.value!!.inPut2) }
    }

    // This method will be called from the Activity to trigger the function
}
