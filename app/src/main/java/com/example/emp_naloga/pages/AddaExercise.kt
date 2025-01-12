package com.example.emp_naloga.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.emp_naloga.db.ExerciseViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddaExercise(
    modifier: Modifier = Modifier,
    workoutId: Int?,
    onBack: () -> Unit,
) {
    val viewModel = remember { ExerciseViewModel() }
    var exerciseName by remember { mutableStateOf("") }
    var exerciseWeight by remember { mutableStateOf("") }
    var exerciseReps by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(16.dp)
    ) {

        Text(
            text = "ADD A EXERCISE",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            textAlign = TextAlign.Center
        )


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            value = exerciseName,
            onValueChange = { exerciseName = it },
            label = { Text("Name of exercise") }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            value = exerciseWeight,
            onValueChange = { exerciseWeight = it },
            label = { Text("Weight of exercise (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            value = exerciseReps,
            onValueChange = { exerciseReps = it },
            label = { Text("Repetitions") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = onBack
            ) {
                Text(text = "Go back")
            }

            Button(
                modifier = Modifier.weight(1f),
                enabled = workoutId != null,
                onClick = {
                    val weight = exerciseWeight.toDoubleOrNull()
                    val reps = exerciseReps.toIntOrNull()

                    if (exerciseName.isNotBlank() && weight != null && reps != null && workoutId != null) {
                        viewModel.addExercise(exerciseName, weight, reps, workoutId)
                        exerciseName = ""
                        exerciseWeight = ""
                        exerciseReps = ""
                    }
                }
            ) {
                Text(text ="Add")
            }
        }
    }
}