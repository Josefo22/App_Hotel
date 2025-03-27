package com.example.hotelapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

enum class ServiceCategory {
    FOOD, CLEANING, WELLNESS, TRANSPORT, ENTERTAINMENT, OTHER
}

@Entity(tableName = "services")
data class Service(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val price: Double,
    val category: ServiceCategory,
    val available: Boolean = true,
    val imageUrl: String = ""
) : Serializable {
    fun getFormattedPrice(): String = String.format("$%.2f", price)
} 