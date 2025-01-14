package com.example.emp_naloga.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Workout::class, Exercise::class], version = 1)
@TypeConverters(Converters::class)
abstract class WorkoutDatabase : RoomDatabase() {
    companion object {
        const val NAME = "Workout_DB"
    }

    abstract fun getWorkoutDao(): WorkoutDao
    abstract fun getExerciseDao(): ExerciseDao
}