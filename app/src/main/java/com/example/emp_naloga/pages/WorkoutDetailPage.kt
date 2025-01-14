package com.example.emp_naloga.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.emp_naloga.R
import com.example.emp_naloga.db.Exercise
import com.example.emp_naloga.db.ExerciseViewModel
import com.example.emp_naloga.db.Workout
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WorkoutDetailPage(
    workout: Workout,
    onBack: () -> Unit,
    onAddExercise: () -> Unit,
    modifier: Modifier = Modifier
) {
    val exerciseViewModel = remember { ExerciseViewModel() }
    val vadbe by exerciseViewModel.getExercisesForWorkout(workout.id).observeAsState(listOf())

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = onBack,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Back")
        }

        Card(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(
                    text = workout.title,
                    style = MaterialTheme.typography.headlineMedium,

                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Created: ${SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(workout.createdAt)}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Weight: ${workout.weight}kg",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        Button(
            onClick = onAddExercise,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Add Exercise")
        }

        Text(
            text = "Exercises",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        if (vadbe.isEmpty()) {
            Text(
                text = "No exercises added yet",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(vadbe) { vadba ->
                    ExerciseI(
                        vadba = vadba,
                        onDelete = {
                            exerciseViewModel.deleteExercise(vadba.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ExerciseI(
    vadba: Exercise,
    onDelete: () ->Unit
) {

    Card(

        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(17.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = vadba.name,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "${vadba.weight}kg × ${vadba.repetitions} reps",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            IconButton(onClick = onDelete) {

                Icon(
                    painter = painterResource(id = R.drawable.baseline_delete_24),
                    contentDescription = "Delete",
                )
            }
        }
    }
}

