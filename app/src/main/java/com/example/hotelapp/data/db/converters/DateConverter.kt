package com.example.hotelapp.data.db.converters

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

/**
 * Conversor de fechas para Room
 * Permite almacenar objetos LocalDate en la base de datos
 */
class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return value?.let {
            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
        }
    }
    
    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? {
        return date?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
    }
} 