package com.example.ironlog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DatabaseScreen(
viewModel: MainViewModel
){
    val searchText by viewModel.searchText.collectAsState()
    val exercises by viewModel.exercises.collectAsState()
     val state by viewModel.state.collectAsState()
    val categorizedExercises: List<Category> = exercises
        .groupBy { it.exerciseName.first() }
        .toSortedMap()
        .map { entry ->
            Category(
                name = entry.key.toString(),
                items = entry.value
            )
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if(state.isAddingExercise) {
            AddExerciseDialog(viewModel, state = state, onDismiss = { viewModel.onDialogDismissed() })
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = searchText,
                onValueChange = {
                    viewModel.onSearchTextChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(8f),
                placeholder = {Text(text = "Search by Name or Muscle Group") }
            )
            IconButton(modifier = Modifier.weight(1f), onClick = {
                viewModel.onStartAddingExercise()
            }) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Add New Exercise")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        CategorizedLazyColumn(categories = categorizedExercises)
    }
}

data class Category(
    val name: String,
    val items: List<Exercise>
)

@Composable
private fun CategoryHeader(
    text: String,
    modifier: Modifier = Modifier
){
    Text(text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp))
}
@Composable
private fun CategoryItem(
    text: String,
    modifier: Modifier = Modifier
){
    Text(text = text,
        fontSize = 14.sp,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp))
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CategorizedLazyColumn(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        categories.forEach { category ->
            stickyHeader {
                CategoryHeader(category.name)
            }
            items(category.items) { exercise ->
                CategoryItem(text = exercise.exerciseName)
            }
        }
    }
}

