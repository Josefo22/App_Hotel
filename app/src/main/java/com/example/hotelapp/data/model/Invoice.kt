package com.example.hotelapp.data.model

import java.io.Serializable
import java.util.Date

enum class PaymentStatus {
    PAID,
    UNPAID,
    OVERDUE,
    CANCELLED
}

data class Invoice(
    val id: Long = 0,
    val reservationId: Long,
    val customerId: Long,
    val issueDate: Date,
    val dueDate: Date,
    val subtotal: Double,
    val tax: Double,
    val total: Double,
    val paymentStatus: PaymentStatus = PaymentStatus.UNPAID,
    val paymentMethod: String? = null,
    val paymentDate: Date? = null,
    val notes: String? = null
) : Serializable {
    fun isPaid(): Boolean = paymentStatus == PaymentStatus.PAID
    
    fun isOverdue(): Boolean {
        return paymentStatus != PaymentStatus.PAID && Date().after(dueDate)
    }
}

data class InvoiceModel(
    val id: Long = 0,
    val reservationId: Long,
    val invoiceNumber: String,
    val issueDate: Date,
    val dueDate: Date,
    val roomCharge: Double,
    val serviceCharge: Double,
    val taxAmount: Double,
    val totalAmount: Double,
    val isPaid: Boolean = false,
    val paymentDate: Date? = null,
    val paymentMethod: String? = null
) {
    companion object {
        fun fromEntity(entity: com.example.hotelapp.data.entity.Invoice): InvoiceModel {
            return InvoiceModel(
                id = entity.id,
                reservationId = entity.reservationId,
                invoiceNumber = entity.invoiceNumber,
                issueDate = entity.issueDate,
                dueDate = entity.dueDate,
                roomCharge = entity.roomCharge,
                serviceCharge = entity.serviceCharge,
                taxAmount = entity.taxAmount,
                totalAmount = entity.totalAmount,
                isPaid = entity.isPaid,
                paymentDate = entity.paymentDate,
                paymentMethod = entity.paymentMethod
            )
        }
    }
    
    fun toEntity(): com.example.hotelapp.data.entity.Invoice {
        val entity = com.example.hotelapp.data.entity.Invoice(
            id = this.id,
            reservationId = this.reservationId,
            customerId = 0, // Este campo es para compatibilidad con la estructura antigua
            issueDate = this.issueDate,
            dueDate = this.dueDate,
            subtotal = this.roomCharge + this.serviceCharge,
            tax = this.taxAmount,
            total = this.totalAmount,
            paymentStatus = if (this.isPaid) "PAID" else "UNPAID",
            paymentDate = this.paymentDate,
            paymentMethod = this.paymentMethod
        )
        
        // Estos campos serán ignorados por Room pero mantenidos en el modelo
        entity.invoiceNumber = this.invoiceNumber
        entity.roomCharge = this.roomCharge
        entity.serviceCharge = this.serviceCharge
        entity.taxAmount = this.taxAmount
        entity.totalAmount = this.totalAmount
        entity.isPaid = this.isPaid
        
        return entity
    }
} 