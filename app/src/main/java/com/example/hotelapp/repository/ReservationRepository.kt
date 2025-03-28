package com.example.hotelapp.repository

import com.example.hotelapp.data.dao.ReservationDao
import com.example.hotelapp.data.entity.Reservation
import com.example.hotelapp.data.model.ReservationStatus
import kotlinx.coroutines.flow.Flow
import java.util.Date

class ReservationRepository(private val reservationDao: ReservationDao) {
    val allReservations: Flow<List<Reservation>> = reservationDao.getAllReservations()
    
    fun getReservationById(id: Long): Flow<Reservation> {
        return reservationDao.getReservationById(id)
    }
    
    fun getReservationsByCustomer(customerId: Long): Flow<List<Reservation>> {
        return reservationDao.getReservationsByCustomer(customerId)
    }
    
    fun getReservationsByRoom(roomId: Long): Flow<List<Reservation>> {
        return reservationDao.getReservationsByRoom(roomId)
    }
    
    fun getReservationsByStatus(status: ReservationStatus): Flow<List<Reservation>> {
        return reservationDao.getReservationsByStatus(status)
    }
    
    fun getReservationsInDateRange(startDate: Date, endDate: Date): Flow<List<Reservation>> {
        return reservationDao.getReservationsInDateRange(startDate, endDate)
    }
    
    fun getConflictingReservations(roomId: Long, startDate: Date, endDate: Date): Flow<List<Reservation>> {
        return reservationDao.getConflictingReservations(roomId, startDate, endDate)
    }
    
    suspend fun insert(reservation: Reservation): Long {
        return reservationDao.insert(reservation)
    }
    
    suspend fun update(reservation: Reservation) {
        reservationDao.update(reservation)
    }
    
    suspend fun delete(reservation: Reservation) {
        reservationDao.delete(reservation)
    }
    
    suspend fun updateReservationStatus(reservationId: Long, status: ReservationStatus) {
        reservationDao.updateReservationStatus(reservationId, status)
    }
} 