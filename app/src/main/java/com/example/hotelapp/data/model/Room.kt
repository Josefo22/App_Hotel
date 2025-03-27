package com.example.hotelapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.hotelapp.data.db.Converters
import java.io.Serializable

enum class RoomType {
    SINGLE, DOUBLE, SUITE, LUXURY, FAMILY
}

enum class RoomStatus {
    AVAILABLE, OCCUPIED, MAINTENANCE
}

@Entity(tableName = "rooms")
@TypeConverters(Converters::class)
data class Room(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val number: String,
    val type: RoomType,
    val capacity: Int,
    val pricePerNight: Double,
    val status: RoomStatus = RoomStatus.AVAILABLE,
    val description: String = "",
    val floor: Int = 1,
    val amenities: List<String> = emptyList(),
    val imageUrl: String = ""
) : Serializable {
    
    fun isAvailable(): Boolean = status == RoomStatus.AVAILABLE
    
    fun getFormattedPrice(): String = String.format("$%.2f", pricePerNight)
    
    fun getStatusColor(): Int {
        return when (status) {
            RoomStatus.AVAILABLE -> android.graphics.Color.GREEN
            RoomStatus.OCCUPIED -> android.graphics.Color.RED
            RoomStatus.MAINTENANCE -> android.graphics.Color.YELLOW
        }
    }
} 