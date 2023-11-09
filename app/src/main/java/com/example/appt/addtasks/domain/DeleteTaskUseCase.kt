package com.example.appt.addtasks.domain

import com.example.appt.addtasks.data.TaskRepository
import com.example.appt.ui.model.TaskModel
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel){
        taskRepository.delete(taskModel)
    }
}