package com.example.hotelapp.data.dao

import androidx.room.*
import com.example.hotelapp.data.entity.Reservation
import com.example.hotelapp.data.model.ReservationStatus
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface ReservationDao {
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM reservations ORDER BY checkInDate DESC")
    fun getAllReservations(): Flow<List<Reservation>>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM reservations WHERE id = :id")
    fun getReservationById(id: Long): Flow<Reservation>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM reservations WHERE customerId = :customerId ORDER BY checkInDate DESC")
    fun getReservationsByCustomer(customerId: Long): Flow<List<Reservation>>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM reservations WHERE roomId = :roomId ORDER BY checkInDate DESC")
    fun getReservationsByRoom(roomId: Long): Flow<List<Reservation>>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM reservations WHERE status = :status ORDER BY checkInDate DESC")
    fun getReservationsByStatus(status: ReservationStatus): Flow<List<Reservation>>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM reservations WHERE " +
           "(checkInDate BETWEEN :startDate AND :endDate) OR " +
           "(checkOutDate BETWEEN :startDate AND :endDate) OR " +
           "(checkInDate <= :startDate AND checkOutDate >= :endDate) " +
           "ORDER BY checkInDate ASC")
    fun getReservationsInDateRange(startDate: Date, endDate: Date): Flow<List<Reservation>>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM reservations WHERE " +
           "roomId = :roomId AND " +
           "status != :excludeStatus AND " +
           "((checkInDate BETWEEN :checkInDate AND :checkOutDate) OR " +
           "(checkOutDate BETWEEN :checkInDate AND :checkOutDate) OR " +
           "(checkInDate <= :checkInDate AND checkOutDate >= :checkOutDate))")
    fun getConflictingReservations(
        roomId: Long,
        checkInDate: Date,
        checkOutDate: Date,
        excludeStatus: ReservationStatus = ReservationStatus.CANCELLED
    ): Flow<List<Reservation>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reservation: Reservation): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(reservations: List<Reservation>)
    
    @Update
    suspend fun update(reservation: Reservation)
    
    @Delete
    suspend fun delete(reservation: Reservation)
    
    @Query("UPDATE reservations SET status = :status WHERE id = :reservationId")
    suspend fun updateReservationStatus(reservationId: Long, status: ReservationStatus)
} 