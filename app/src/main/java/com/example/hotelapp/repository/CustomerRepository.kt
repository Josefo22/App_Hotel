package com.example.hotelapp.repository

import com.example.hotelapp.data.dao.CustomerDao
import com.example.hotelapp.data.entity.Customer
import kotlinx.coroutines.flow.Flow

class CustomerRepository(private val customerDao: CustomerDao) {
    val allCustomers: Flow<List<Customer>> = customerDao.getAllCustomers()
    
    fun getCustomerById(id: Long): Flow<Customer> {
        return customerDao.getCustomerById(id)
    }
    
    fun searchCustomersByName(searchQuery: String): Flow<List<Customer>> {
        return customerDao.searchCustomersByName("%$searchQuery%")
    }
    
    fun getCustomerByEmail(email: String): Flow<Customer?> {
        return customerDao.getCustomerByEmail("%$email%")
    }
    
    fun getCustomerByDocumentNumber(documentNumber: String): Flow<Customer?> {
        return customerDao.getCustomerByDocumentNumber("%$documentNumber%")
    }
    
    suspend fun insert(customer: Customer): Long {
        return customerDao.insert(customer)
    }
    
    suspend fun update(customer: Customer) {
        customerDao.update(customer)
    }
    
    suspend fun delete(customer: Customer) {
        customerDao.delete(customer)
    }
} 