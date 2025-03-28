package com.example.hotelapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hotelapp.data.model.ServiceCategory

@Entity(tableName = "services")
data class Service(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val price: Double,
    val category: ServiceCategory,
    val available: Boolean = true,
    val imageUrl: String? = null
) 