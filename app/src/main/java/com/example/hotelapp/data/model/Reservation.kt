package com.example.hotelapp.data.model

import java.io.Serializable
import java.util.Date

enum class ReservationStatus {
    PENDING,
    CONFIRMED,
    CHECKED_IN,
    COMPLETED,
    CANCELLED,
    NO_SHOW
}

data class Reservation(
    val id: Long = 0,
    val customerId: Long,
    val roomId: Long,
    val checkInDate: Date,
    val checkOutDate: Date,
    val numberOfGuests: Int,
    val totalPrice: Double,
    val status: ReservationStatus = ReservationStatus.PENDING,
    val specialRequests: String? = null,
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

data class ReservationModel(
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
) {
    companion object {
        fun fromEntity(entity: com.example.hotelapp.data.entity.Reservation): ReservationModel {
            return ReservationModel(
                id = entity.id,
                customerId = entity.customerId,
                roomId = entity.roomId,
                checkInDate = entity.checkInDate,
                checkOutDate = entity.checkOutDate,
                numberOfGuests = entity.numberOfGuests,
                totalPrice = entity.totalPrice,
                status = entity.status,
                notes = entity.notes,
                createdAt = entity.createdAt
            )
        }
    }
    
    fun toEntity(): com.example.hotelapp.data.entity.Reservation {
        return com.example.hotelapp.data.entity.Reservation(
            id = this.id,
            customerId = this.customerId,
            roomId = this.roomId,
            checkInDate = this.checkInDate,
            checkOutDate = this.checkOutDate,
            numberOfGuests = this.numberOfGuests,
            totalPrice = this.totalPrice,
            status = this.status,
            notes = this.notes,
            createdAt = this.createdAt
        )
    }
} 