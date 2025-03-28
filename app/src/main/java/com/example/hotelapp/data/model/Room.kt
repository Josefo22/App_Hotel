package com.example.hotelapp.data.model

import java.io.Serializable

enum class RoomType {
    SINGLE,
    DOUBLE,
    TWIN,
    SUITE,
    DELUXE,
    STANDARD,
    FAMILY,
    LUXURY
}

enum class RoomStatus {
    AVAILABLE,
    OCCUPIED,
    MAINTENANCE,
    RESERVED,
    CLEANING
}

// Modelo de dominio, no es una entidad Room
data class Room(
    val id: Long = 0,
    val number: String,
    val floor: Int,
    val type: RoomType,
    val capacity: Int,
    val pricePerNight: Double,
    val status: RoomStatus,
    val description: String,
    val hasWifi: Boolean = false,
    val hasTV: Boolean = false,
    val hasAC: Boolean = false,
    val hasMinibar: Boolean = false,
    val imageUrl: String? = null
) : Serializable {
    fun isAvailable(): Boolean = status == RoomStatus.AVAILABLE
    
    companion object {
        fun fromEntity(entity: com.example.hotelapp.data.entity.HotelRoom): Room {
            return Room(
                id = entity.id,
                number = entity.number,
                floor = entity.floor,
                type = entity.type,
                capacity = entity.capacity,
                pricePerNight = entity.pricePerNight,
                status = entity.status,
                description = entity.description,
                hasWifi = entity.hasWifi,
                hasTV = entity.hasTV,
                hasAC = entity.hasAC,
                hasMinibar = entity.hasMinibar,
                imageUrl = entity.imageUrl
            )
        }
    }
    
    fun toEntity(): com.example.hotelapp.data.entity.HotelRoom {
        return com.example.hotelapp.data.entity.HotelRoom(
            id = this.id,
            number = this.number,
            floor = this.floor,
            type = this.type,
            capacity = this.capacity,
            pricePerNight = this.pricePerNight,
            status = this.status,
            description = this.description,
            hasWifi = this.hasWifi,
            hasTV = this.hasTV,
            hasAC = this.hasAC,
            hasMinibar = this.hasMinibar,
            imageUrl = this.imageUrl
        )
    }
    
    fun getFormattedPrice(): String = String.format("$%.2f", pricePerNight)
    
    fun getStatusColor(): Int {
        return when (status) {
            RoomStatus.AVAILABLE -> android.graphics.Color.GREEN
            RoomStatus.OCCUPIED -> android.graphics.Color.RED
            RoomStatus.MAINTENANCE -> android.graphics.Color.YELLOW
            RoomStatus.RESERVED -> android.graphics.Color.BLUE
            RoomStatus.CLEANING -> android.graphics.Color.GRAY
        }
    }
} 