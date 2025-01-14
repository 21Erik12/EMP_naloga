package com.example.emp_naloga

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.emp_naloga.ui.theme.EMP_NalogaTheme

class MainActivity : ComponentActivity() {
    private val tag = "MainActivity"
    private var startTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate called")
        enableEdgeToEdge()
        setContent {
            EMP_NalogaTheme {
                MainScreen()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        startTime = SystemClock.elapsedRealtime()
        Log.d(tag, "on start - starting timer")
    }


    override fun onStop() {
        val currentTime = SystemClock.elapsedRealtime()
        val timeElapsed = currentTime - startTime
        super.onStop()
        Log.d(tag, "Time in app: ${formatTime(timeElapsed)}")
    }


    private fun formatTime(timeInMillis: Long): String {
        val second = (timeInMillis / 1000) % 60
        val minute = (timeInMillis / (1000 * 60)) % 60
        val hour = (timeInMillis / (1000 * 60 * 60))
        return String.format("%02d:%02d:%02d", hour, minute, second)
    }
}
