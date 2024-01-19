package com.example.ironlog

data class ExerciseState(
    val exercises: List<Exercise> = emptyList(),
    val exerciseName: String = "",
    val muscleGroup: String = "",
    val isAddingExercise: Boolean = false,
)