package com.example.hotelapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hotelapp.data.model.Reservation
import com.example.hotelapp.data.model.ReservationStatus
import java.util.Date

@Dao
interface ReservationDao {
    @Query("SELECT * FROM reservations ORDER BY checkInDate DESC")
    fun getAllReservations(): LiveData<List<Reservation>>
    
    @Query("SELECT * FROM reservations WHERE id = :reservationId")
    fun getReservationById(reservationId: Long): LiveData<Reservation>
    
    @Query("SELECT * FROM reservations WHERE customerId = :customerId ORDER BY checkInDate DESC")
    fun getReservationsByCustomer(customerId: Long): LiveData<List<Reservation>>
    
    @Query("SELECT * FROM reservations WHERE roomId = :roomId ORDER BY checkInDate DESC")
    fun getReservationsByRoom(roomId: Long): LiveData<List<Reservation>>
    
    @Query("SELECT * FROM reservations WHERE status = :status ORDER BY checkInDate DESC")
    fun getReservationsByStatus(status: ReservationStatus): LiveData<List<Reservation>>
    
    @Query("SELECT * FROM reservations WHERE checkInDate >= :startDate AND checkOutDate <= :endDate ORDER BY checkInDate")
    fun getReservationsByDateRange(startDate: Date, endDate: Date): LiveData<List<Reservation>>
    
    @Query("SELECT * FROM reservations WHERE status = :status AND checkInDate >= :date ORDER BY checkInDate")
    fun getUpcomingReservationsByStatus(status: ReservationStatus, date: Date): LiveData<List<Reservation>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReservation(reservation: Reservation): Long
    
    @Update
    suspend fun updateReservation(reservation: Reservation)
    
    @Delete
    suspend fun deleteReservation(reservation: Reservation)
    
    @Query("UPDATE reservations SET status = :newStatus WHERE id = :reservationId")
    suspend fun updateReservationStatus(reservationId: Long, newStatus: ReservationStatus)
} 