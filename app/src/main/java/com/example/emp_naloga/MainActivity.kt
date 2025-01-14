package com.example.emp_naloga

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.emp_naloga.ui.theme.EMP_NalogaTheme

class MainActivity : ComponentActivity() {
    private val kje = "MainActivity"

    private var zacetekcas: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(kje, "onCreate check")
        enableEdgeToEdge()
        setContent {
            EMP_NalogaTheme {
                MainScreen()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        zacetekcas = SystemClock.elapsedRealtime()
        Log.d(kje, "on start - starting timer check")
    }
    override fun onStop() {
        val trenutnicas = SystemClock.elapsedRealtime()
        val zminusz = trenutnicas - zacetekcas
        super.onStop()
        Log.d(kje, "Time in app: ${formatTime(zminusz)}")
    }
    private fun formatTime(timeInMillis: Long): String {
        val second = (timeInMillis / 1000) % 60
        val minute = (timeInMillis / (1000 * 60)) % 60
        val hour = (timeInMillis / (1000 * 60 * 60))
        return String.format("%02d:%02d:%02d", hour, minute, second)
    }
}
