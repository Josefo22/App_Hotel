package com.example.hotelapp.repository

import androidx.lifecycle.LiveData
import com.example.hotelapp.data.dao.ReservationServiceDao
import com.example.hotelapp.data.model.ReservationService

class ReservationServiceRepository(private val reservationServiceDao: ReservationServiceDao) {
    
    suspend fun insert(reservationService: ReservationService): Long {
        return reservationServiceDao.insertReservationService(reservationService)
    }
    
    suspend fun update(reservationService: ReservationService) {
        reservationServiceDao.updateReservationService(reservationService)
    }
    
    suspend fun delete(reservationService: ReservationService) {
        reservationServiceDao.deleteReservationService(reservationService)
    }
    
    fun getServicesByReservation(reservationId: Long): LiveData<List<ReservationService>> {
        return reservationServiceDao.getServicesByReservation(reservationId)
    }
    
    fun getReservationsByService(serviceId: Long): LiveData<List<ReservationService>> {
        return reservationServiceDao.getReservationsByService(serviceId)
    }
} 