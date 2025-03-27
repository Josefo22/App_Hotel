package com.example.hotelapp.repository

import androidx.lifecycle.LiveData
import com.example.hotelapp.data.dao.ReservationDao
import com.example.hotelapp.data.model.Reservation
import com.example.hotelapp.data.model.ReservationStatus
import java.util.Date

class ReservationRepository(private val reservationDao: ReservationDao) {
    
    val allReservations: LiveData<List<Reservation>> = reservationDao.getAllReservations()
    
    suspend fun insert(reservation: Reservation): Long {
        return reservationDao.insertReservation(reservation)
    }
    
    suspend fun update(reservation: Reservation) {
        reservationDao.updateReservation(reservation)
    }
    
    suspend fun delete(reservation: Reservation) {
        reservationDao.deleteReservation(reservation)
    }
    
    fun getReservationById(reservationId: Long): LiveData<Reservation> {
        return reservationDao.getReservationById(reservationId)
    }
    
    fun getReservationsByCustomer(customerId: Long): LiveData<List<Reservation>> {
        return reservationDao.getReservationsByCustomer(customerId)
    }
    
    fun getReservationsByRoom(roomId: Long): LiveData<List<Reservation>> {
        return reservationDao.getReservationsByRoom(roomId)
    }
    
    fun getReservationsByStatus(status: ReservationStatus): LiveData<List<Reservation>> {
        return reservationDao.getReservationsByStatus(status)
    }
    
    fun getReservationsByDateRange(startDate: Date, endDate: Date): LiveData<List<Reservation>> {
        return reservationDao.getReservationsByDateRange(startDate, endDate)
    }
    
    suspend fun updateReservationStatus(reservationId: Long, status: ReservationStatus) {
        reservationDao.updateReservationStatus(reservationId, status)
    }
} 