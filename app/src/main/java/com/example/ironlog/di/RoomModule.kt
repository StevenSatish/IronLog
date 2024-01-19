package com.example.ironlog.di

import android.content.Context
import androidx.room.Room
import com.example.ironlog.ExerciseDao
import com.example.ironlog.ExerciseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    fun provideExerciseDatabase(@ApplicationContext context: Context ) : ExerciseDatabase {
        return Room.databaseBuilder(context, ExerciseDatabase::class.java, "exerciseDB")
            .build()
    }

    @Provides
    fun provideExerciseDao(exerciseDatabase: ExerciseDatabase) : ExerciseDao {
        return exerciseDatabase.exerciseDao
    }

}