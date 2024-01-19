package com.example.ironlog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(val exerciseRepository: ExerciseRepository) : ViewModel() {
        fun getAllExercises(): Flow<List<Exercise>>  {
            return exerciseRepository.getAllExercises()
        }
    fun insertExercise(exercise: Exercise){
        viewModelScope.launch {
            exerciseRepository.insertExercise(exercise)
        }
    }
    private val _state = MutableStateFlow(ExerciseState())
    val state = _state.asStateFlow()

    fun onStartAddingExercise() {
        _state.value = _state.value.copy(isAddingExercise = true)
    }
    fun onDialogDismissed() {
        _state.value = _state.value.copy(isAddingExercise = false)
    }
    fun onExerciseNameChange(exerciseName: String) {
        _state.value = _state.value.copy(exerciseName = exerciseName)
    }
    fun onMuscleGroupChange(muscleGroup: String) {
        _state.value = _state.value.copy(muscleGroup = muscleGroup)
    }
    fun onSaveExercise() {
        val exerciseName = state.value.exerciseName
        val muscleGroup = state.value.muscleGroup

        if(exerciseName.isBlank()) {
            return
        }

        val exercise = Exercise(
            exerciseName = exerciseName,
            muscleGroup = muscleGroup
        )
        viewModelScope.launch {
            exerciseRepository.insertExercise(exercise)
        }
        _state.value = _state.value.copy(
            exerciseName = "",
            muscleGroup = "",
            isAddingExercise = false
        )
    }


    val _exercises = exerciseRepository.getAllExercises()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()
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



