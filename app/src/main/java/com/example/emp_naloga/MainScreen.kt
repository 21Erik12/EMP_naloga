package com.example.emp_naloga

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
    var poakziExercise by rememberSaveable { mutableStateOf(false) }
    var trenutniWorkoutId by rememberSaveable { mutableStateOf<Int?>(null) }
    var izbranWorkout by remember { mutableStateOf<Workout?>(null) }
    val viewModel = remember { WorkoutViewModel() }
    var isLoggedIn by rememberSaveable { mutableStateOf(false) }
    var username by rememberSaveable { mutableStateOf("") }

    val navItemList = listOf(
        NavItem("Home", R.drawable.home),
        NavItem("Add", R.drawable.add),
        NavItem("Exercises", R.drawable.fitness),
        NavItem("Profile", R.drawable.profile),
    )

    var selectedIndex by rememberSaveable { mutableStateOf(3) }

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
                                poakziExercise = false
                                trenutniWorkoutId = null
                            }
                            izbranWorkout = null
                        },
                        icon = {
                            Icon(painter = painterResource(id = navItem.icon), contentDescription = "Icon", modifier = Modifier.size(20.dp))
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        if (izbranWorkout != null) {

            WorkoutDetailPage(
                workout = izbranWorkout!!,
                onBack = { izbranWorkout = null },
                onAddExercise = {
                    trenutniWorkoutId = izbranWorkout!!.id
                    poakziExercise = true
                    izbranWorkout = null
                },

                modifier = Modifier.padding(innerPadding)
            )
        }
        else if (poakziExercise) {
            AddaExercise(
                modifier = Modifier.padding(innerPadding),
                workoutId = trenutniWorkoutId,
                onBack = {
                    poakziExercise = false
                    if (trenutniWorkoutId != null) {
                        val workout = viewModel.workoutList.value?.find { it.id == trenutniWorkoutId }
                        izbranWorkout = workout
                    }
                }
            )
        }
        else {
            when (selectedIndex) {
                0 -> HomePage(
                    viewModel = viewModel,
                    onWorkoutClick = { workout ->
                        izbranWorkout = workout
                    }
                )
                1 -> AddaWorkoutPage(
                    viewModel = viewModel,
                    currentWorkoutId = trenutniWorkoutId,
                    onNavigateToExercise = { workoutId ->
                        if (workoutId != -1) {
                            trenutniWorkoutId = workoutId
                        }
                        poakziExercise = true
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