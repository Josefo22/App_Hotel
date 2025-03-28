package com.example.hotelapp.data.converters

import androidx.room.TypeConverter
import com.example.hotelapp.data.model.ReservationStatus

class ReservationStatusConverter {
    @TypeConverter
    fun toReservationStatus(value: String): ReservationStatus = enumValueOf(value)

    @TypeConverter
    fun fromReservationStatus(value: ReservationStatus): String = value.name
} 