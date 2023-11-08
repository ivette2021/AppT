package com.example.appt.data.di

import android.content.Context
import androidx.room.Room
import com.example.appt.data.TDataBase
import com.example.appt.data.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    fun provideTaskDao(tDataBase: TDataBase):TaskDao{ //injectar el dao
        return tDataBase.taskDao()
    }

    @Provides
    @Singleton
    fun provideTDataBase(@ApplicationContext appContext: Context): TDataBase{ //creada la db
        return Room.databaseBuilder(appContext,TDataBase::class.java, "TaskDataBase").build()
    }
}