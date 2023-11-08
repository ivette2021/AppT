package com.example.appt.ui.model

data class TaskModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val task: String,
    var selected: Boolean = false
) //el system.current... nos devuelve el momento exacto (milisg) en que fue el evento y nunca se repite
