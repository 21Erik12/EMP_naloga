package com.example.emp_naloga.db


import android.app.Application
import androidx.room.Room

class MainApplication : Application() {

    companion object {
        lateinit var workoutDatabase: WorkoutDatabase
    }

    override fun onCreate() {
        super.onCreate()
        workoutDatabase = Room.databaseBuilder(
            applicationContext,
            WorkoutDatabase::class.java,
            WorkoutDatabase.NAME
        )
//           .fallbackToDestructiveMigration() //TO TREBA IZBRISAT PREDEN ODDAMO
            .build()
    }
}