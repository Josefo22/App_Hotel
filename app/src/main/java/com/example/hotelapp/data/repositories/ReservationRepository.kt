package com.example.hotelapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.hotelapp.data.dao.ReservationDao
import com.example.hotelapp.data.entity.Reservation
import com.example.hotelapp.data.model.ReservationStatus
import kotlinx.coroutines.flow.Flow
import java.util.Date

class ReservationRepository(private val reservationDao: ReservationDao) {
    
    fun getAllReservations(): LiveData<List<Reservation>> {
        return reservationDao.getAllReservations().asLiveData()
    }
    
    fun getReservationById(id: Long): LiveData<Reservation> {
        return reservationDao.getReservationById(id).asLiveData()
    }
    
    fun getReservationsByCustomer(customerId: Long): LiveData<List<Reservation>> {
        return reservationDao.getReservationsByCustomer(customerId).asLiveData()
    }
    
    fun getReservationsByRoom(roomId: Long): LiveData<List<Reservation>> {
        return reservationDao.getReservationsByRoom(roomId).asLiveData()
    }
    
    fun getReservationsByStatus(status: ReservationStatus): LiveData<List<Reservation>> {
        return reservationDao.getReservationsByStatus(status).asLiveData()
    }
    
    /**
     * Obtiene todas las reservaciones para un rango de fechas
     */
    fun getReservationsInDateRange(startDate: Date, endDate: Date): LiveData<List<Reservation>> {
        return reservationDao.getReservationsInDateRange(startDate, endDate).asLiveData()
    }
    
    suspend fun getConflictingReservations(
        roomId: Long,
        checkInDate: Date,
        checkOutDate: Date,
        excludeStatus: ReservationStatus = ReservationStatus.CANCELLED
    ): List<Reservation> {
        return reservationDao.getConflictingReservations(roomId, checkInDate, checkOutDate, excludeStatus).asLiveData().value ?: emptyList()
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