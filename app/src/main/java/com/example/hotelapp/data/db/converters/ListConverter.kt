package com.example.hotelapp.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Conversor de listas para Room
 * Permite almacenar listas en la base de datos
 */
class ListConverter {
    @TypeConverter
    fun fromString(value: String?): List<String> {
        if (value == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
    
    @TypeConverter
    fun fromList(list: List<String>?): String {
        if (list == null) {
            return "[]"
        }
        return Gson().toJson(list)
    }
} 