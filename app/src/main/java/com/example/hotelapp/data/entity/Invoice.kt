package com.example.hotelapp.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "invoices",
    foreignKeys = [
        ForeignKey(
            entity = Reservation::class,
            parentColumns = ["id"],
            childColumns = ["reservationId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("reservationId")]
)
data class Invoice(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var reservationId: Long = 0,
    var customerId: Long = 0,
    var issueDate: Date = Date(),
    var dueDate: Date = Date(),
    var subtotal: Double = 0.0,
    var tax: Double = 0.0,
    var total: Double = 0.0,
    var paymentStatus: String? = null,
    var paymentMethod: String? = null,
    var paymentDate: Date? = null,
    var notes: String? = null,
    var invoiceNumber: String = "",
    var roomCharge: Double = 0.0,
    var serviceCharge: Double = 0.0,
    var taxAmount: Double = 0.0,
    var totalAmount: Double = 0.0,
    var isPaid: Boolean = false
) {
    // Constructor vacío necesario para Room
    constructor() : this(
        id = 0,
        reservationId = 0,
        customerId = 0,
        issueDate = Date(),
        dueDate = Date(),
        subtotal = 0.0,
        tax = 0.0,
        total = 0.0,
        paymentStatus = null,
        paymentMethod = null,
        paymentDate = null,
        notes = null,
        invoiceNumber = "",
        roomCharge = 0.0,
        serviceCharge = 0.0,
        taxAmount = 0.0,
        totalAmount = 0.0,
        isPaid = false
    )
} 