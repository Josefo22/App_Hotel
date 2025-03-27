package com.example.hotelapp.repository

import androidx.lifecycle.LiveData
import com.example.hotelapp.data.dao.CustomerDao
import com.example.hotelapp.data.model.Customer

class CustomerRepository(private val customerDao: CustomerDao) {
    
    val allCustomers: LiveData<List<Customer>> = customerDao.getAllCustomers()
    
    suspend fun insert(customer: Customer): Long {
        return customerDao.insertCustomer(customer)
    }
    
    suspend fun update(customer: Customer) {
        customerDao.updateCustomer(customer)
    }
    
    suspend fun delete(customer: Customer) {
        customerDao.deleteCustomer(customer)
    }
    
    fun getCustomerById(customerId: Long): LiveData<Customer> {
        return customerDao.getCustomerById(customerId)
    }
    
    fun searchCustomers(query: String): LiveData<List<Customer>> {
        return customerDao.searchCustomersByName(query)
    }
    
    fun getCustomerByDocumentNumber(documentNumber: String): LiveData<Customer> {
        return customerDao.getCustomerByDocumentNumber(documentNumber)
    }
} 