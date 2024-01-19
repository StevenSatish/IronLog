package com.example.ironlog

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exerciseList")
    fun getAllExercises(): Flow<List<Exercise>>

    @Upsert()
    suspend fun insertExercise(exercise: Exercise)
}