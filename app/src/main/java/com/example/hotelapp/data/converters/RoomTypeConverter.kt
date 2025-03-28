package com.example.hotelapp.data.converters

import androidx.room.TypeConverter
import com.example.hotelapp.data.model.RoomType

class RoomTypeConverter {
    @TypeConverter
    fun toRoomType(value: String): RoomType = enumValueOf(value)

    @TypeConverter
    fun fromRoomType(value: RoomType): String = value.name
} 