package com.example.hotelapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hotelapp.data.model.ReservationService

@Dao
interface ReservationServiceDao {
    @Query("SELECT * FROM reservation_services ORDER BY date DESC")
    fun getAllReservationServices(): LiveData<List<ReservationService>>
    
    @Query("SELECT * FROM reservation_services WHERE id = :reservationServiceId")
    fun getReservationServiceById(reservationServiceId: Long): LiveData<ReservationService>
    
    @Query("SELECT * FROM reservation_services WHERE reservationId = :reservationId ORDER BY date DESC")
    fun getServicesByReservation(reservationId: Long): LiveData<List<ReservationService>>
    
    @Query("SELECT * FROM reservation_services WHERE serviceId = :serviceId")
    fun getReservationsByService(serviceId: Long): LiveData<List<ReservationService>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReservationService(reservationService: ReservationService): Long
    
    @Update
    suspend fun updateReservationService(reservationService: ReservationService)
    
    @Delete
    suspend fun deleteReservationService(reservationService: ReservationService)
} 