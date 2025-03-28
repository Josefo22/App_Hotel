package com.example.hotelapp.repository

import com.example.hotelapp.data.dao.ServiceDao
import com.example.hotelapp.data.entity.Service
import kotlinx.coroutines.flow.Flow

class ServiceRepository(private val serviceDao: ServiceDao) {
    val allServices: Flow<List<Service>> = serviceDao.getAllServices()
    
    fun getServiceById(id: Long): Flow<Service> {
        return serviceDao.getServiceById(id)
    }
    
    fun getAvailableServices(): Flow<List<Service>> {
        return serviceDao.getAvailableServices()
    }
    
    fun searchServices(searchQuery: String): Flow<List<Service>> {
        return serviceDao.searchServices("%$searchQuery%")
    }
    
    suspend fun insert(service: Service): Long {
        return serviceDao.insert(service)
    }
    
    suspend fun update(service: Service) {
        serviceDao.update(service)
    }
    
    suspend fun delete(service: Service) {
        serviceDao.delete(service)
    }
    
    suspend fun updateServiceAvailability(serviceId: Long, available: Boolean) {
        serviceDao.updateServiceAvailability(serviceId, available)
    }
} 