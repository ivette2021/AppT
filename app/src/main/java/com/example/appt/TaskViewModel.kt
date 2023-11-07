package com.example.appt

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject


class TaskViewModel @Inject constructor(): ViewModel() {

    //primer livedata
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog:LiveData<Boolean> = _showDialog


    fun onDialogClose() {
       _showDialog.value = false
    }

    fun onTaskCreate(task: String) {
        _showDialog.value = false
 Log.i("ivette", task)
    }

    fun onShowDailogClick() {
        _showDialog.value = true
    }
}