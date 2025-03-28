package com.example.hotelapp.data.converters

import androidx.room.TypeConverter
import com.example.hotelapp.data.model.RoomStatus

class RoomStatusConverter {
    @TypeConverter
    fun toRoomStatus(value: String): RoomStatus = enumValueOf(value)

    @TypeConverter
    fun fromRoomStatus(value: RoomStatus): String = value.name
} 