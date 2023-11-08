package com.example.appt.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appt.ui.model.TaskModel
import javax.inject.Inject


class TaskViewModel @Inject constructor() : ViewModel() {

    //primer livedata
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog


    private val _tasks = mutableStateListOf<TaskModel>() //usamos mutable statelist en vez de live data para tratar mas facil la lsta, se pyuede igual flow
    val task:List<TaskModel> = _tasks
     //usamos mutable statelist en vez de live data para tratar mas facil la lsta, se pyuede igual flow


    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreate(task: String) {
        _showDialog.value = false
        Log.i("ivette", task)
        _tasks.add(TaskModel(task = task))
    }

    fun onShowDailogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
//primero buscams la posicion del index
        val index = _tasks.indexOf(taskModel)
        _tasks[index] = _tasks[index].let {
            it.copy(selected = !it.selected) //para generar el marcado de checkbox
        }
    }

    fun onItemRemove(taskModel: TaskModel) { //este metodo de eliminar funciona aqui porque anteriormente estamos usando copy
val task = _tasks.find {it.id == taskModel.id } //buscame tarea dentro de la lista que sea igual a taskmodel id
        _tasks.remove(task)
    }
}