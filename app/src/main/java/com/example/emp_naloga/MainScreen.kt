package com.example.emp_naloga

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.emp_naloga.dataclass.NavItem
import com.example.emp_naloga.db.Workout
import com.example.emp_naloga.db.WorkoutViewModel
import com.example.emp_naloga.pages.AddaExercise
import com.example.emp_naloga.pages.AddaWorkoutPage
import com.example.emp_naloga.pages.HomePage
import com.example.emp_naloga.pages.ProfilePage
import com.example.emp_naloga.pages.StatisticsPage
import com.example.emp_naloga.pages.WorkoutDetailPage


@SuppressLint("NewApi")
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var showExercise by rememberSaveable { mutableStateOf(false) }
    var currentWorkoutId by rememberSaveable { mutableStateOf<Int?>(null) }
    var selectedWorkout by remember { mutableStateOf<Workout?>(null) }
    val viewModel = remember { WorkoutViewModel() }
    var isLoggedIn by rememberSaveable { mutableStateOf(false) }
    var username by rememberSaveable { mutableStateOf("") }

    val navItemList = listOf(
        NavItem("Home", R.drawable.home),
        NavItem("Add", R.drawable.add),
        NavItem("Exercises", R.drawable.fitness),
        NavItem("Profile", R.drawable.profile),
    )

    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            if (index != 1) {
                                showExercise = false
                                currentWorkoutId = null
                            }
                            selectedWorkout = null
                        },
                        icon = {
                            Icon(painter = painterResource(id = navItem.icon),
                                contentDescription = "Icon",
                                modifier = Modifier.size(20.dp))
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        if (selectedWorkout != null) {
            WorkoutDetailPage(
                workout = selectedWorkout!!,
                onBack = { selectedWorkout = null },
                onAddExercise = {
                    currentWorkoutId = selectedWorkout!!.id
                    showExercise = true
                    selectedWorkout = null
                },
                modifier = Modifier.padding(innerPadding)
            )
        } else if (showExercise) {
            AddaExercise(
                modifier = Modifier.padding(innerPadding),
                workoutId = currentWorkoutId,
                onBack = {
                    showExercise = false
                    if (currentWorkoutId != null) {
                        val workout = viewModel.workoutList.value?.find { it.id == currentWorkoutId }
                        selectedWorkout = workout
                    }
                }
            )
        } else {
            when (selectedIndex) {
                0 -> HomePage(
                    viewModel = viewModel,
                    onWorkoutClick = { workout ->
                        selectedWorkout = workout
                    }
                )
                1 -> AddaWorkoutPage(
                    viewModel = viewModel,
                    currentWorkoutId = currentWorkoutId,
                    onNavigateToExercise = { workoutId ->
                        if (workoutId != -1) {
                            currentWorkoutId = workoutId
                        }
                        showExercise = true
                    }
                )
                2 -> StatisticsPage()
                3 -> ProfilePage(
                    isLoggedIn = isLoggedIn,
                    username = username,
                    onLogin = { newUsername ->
                        isLoggedIn = true
                        username = newUsername
                        selectedIndex = 0
                    },
                    onLogout = {
                        isLoggedIn = false
                        username = ""
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}