package com.example.ironlog

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseRepository@Inject
    constructor(private val exerciseDao: ExerciseDao) {
    suspend fun insertExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise)
    }

    fun getAllExercises(): Flow<List<Exercise>> {
        return exerciseDao.getAllExercises()
    }
}