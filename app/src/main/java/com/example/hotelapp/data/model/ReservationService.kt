package com.example.hotelapp.data.model

import java.io.Serializable
import java.util.Date

// Modelo de dominio, no es una entidad de Room
data class ReservationService(
    val id: Long = 0,
    val reservationId: Long,
    val serviceId: Long,
    val quantity: Int,
    val date: Date,
    val price: Double
) : Serializable {
    fun getTotal(): Double = price * quantity
    
    fun getFormattedTotal(): String = String.format("$%.2f", getTotal())
    
    companion object {
        fun fromEntity(entity: com.example.hotelapp.data.entity.ReservationService): ReservationService {
            return ReservationService(
                id = entity.id,
                reservationId = entity.reservationId,
                serviceId = entity.serviceId,
                quantity = entity.quantity,
                date = entity.date,
                price = entity.price
            )
        }
    }
    
    fun toEntity(): com.example.hotelapp.data.entity.ReservationService {
        return com.example.hotelapp.data.entity.ReservationService(
            id = this.id,
            reservationId = this.reservationId,
            serviceId = this.serviceId,
            quantity = this.quantity,
            date = this.date,
            price = this.price
        )
    }
} 