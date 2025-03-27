package com.example.hotelapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.hotelapp.data.db.Converters
import java.io.Serializable
import java.util.Date

enum class ReservationStatus {
    CONFIRMED, CHECKED_IN, CHECKED_OUT, CANCELLED
}

@Entity(
    tableName = "reservations",
    foreignKeys = [
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["id"],
            childColumns = ["customerId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Room::class,
            parentColumns = ["id"],
            childColumns = ["roomId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("customerId"),
        Index("roomId")
    ]
)
@TypeConverters(Converters::class)
data class Reservation(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val customerId: Long,
    val roomId: Long,
    val checkInDate: Date,
    val checkOutDate: Date,
    val numberOfGuests: Int,
    val totalPrice: Double,
    val status: ReservationStatus = ReservationStatus.CONFIRMED,
    val specialRequests: String = "",
    val createdAt: Date = Date()
) : Serializable {
    fun calculateNights(): Int {
        val diffInMillis = checkOutDate.time - checkInDate.time
        return (diffInMillis / (1000 * 60 * 60 * 24)).toInt()
    }
    
    fun getFormattedTotalPrice(): String = String.format("$%.2f", totalPrice)
    
    fun isActive(): Boolean = 
        status == ReservationStatus.CONFIRMED || status == ReservationStatus.CHECKED_IN
} 