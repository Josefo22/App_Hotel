package com.example.hotelapp.data.dao

import androidx.room.*
import com.example.hotelapp.data.entity.Service
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceDao {
    @Query("SELECT * FROM services ORDER BY name ASC")
    fun getAllServices(): Flow<List<Service>>
    
    @Query("SELECT * FROM services WHERE id = :id")
    fun getServiceById(id: Long): Flow<Service>
    
    @Query("SELECT * FROM services WHERE available = 1 ORDER BY name ASC")
    fun getAvailableServices(): Flow<List<Service>>
    
    @Query("SELECT * FROM services WHERE name LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchServices(searchQuery: String): Flow<List<Service>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(service: Service): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(services: List<Service>)
    
    @Update
    suspend fun update(service: Service)
    
    @Delete
    suspend fun delete(service: Service)
    
    @Query("UPDATE services SET available = :available WHERE id = :serviceId")
    suspend fun updateServiceAvailability(serviceId: Long, available: Boolean)
} 