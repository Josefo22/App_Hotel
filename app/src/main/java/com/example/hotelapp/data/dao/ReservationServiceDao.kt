package com.example.hotelapp.data.dao

import androidx.room.*
import com.example.hotelapp.data.entity.ReservationService
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservationServiceDao {
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM reservation_services ORDER BY date DESC")
    fun getAllReservationServices(): Flow<List<ReservationService>>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM reservation_services WHERE id = :id")
    fun getReservationServiceById(id: Long): Flow<ReservationService>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM reservation_services WHERE reservationId = :reservationId ORDER BY date DESC")
    fun getServicesByReservation(reservationId: Long): Flow<List<ReservationService>>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM reservation_services WHERE serviceId = :serviceId ORDER BY date DESC")
    fun getReservationsByService(serviceId: Long): Flow<List<ReservationService>>
    
    @Query("SELECT SUM(price * quantity) FROM reservation_services WHERE reservationId = :reservationId")
    fun getTotalServiceCharge(reservationId: Long): Flow<Double?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reservationService: ReservationService): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(reservationServices: List<ReservationService>)
    
    @Update
    suspend fun update(reservationService: ReservationService)
    
    @Delete
    suspend fun delete(reservationService: ReservationService)
    
    @Query("DELETE FROM reservation_services WHERE reservationId = :reservationId")
    suspend fun deleteAllForReservation(reservationId: Long)
} 