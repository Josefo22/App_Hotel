package com.example.hotelapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.hotelapp.data.db.Converters
import java.io.Serializable
import java.util.Date

enum class PaymentMethod {
    CREDIT_CARD, DEBIT_CARD, CASH, BANK_TRANSFER, PAYPAL, OTHER
}

enum class PaymentStatus {
    PENDING, PAID, PARTIALLY_PAID, CANCELLED
}

@Entity(
    tableName = "invoices",
    foreignKeys = [
        ForeignKey(
            entity = Reservation::class,
            parentColumns = ["id"],
            childColumns = ["reservationId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["id"],
            childColumns = ["customerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("reservationId"),
        Index("customerId")
    ]
)
@TypeConverters(Converters::class)
data class Invoice(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val reservationId: Long,
    val customerId: Long,
    val issueDate: Date = Date(),
    val dueDate: Date,
    val subtotal: Double,
    val tax: Double,
    val total: Double,
    val paymentStatus: PaymentStatus = PaymentStatus.PENDING,
    val paymentMethod: PaymentMethod? = null,
    val paymentDate: Date? = null,
    val notes: String = ""
) : Serializable {
    fun isPaid(): Boolean = paymentStatus == PaymentStatus.PAID
    
    fun getFormattedTotal(): String = String.format("$%.2f", total)
    
    fun getDaysUntilDue(): Int {
        val today = Date()
        if (today.after(dueDate)) return 0
        val diffInMillis = dueDate.time - today.time
        return (diffInMillis / (1000 * 60 * 60 * 24)).toInt()
    }
    
    fun isOverdue(): Boolean {
        return paymentStatus != PaymentStatus.PAID && Date().after(dueDate)
    }
} 