package com.example.emp_naloga.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM Exercise")
    fun getAllExercises(): LiveData<List<Exercise>>

    @Query("SELECT * FROM Exercise WHERE workoutId = :workoutId")
    fun getExercisesForWorkout(workoutId: Int): LiveData<List<Exercise>>

    @Insert
    fun addExercise(exercise: Exercise)

    @Query("DELETE FROM Exercise WHERE id = :id")
    fun deleteExercise(id: Int)
}