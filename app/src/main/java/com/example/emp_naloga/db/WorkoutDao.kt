package com.example.emp_naloga.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM Workout ORDER BY createdAt DESC")
    fun getAllWorkout() : LiveData<List<Workout>>

    @Insert
    fun addWorkout(workout: Workout): Long

    @Query("Delete FROM Workout where id = :id")
    fun deleteWorkout(id : Int)
}