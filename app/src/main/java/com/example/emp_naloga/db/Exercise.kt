package com.example.emp_naloga.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String,
    var weight: Double,
    var repetitions: Int,
    var workoutId: Int
)
