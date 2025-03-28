package com.example.hotelapp.repository

import com.example.hotelapp.data.dao.ReservationServiceDao
import com.example.hotelapp.data.entity.ReservationService
import kotlinx.coroutines.flow.Flow

class ReservationServiceRepository(private val reservationServiceDao: ReservationServiceDao) {
    val allReservationServices: Flow<List<ReservationService>> = reservationServiceDao.getAllReservationServices()
    
    fun getReservationServiceById(id: Long): Flow<ReservationService> {
        return reservationServiceDao.getReservationServiceById(id)
    }
    
    fun getServicesByReservation(reservationId: Long): Flow<List<ReservationService>> {
        return reservationServiceDao.getServicesByReservation(reservationId)
    }
    
    fun getReservationsByService(serviceId: Long): Flow<List<ReservationService>> {
        return reservationServiceDao.getReservationsByService(serviceId)
    }
    
    fun getTotalServiceCharge(reservationId: Long): Flow<Double?> {
        return reservationServiceDao.getTotalServiceCharge(reservationId)
    }
    
    suspend fun insert(reservationService: ReservationService): Long {
        return reservationServiceDao.insert(reservationService)
    }
    
    suspend fun update(reservationService: ReservationService) {
        reservationServiceDao.update(reservationService)
    }
    
    suspend fun delete(reservationService: ReservationService) {
        reservationServiceDao.delete(reservationService)
    }
    
    suspend fun deleteAllForReservation(reservationId: Long) {
        reservationServiceDao.deleteAllForReservation(reservationId)
    }
} 