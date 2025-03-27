package com.example.hotelapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.hotelapp.data.db.Converters
import java.io.Serializable
import java.util.Date

@Entity(
    tableName = "reservation_services",
    foreignKeys = [
        ForeignKey(
            entity = Reservation::class,
            parentColumns = ["id"],
            childColumns = ["reservationId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Service::class,
            parentColumns = ["id"],
            childColumns = ["serviceId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("reservationId"),
        Index("serviceId")
    ]
)
@TypeConverters(Converters::class)
data class ReservationService(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val reservationId: Long,
    val serviceId: Long,
    val quantity: Int,
    val date: Date,
    val price: Double,
    val notes: String = ""
) : Serializable {
    fun getTotal(): Double = price * quantity
    
    fun getFormattedTotal(): String = String.format("$%.2f", getTotal())
} 