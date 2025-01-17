package com.example.emp_naloga.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class WorkoutViewModel : ViewModel() {

    val WorkoutDao = MainApplication.workoutDatabase.getWorkoutDao()

    val workoutList : LiveData<List<Workout>> = WorkoutDao.getAllWorkout()

    suspend fun addWorkout(title : String, weight: Double): Long {
        return viewModelScope.async(Dispatchers.IO) {
            WorkoutDao.addWorkout(Workout(
                title = title,
                weight = weight,
                createdAt = Date.from(Instant.now())
            ))
        }.await()
    }

    fun deleteWorkout(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            WorkoutDao.deleteWorkout(id)
        }
    }
}