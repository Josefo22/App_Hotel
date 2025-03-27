package com.example.hotelapp.data.repositories

import androidx.lifecycle.LiveData
import com.example.hotelapp.data.db.dao.ReservationDao
import com.example.hotelapp.data.model.Reservation
import java.time.LocalDate

class ReservationRepository(private val reservationDao: ReservationDao) {
    
    fun getAllReservations(): LiveData<List<Reservation>> {
        return reservationDao.getAllReservations()
    }
    
    fun getReservationById(id: Long): LiveData<Reservation> {
        return reservationDao.getReservationById(id)
    }
    
    fun getReservationsByCustomerId(customerId: Long): LiveData<List<Reservation>> {
        return reservationDao.getReservationsByCustomerId(customerId)
    }
    
    fun getReservationsByRoomId(roomId: Long): LiveData<List<Reservation>> {
        return reservationDao.getReservationsByRoomId(roomId)
    }
    
    fun getReservationsByStatus(status: String): LiveData<List<Reservation>> {
        return reservationDao.getReservationsByStatus(status)
    }
    
    /**
     * Obtiene todas las reservaciones para una fecha específica
     */
    fun getReservationsForDate(date: LocalDate): LiveData<List<Reservation>> {
        return reservationDao.getReservationsForDate(date)
    }
    
    fun getReservationsBetweenDates(startDate: LocalDate, endDate: LocalDate): LiveData<List<Reservation>> {
        return reservationDao.getReservationsBetweenDates(startDate, endDate)
    }
    
    suspend fun isRoomReservedForDates(roomId: Long, checkInDate: LocalDate, checkOutDate: LocalDate): Boolean {
        val count = reservationDao.isRoomReservedForDates(roomId, checkInDate, checkOutDate)
        return count > 0
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
    
    suspend fun deleteAll() {
        reservationDao.deleteAll()
    }
} 