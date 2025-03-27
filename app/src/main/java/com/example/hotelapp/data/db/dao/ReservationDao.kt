package com.example.hotelapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hotelapp.data.model.Reservation
import java.time.LocalDate

@Dao
interface ReservationDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reservation: Reservation): Long
    
    @Update
    suspend fun update(reservation: Reservation)
    
    @Delete
    suspend fun delete(reservation: Reservation)
    
    @Query("SELECT * FROM reservations")
    fun getAllReservations(): LiveData<List<Reservation>>
    
    @Query("SELECT * FROM reservations WHERE id = :id")
    fun getReservationById(id: Long): LiveData<Reservation>
    
    @Query("SELECT * FROM reservations WHERE customerId = :customerId")
    fun getReservationsByCustomerId(customerId: Long): LiveData<List<Reservation>>
    
    @Query("SELECT * FROM reservations WHERE roomId = :roomId")
    fun getReservationsByRoomId(roomId: Long): LiveData<List<Reservation>>
    
    @Query("SELECT * FROM reservations WHERE status = :status")
    fun getReservationsByStatus(status: String): LiveData<List<Reservation>>
    
    /**
     * Obtiene todas las reservaciones para una fecha específica
     */
    @Query("SELECT * FROM reservations WHERE (checkInDate <= :date AND checkOutDate >= :date) OR checkInDate = :date")
    fun getReservationsForDate(date: LocalDate): LiveData<List<Reservation>>
    
    @Query("SELECT * FROM reservations WHERE checkInDate BETWEEN :startDate AND :endDate OR checkOutDate BETWEEN :startDate AND :endDate")
    fun getReservationsBetweenDates(startDate: LocalDate, endDate: LocalDate): LiveData<List<Reservation>>
    
    @Query("SELECT COUNT(*) FROM reservations WHERE roomId = :roomId AND ((checkInDate <= :checkInDate AND checkOutDate >= :checkInDate) OR (checkInDate <= :checkOutDate AND checkOutDate >= :checkOutDate) OR (checkInDate >= :checkInDate AND checkOutDate <= :checkOutDate))")
    suspend fun isRoomReservedForDates(roomId: Long, checkInDate: LocalDate, checkOutDate: LocalDate): Int
    
    @Query("DELETE FROM reservations")
    suspend fun deleteAll()
} 