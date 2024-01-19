package com.example.ironlog


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddExerciseDialog(
    viewModel: MainViewModel,
    state: ExerciseState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
        onDismiss()
        },
        title = { Text(text = "Add New Exercise") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.exerciseName,
                    onValueChange = {
                    viewModel.onExerciseNameChange(it)
                    },
                    placeholder = {
                        Text(text = "Exercise Name")
                    }
                )
                TextField(
                    value = state.muscleGroup,
                    onValueChange = {
                    viewModel.onMuscleGroupChange(it)
                    },
                    placeholder = {
                        Text(text = "Muscle Groups (optional)")
                    }
                )
            }
        },
        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                viewModel.onSaveExercise()
                }) {
                    Text(text = "Save")
                }
            }
        }
    )
}