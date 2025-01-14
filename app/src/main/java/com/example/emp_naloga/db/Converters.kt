package com.example.emp_naloga.db

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromate(date : Date) : Long{
        return date.time
    }
    @TypeConverter
    fun toDate(time : Long) : Date {
        return Date(time)
    }
}