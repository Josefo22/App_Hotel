package com.example.hotelapp.data.converters

import androidx.room.TypeConverter
import com.example.hotelapp.data.model.ServiceCategory

class ServiceCategoryConverter {
    @TypeConverter
    fun toServiceCategory(value: String): ServiceCategory = enumValueOf(value)

    @TypeConverter
    fun fromServiceCategory(value: ServiceCategory): String = value.name
} 