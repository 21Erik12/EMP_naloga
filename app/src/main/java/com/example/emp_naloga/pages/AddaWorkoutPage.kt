package com.example.emp_naloga.pages


import androidx.compose.foundation.layout.Column
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
import androidx.lifecycle.viewModelScope
import com.example.emp_naloga.db.WorkoutViewModel
import kotlinx.coroutines.launch

@Composable
fun AddaWorkoutPage(
    viewModel: WorkoutViewModel,
    onNavigateToExercise: (Int) -> Unit
) {
    var ImeText by remember { mutableStateOf("") }
    var TezaText by remember { mutableStateOf("") }


    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp),

    ) {
        Text(
            text = "ADD A WORKOUT",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().padding(30.dp),
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ImeText,
            onValueChange = { ImeText = it },
            label = {
                Text("Name of a Workout")
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = TezaText,
            onValueChange = { TezaText = it },
            label = {
                Text("Your Weight (kg)")
                    },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Button(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            onClick = {
                val weight = TezaText.toDoubleOrNull() ?: 0.0

                if (ImeText.isNotBlank()) {

                    viewModel.viewModelScope.launch {
                        val newWorkoutId = viewModel.addWorkout(ImeText, weight).toInt()
                        onNavigateToExercise(newWorkoutId)
                    }
                }
            }
        ) {
            Text(text = "Create workout")
        }
    }

}


