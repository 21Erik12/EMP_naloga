package com.example.emp_naloga.db


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseViewModel : ViewModel() {
    private val exerciseDao = MainApplication.workoutDatabase.getExerciseDao()

    fun getExercisesForWorkout(workoutId: Int): LiveData<List<Exercise>> {
        return exerciseDao.getExercisesForWorkout(workoutId)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addExercise(name: String, weight: Double, repetitions: Int, workoutId: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            exerciseDao.addExercise(
                Exercise(
                    name = name,
                    weight = weight,
                    repetitions = repetitions,
                    workoutId = workoutId,

                )


            )


        }
    }

    fun deleteExercise(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            exerciseDao.deleteExercise(id)
        }
    }
}