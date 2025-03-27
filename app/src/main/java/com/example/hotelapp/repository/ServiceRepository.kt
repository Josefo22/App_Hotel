package com.example.hotelapp.repository

import androidx.lifecycle.LiveData
import com.example.hotelapp.data.dao.ServiceDao
import com.example.hotelapp.data.model.Service
import com.example.hotelapp.data.model.ServiceCategory

class ServiceRepository(private val serviceDao: ServiceDao) {
    
    val allServices: LiveData<List<Service>> = serviceDao.getAllServices()
    
    suspend fun insert(service: Service): Long {
        return serviceDao.insert(service)
    }
    
    suspend fun update(service: Service) {
        serviceDao.update(service)
    }
    
    suspend fun delete(service: Service) {
        serviceDao.delete(service)
    }
    
    fun getServiceById(serviceId: Long): LiveData<Service> {
        return serviceDao.getServiceById(serviceId)
    }
    
    fun getServicesByCategory(category: ServiceCategory): LiveData<List<Service>> {
        return serviceDao.getServicesByCategory(category)
    }
    
    fun searchServices(query: String): LiveData<List<Service>> {
        return serviceDao.searchServices("%$query%")
    }
} 