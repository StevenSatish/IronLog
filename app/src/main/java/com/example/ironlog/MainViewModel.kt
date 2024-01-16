package com.example.ironlog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class MainViewModel : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()
    private val _exercises = MutableStateFlow(exerciseList)
    val exercises = searchText
        .combine(_exercises) { text, exercises ->
            if (text.isBlank()) {
                exercises
            } else {
                val filteredExercises = exercises.filter {
                    it.DoesMatchSearchQuery(text)
                }
                filteredExercises
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _exercises.value
        )


    fun onSearchTextChange(text: String){
        _searchText.value = text
    }
}
data class Exercise(
    val exerciseName: String,
    val muscleGroup: String

) {
    fun DoesMatchSearchQuery(query: String): Boolean {
        val MatchingCombinations = listOf(
            "$exerciseName",
            "$muscleGroup",
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
