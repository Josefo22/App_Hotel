package com.example.hotelapp.data.dao

import androidx.room.*
import com.example.hotelapp.data.entity.Customer
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM customers ORDER BY name ASC")
    fun getAllCustomers(): Flow<List<Customer>>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM customers WHERE id = :id")
    fun getCustomerById(id: Long): Flow<Customer>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM customers WHERE name LIKE '%' || :searchQuery || '%' ORDER BY name ASC")
    fun searchCustomersByName(searchQuery: String): Flow<List<Customer>>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM customers WHERE email = :email")
    fun getCustomerByEmail(email: String): Flow<Customer?>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM customers WHERE documentNumber = :documentNumber")
    fun getCustomerByDocumentNumber(documentNumber: String): Flow<Customer?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customer: Customer): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(customers: List<Customer>)
    
    @Update
    suspend fun update(customer: Customer)
    
    @Delete
    suspend fun delete(customer: Customer)
} 