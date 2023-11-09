package com.example.appt.ui

import com.example.appt.ui.model.TaskModel

sealed interface TasksUiState {

    object Loading : TasksUiState
    data class Error(val throwable: Throwable) : TasksUiState
    data class Success(val tasks: List<TaskModel>) : TasksUiState
}