package com.example.appt.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appt.addtasks.domain.AddTaskUseCase
import com.example.appt.addtasks.domain.DeleteTaskUseCase
import com.example.appt.addtasks.domain.GetTasksUseCase
import com.example.appt.addtasks.domain.UpdateTaskUseCase
import com.example.appt.ui.TasksUiState.Success
import com.example.appt.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val addTaskUseCase:AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getTasksUseCase:GetTasksUseCase
):ViewModel() {

    val uiState: StateFlow<TasksUiState> = getTasksUseCase().map ( ::Success ) //consume los dos casos de uso
        .catch { TasksUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),TasksUiState.Loading)
    //primer livedata
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreate(task: String) {
        _showDialog.value = false
        viewModelScope.launch { addTaskUseCase(TaskModel(task=task)) }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
//primero buscams la posicion del index
      /*  val index = _tasks.indexOf(taskModel)
        _tasks[index] = _tasks[index].let {
            it.copy(selected = !it.selected) //para generar el marcado de checkbox
        }*/
        viewModelScope.launch {
           updateTaskUseCase(taskModel.copy(selected = !taskModel.selected))}
    }

    fun onItemRemove(taskModel: TaskModel) { //este metodo de eliminar funciona aqui porque anteriormente estamos usando copy
/*val task = _tasks.find {it.id == taskModel.id } //buscame tarea dentro de la lista que sea igual a taskmodel id
        _tasks.remove(task)*/
viewModelScope.launch { deleteTaskUseCase(taskModel) }
    }
}