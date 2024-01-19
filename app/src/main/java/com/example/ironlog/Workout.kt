package com.example.ironlog

import androidx.room.Entity
import androidx.room.PrimaryKey

val allFolders: List<Folder> = listOf(sample)
data class Folder(
    var folderName: String,
    var workouts: List<Workout>
)
data class Workout(
    var workoutName: String,
    var oldExercises: List<OldExercise>,
)

data class OldExercise(
    var exerciseName: String,
    var muscleGroup: String,
    var sets: List<ExerciseSet> = emptyList()

) {
    fun DoesMatchSearchQuery(query: String): Boolean {
        if (query.isBlank()) {
            return true // If the query is empty, consider it a match
        }
        if (muscleGroup.isBlank()) {
            return exerciseName.contains(query, ignoreCase = true)
        }
        val MatchingCombinations = listOf(
            exerciseName,
            muscleGroup,
            "$exerciseName $muscleGroup",
            "$muscleGroup $exerciseName",
            "${exerciseName.first()}${muscleGroup.first()}",
            "${muscleGroup.first()}${exerciseName.first()}",
            "${exerciseName.first()} ${muscleGroup.first()}",
        )
        return MatchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
@Entity(tableName = "exerciseList")
data class Exercise(
    val exerciseName: String,
    val muscleGroup: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
){

    fun DoesMatchSearchQuery(query: String): Boolean {
        if (query.isBlank()) {
            return true // If the query is empty, consider it a match
        }
        if (muscleGroup.isBlank()) {
            return exerciseName.contains(query, ignoreCase = true)
        }
        val matchingCombinations = listOf(
            exerciseName,
            muscleGroup,
            "$exerciseName $muscleGroup",
            "$muscleGroup $exerciseName",
            "${exerciseName.first()}${muscleGroup.first()}",
            "${muscleGroup.first()}${exerciseName.first()}",
            "${exerciseName.first()} ${muscleGroup.first()}",
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
data class ExerciseSet(
    var weight: Int = 0,
    var reps: Int = 0,
)
