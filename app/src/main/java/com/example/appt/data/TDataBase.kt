package com.example.appt.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version =1)
abstract class TDataBase: RoomDatabase() {
//DAO
    abstract fun taskDao():TaskDao
}