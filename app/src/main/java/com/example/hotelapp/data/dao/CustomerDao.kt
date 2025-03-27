package com.example.hotelapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hotelapp.data.model.Customer

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customers ORDER BY name")
    fun getAllCustomers(): LiveData<List<Customer>>
    
    @Query("SELECT * FROM customers WHERE id = :customerId")
    fun getCustomerById(customerId: Long): LiveData<Customer>
    
    @Query("SELECT * FROM customers WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchCustomersByName(searchQuery: String): LiveData<List<Customer>>
    
    @Query("SELECT * FROM customers WHERE email LIKE '%' || :searchQuery || '%'")
    fun searchCustomersByEmail(searchQuery: String): LiveData<List<Customer>>
    
    @Query("SELECT * FROM customers WHERE documentNumber = :documentNumber")
    fun getCustomerByDocumentNumber(documentNumber: String): LiveData<Customer>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: Customer): Long
    
    @Update
    suspend fun updateCustomer(customer: Customer)
    
    @Delete
    suspend fun deleteCustomer(customer: Customer)
} 