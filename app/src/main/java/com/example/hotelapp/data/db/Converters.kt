package com.example.hotelapp.data.db

import androidx.room.TypeConverter
import com.example.hotelapp.data.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toRoomType(value: String) = enumValueOf<RoomType>(value)

    @TypeConverter
    fun fromRoomType(value: RoomType) = value.name

    @TypeConverter
    fun toRoomStatus(value: String) = enumValueOf<RoomStatus>(value)

    @TypeConverter
    fun fromRoomStatus(value: RoomStatus) = value.name
    
    @TypeConverter
    fun toServiceCategory(value: String) = enumValueOf<ServiceCategory>(value)

    @TypeConverter
    fun fromServiceCategory(value: ServiceCategory) = value.name
    
    @TypeConverter
    fun toReservationStatus(value: String) = enumValueOf<ReservationStatus>(value)

    @TypeConverter
    fun fromReservationStatus(value: ReservationStatus) = value.name
    
    @TypeConverter
    fun toPaymentStatus(value: String) = enumValueOf<PaymentStatus>(value)

    @TypeConverter
    fun fromPaymentStatus(value: PaymentStatus) = value.name
    
    @TypeConverter
    fun toPaymentMethod(value: String) = enumValueOf<PaymentMethod>(value)

    @TypeConverter
    fun fromPaymentMethod(value: PaymentMethod) = value.name
} 