package com.example.hotelapp.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.hotelapp.data.model.ReservationStatus
import java.util.Date

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
            entity = HotelRoom::class,
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
data class Reservation(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val customerId: Long,
    val roomId: Long,
    val checkInDate: Date,
    val checkOutDate: Date,
    val numberOfGuests: Int,
    val totalPrice: Double,
    val status: ReservationStatus,
    val notes: String? = null,
    val createdAt: Date = Date()
) 