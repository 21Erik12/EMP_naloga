package com.example.emp_naloga.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.emp_naloga.R
import com.example.emp_naloga.db.Exercise
import com.example.emp_naloga.db.ExerciseViewModel
import com.example.emp_naloga.db.WorkoutViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddaWorkoutPage(
    viewModel: WorkoutViewModel,
    currentWorkoutId: Int?,
    onNavigateToExercise: (Int) -> Unit
) {
    var titleText by remember { mutableStateOf("") }
    var weightText by remember { mutableStateOf("") }
    var localCurrentWorkoutId by remember(currentWorkoutId) { mutableStateOf(currentWorkoutId) }
    var savedWorkoutTitle by remember { mutableStateOf("") }
    var savedWorkoutWeight by remember { mutableStateOf("") }
    var isWorkoutCreated by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Text(
            text = "ADD A WORKOUT",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = titleText,
            onValueChange = { titleText = it },
            label = { Text("Name of a Workout") }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = weightText,
            onValueChange = { weightText = it },
            label = { Text("Your Weight (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            onClick = {
                val weight = weightText.toDoubleOrNull() ?: 0.0
                if (titleText.isNotBlank()) {
                    viewModel.viewModelScope.launch {
                        val newWorkoutId = viewModel.addWorkout(titleText, weight).toInt()
                        localCurrentWorkoutId = newWorkoutId
                        savedWorkoutTitle = titleText
                        savedWorkoutWeight = weightText
                        isWorkoutCreated = true
                        onNavigateToExercise(newWorkoutId)
                    }
                }
            }
        ) {
            Text(text = "Create workout")
        }
    }

}


