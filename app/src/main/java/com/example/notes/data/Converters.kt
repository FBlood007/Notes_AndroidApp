package com.example.notes.data

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun fromDateToLong(value: Date):Long{
        return value.time
    }

    @TypeConverter
    fun frontLongToDate(value:Long): Date{
        return Date(value)
    }

}