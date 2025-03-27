package com.example.hotelapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "customers")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val email: String,
    val phone: String,
    val address: String = "",
    val documentNumber: String = "",
    val notes: String = ""
) : Serializable {
    fun getFullContactInfo(): String {
        return "Email: $email\nTeléfono: $phone\nDirección: $address"
    }
} 