package com.example.hotelapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hotelapp.data.model.Service
import com.example.hotelapp.data.model.ServiceCategory

@Dao
interface ServiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(service: Service): Long

    @Update
    suspend fun update(service: Service)

    @Delete
    suspend fun delete(service: Service)

    @Query("SELECT * FROM services")
    fun getAllServices(): LiveData<List<Service>>

    @Query("SELECT * FROM services WHERE id = :serviceId")
    fun getServiceById(serviceId: Long): LiveData<Service>

    @Query("SELECT * FROM services WHERE category = :category")
    fun getServicesByCategory(category: ServiceCategory): LiveData<List<Service>>

    @Query("SELECT * FROM services WHERE name LIKE :searchQuery")
    fun searchServices(searchQuery: String): LiveData<List<Service>>
} 