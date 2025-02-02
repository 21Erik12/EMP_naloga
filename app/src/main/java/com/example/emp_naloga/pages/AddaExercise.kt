package com.example.emp_naloga.pages

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

@Composable
fun AddaExercise(
    modifier: Modifier = Modifier,
    workoutId: Int?,
    onBack: () -> Unit,
) {
    val viewModel = remember { ExerciseViewModel() }
    var exerciseIme by remember { mutableStateOf("") }
    var exerciseTeza by remember { mutableStateOf("") }
    var exerciseReps by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp)
    ) {

        Text(
            text = "ADD A EXERCISE",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().padding(30.dp),
            textAlign = TextAlign.Center

        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(bottom = 9.dp),
            value = exerciseIme,
            onValueChange = { exerciseIme = it },
            label = { Text("Name of exercise") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(bottom = 9.dp),
            value = exerciseTeza,
            onValueChange = { exerciseTeza = it },
            label = { Text("Weight of exercise(kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
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
                onClick = {
                    val weight = exerciseTeza.toDoubleOrNull()
                    val reps = exerciseReps.toIntOrNull()

                    if (exerciseIme.isNotBlank() && weight != null && reps != null && workoutId != null) {
                        viewModel.addExercise(exerciseIme, weight, reps, workoutId)
                        exerciseIme = ""
                        exerciseTeza = ""
                        exerciseReps = ""
                    }
                }
            ) {
                Text(text ="Add")
            }
        }
    }
}