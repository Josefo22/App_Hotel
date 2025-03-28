package com.example.hotelapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hotelapp.data.model.RoomStatus
import com.example.hotelapp.data.model.RoomType

@Entity(tableName = "rooms")
data class HotelRoom(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var number: String = "",
    var floor: Int = 0,
    var type: RoomType = RoomType.STANDARD,
    var capacity: Int = 0,
    var pricePerNight: Double = 0.0,
    var status: RoomStatus = RoomStatus.AVAILABLE,
    var description: String = "",
    var imageUrl: String? = null,
    var amenities: String? = null,
    var hasWifi: Boolean = false,
    var hasTV: Boolean = false,
    var hasAC: Boolean = false,
    var hasMinibar: Boolean = false
) {
    // Constructor vacío necesario para Room
    constructor() : this(
        id = 0,
        number = "",
        floor = 0,
        type = RoomType.STANDARD,
        capacity = 0,
        pricePerNight = 0.0,
        status = RoomStatus.AVAILABLE,
        description = "",
        imageUrl = null,
        amenities = null,
        hasWifi = false,
        hasTV = false,
        hasAC = false,
        hasMinibar = false
    )
} 